package common.util

/**
 * Persistence Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:01 AM
 */

@Suppress("unused") // Although T isn't used here it provides type-safety between IDs of different types.
data class ID<T : WithID<T>>(val _id: String) {
    override fun equals(other: Any?): Boolean = if (other is ID<*>) _id == other._id else false
    override fun hashCode(): Int = _id.hashCode()
    override fun toString(): String = _id
}

fun <T : WithID<T>> ID(id: Long): ID<T> = ID(id.toString())

interface WithID<T : WithID<T>> {
    fun getID(): ID<T>?

    fun withID(id: ID<T>): T
}

class IdGenerator {
    private var next: Long = 1

    fun generateID(): Long {
        val result = next
        next = result + 1
        return result
    }
}

/**
 * The model for working with a certain kind of stored entities.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/25/16
 * Time: 3:53 PM
 */
interface Repository<T : WithID<T>> {
    fun list(): List<T>

    fun list(criteria: RepositoryCriteria<T>): List<T> = list().filter { criteria.invoke(it) }

    /** @return original.getID() or else replacement.getID() or else [generateID]. */
    fun save(original: T?, replacement: T): ID<T>

    /** @return entity.getID() or else [generateID]. */
    fun save(entity: T): ID<T> {
        val original = entity.getID()?.let { find(it) }
        return save(original, entity)
    }

    fun saveAndGet(entity: T): T {
        return entity.withID(save(entity))
    }

    fun remove(id: ID<T>) {
        val item = find(id)
        if (item != null) {
            remove(item)
        }
    }

    fun remove(item: T) {
        item.getID()?.let { remove(it) }
    }

    fun find(id: ID<T>): T? = list().find { it.getID() == id }

    fun generateID(): ID<T>

    fun withID(replacement: T, id: ID<T> = generateID()): T {
        if (replacement.getID() != null) {
            return replacement
        } else {
            return replacement.withID(id)
        }
    }

    fun addListener(listener: RepositoryListener<T>)

    fun removeListener(listener: RepositoryListener<T>)
}

fun <T : WithID<T>> Repository<T>.removeAll(criteria: RepositoryCriteria<T>) {
    list(criteria).forEach { remove(it) }
}

interface RepositoryCriteria<T : WithID<T>> {
    fun invoke(entity: T): Boolean

    override operator fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}

private class AllItems<T : WithID<T>> : RepositoryCriteria<T> {
    override fun invoke(entity: T): Boolean = true
    override fun equals(other: Any?): Boolean = other is AllItems<*>

    override fun hashCode(): Int = 93993
}

fun <T : WithID<T>> allItems(): RepositoryCriteria<T> = AllItems()

interface RepositoryListener<in T> {
    fun onSaved(original: T?, replacementWithID: T)

    fun onRemoved(item: T)
}

internal fun <T : WithID<T>> Repository<T>.getOrGenerateID(originalID: ID<T>?, replacement: T): T {
    val newID: ID<T> = originalID ?: generateID()
    val replacementWithID = withID(replacement, newID)
    return replacementWithID
}

internal fun <T : WithID<T>> putIntoList(mutableList: ArrayList<T>, replacementWithID: T, originalID: ID<T>?) {
    val index = if (originalID != null) mutableList.indexOfFirst { it.getID() == originalID } else -1
    if (index >= 0) {
        mutableList[index] = replacementWithID
    } else {
        mutableList.add(replacementWithID)
    }
}

interface UndoProvider {
    fun <T : WithID<T>, F> undoableSave(original: T?, replacementWithID: T, function: () -> F): F {
        val isUpdate = original != null && original.getID() != null
        val pastTenseDescription = if (isUpdate) "Updated $original" else "Added $replacementWithID"
        val undoPastTenseDescription = if (isUpdate) "Reverted $original" else "Deleted $replacementWithID"
        return undoable(pastTenseDescription, undoPastTenseDescription, function)
    }


    fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: () -> T): T

    fun <T> notUndoable(function: () -> T): T

    companion object {
        val empty: UndoProvider = object : UndoProvider {
            override fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: () -> T): T {
                return function()
            }

            override fun <T> notUndoable(function: () -> T): T {
                return function()
            }
        }
    }
}

open class CompositeRepository<T : WithID<T>,R>(
        private val repositoryMap: Map<R,Repository<T>>,
        private val undoProvider: UndoProvider = UndoProvider.empty,
        private val categorizer: (T) -> R) : Repository<T> {
    override fun list(): List<T> = repositoryMap.values.flatMap { it.list() }

    override fun find(id: ID<T>): T? {
        return repositoryMap.values.asSequence().map { it.find(id) }.find { it != null }
    }

    override fun save(original: T?, replacement: T): ID<T> {
        val originalCategory = original?.let { categorizer.invoke(it) }
        val category = categorizer.invoke(replacement)
        val replacementRepository = repositoryMap[category] ?: error("no repository found for category=$category")
        if (originalCategory == category) {
            return replacementRepository.save(original, replacement)
        } else {
            return undoProvider.undoableSave(original, replacement) {
                val originalRepository = originalCategory?.let { repositoryMap[it] ?: error("no repository found for category=$category") }
                originalRepository?.remove(original)
                replacementRepository.save(null, replacement)
            }
        }
    }

    override fun remove(id: ID<T>) {
        return repositoryMap.values.asSequence().forEach { it.remove(id) }
    }

    override fun generateID(): ID<T> = repositoryMap.values.firstOrNull()!!.generateID()

    override fun addListener(listener: RepositoryListener<T>) {
        repositoryMap.values.forEach { it.addListener(listener) }
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        repositoryMap.values.forEach { it.removeListener(listener) }
    }
}

open class InMemoryRepository<T : WithID<T>> : Repository<T> {
    private val list: ArrayList<T> = ArrayList()
    private val listeners: ArrayList<RepositoryListener<T>> = ArrayList(4)

    override fun list(): List<T> = list.toList()

    override fun save(original: T?, replacement: T): ID<T> {
        val originalID = original?.getID() ?: replacement.getID()
        val replacementWithID = getOrGenerateID(originalID, replacement)
        val newID = replacementWithID.getID()!!
        val originalWithID = originalID?.let { original?.withID(it) }
        if (originalWithID != replacementWithID) {
            putIntoList(list, replacementWithID, originalID)
            listeners.forEach { it.onSaved(originalWithID, replacementWithID) }
        }
        return newID
    }

    override fun generateID(): ID<T> = ID(idGenerator.generateID().toString())

    override fun remove(id: ID<T>) {
        val index = list.indexOfFirst { it.getID() == id }
        if (index >= 0) {
            val item = list.removeAt(index)
            listeners.forEach { it.onRemoved(item) }
        }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        listeners += listener
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        listeners -= listener
    }

    companion object {
        private val idGenerator = IdGenerator()
    }
}

interface FieldSelector<T : WithID<T>,F> {
    fun invoke(entity: T): F
    override operator fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}

class IdFieldSelector<T : WithID<T>> : FieldSelector<T,ID<T>> {
    override fun invoke(entity: T): ID<T> = entity.getID()!!
    override fun equals(other: Any?): Boolean = other is IdFieldSelector<*>
    override fun hashCode(): Int = toString().hashCode()
    override fun toString(): String = "IdFieldSelector"
}

class SelfSelector<T : WithID<T>> : FieldSelector<T,T> {
    override fun invoke(entity: T): T = entity
    override fun equals(other: Any?): Boolean = other is SelfSelector<*>
    override fun hashCode(): Int = toString().hashCode()
    override fun toString(): String = "SelfSelector"
}
