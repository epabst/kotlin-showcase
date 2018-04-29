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

interface WithID<T : WithID<T>> {
    fun getID(): ID<T>?

    fun withID(id: ID<T>): T
}

/**
 * The parent ID to use for a top-level entity that implements ChildWithID.
 * This is used in the database, so beware with changing it if data already exists.
 * It's useful to do this so that the database structure has a consistent structure
 * for top-level and child entities, simplifying security rules such as in Firebase's real-time database.
 */
val noParentId = ID<Nothing>("main")

object NoParent: WithID<Nothing> {
    override fun getID(): ID<Nothing>? = noParentId

    override fun withID(id: ID<Nothing>): Nothing = throw UnsupportedOperationException("NoParent")
}

interface ChildWithID<T : WithID<T>, P : WithID<P>> : WithID<T> {
    val parentId: ID<P>
}

data class ByParentId<T: ChildWithID<T,P>,P: WithID<P>>(val parentId: ID<P>) : RepositoryCriteria<T> {
    override fun invoke(entity: T): Boolean = parentId == entity.parentId
}

data class ByParentIds<T: ChildWithID<T,P>,P: WithID<P>>(val parentIds: Collection<ID<P>>) : RepositoryCriteria<T> {
    override fun invoke(entity: T): Boolean = parentIds.contains(entity.parentId)
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

    fun remove(id: ID<T>): Boolean {
        val item = find(id)
        if (item != null) {
            return remove(item)
        } else {
            return false
        }
    }

    fun remove(item: T): Boolean {
        return item.getID()?.let { remove(it) } ?: false
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

    val localStorageKeys: Set<String>
}

fun <T : WithID<T>> Repository<T>.removeAll(criteria: RepositoryCriteria<T>) {
    list(criteria).forEach { remove(it) }
}

class EmptyRepository<T : WithID<T>> : Repository<T> {
    override fun list(): List<T> = emptyList()

    override fun save(original: T?, replacement: T): ID<T> = throw UnsupportedOperationException("read-only")

    override fun remove(id: ID<T>): Boolean = false

    override fun generateID(): ID<T> = throw UnsupportedOperationException("read-only")

    override fun addListener(listener: RepositoryListener<T>) = Unit // no-op

    override fun removeListener(listener: RepositoryListener<T>) = Unit // no-op

    override val localStorageKeys: Set<String> = emptySet()
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

data class IdSet<T : WithID<T>>(val ids: Collection<ID<T>>) : RepositoryCriteria<T> {
    override fun invoke(entity: T): Boolean = ids.contains(entity.getID())
}

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

abstract class NormalizingRepository<T : WithID<T>> : Repository<T> {
    final override fun save(original: T?, replacement: T): ID<T> {
        val originalID = original?.getID() ?: replacement.getID()
        val replacementWithID = getOrGenerateID(originalID, replacement)
        val newID = replacementWithID.getID()!!
        val originalWithID = originalID?.let { original?.withID(it) }
        if (originalWithID != replacementWithID) {
            doSaveAndNotify(originalID, originalWithID, replacementWithID)
        }
        return newID
    }

    abstract fun doSaveAndNotify(originalID: ID<T>?, originalWithID: T?, replacementWithID: T)
}

abstract class NotifyingRepository<T : WithID<T>>(val undoProvider: UndoProvider) : NormalizingRepository<T>() {
    private val _listeners: MutableList<RepositoryListener<T>> = ArrayList(4)

    protected val listeners: List<RepositoryListener<T>> get() = _listeners

    override fun doSaveAndNotify(originalID: ID<T>?, originalWithID: T?, replacementWithID: T) {
        undoProvider.undoable(
                if (originalID == null) "Added $replacementWithID" else "Updated $replacementWithID",
                if (originalID == null) "Deleted $replacementWithID" else "Reverted $originalWithID") {
            doSave(originalWithID, replacementWithID)
            listeners.forEach { it.onSaved(originalWithID, replacementWithID) }
            doAfterNotify()
        }
    }

    abstract fun doSave(originalWithID: T?, replacementWithID: T)

    final override fun remove(item: T): Boolean {
        return undoProvider.undoable("Deleted $item", "Restored $item") {
            val didRemoval = doRemove(item)
            if (didRemoval) {
                listeners.forEach { it.onRemoved(item) }
            }
            doAfterNotify()
            didRemoval
        }
    }

    abstract fun doRemove(item: T): Boolean

    open fun doAfterNotify() {}

    final override fun addListener(listener: RepositoryListener<T>) {
        _listeners += listener
    }

    final override fun removeListener(listener: RepositoryListener<T>) {
        _listeners -= listener
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
        undoProvider: UndoProvider = UndoProvider.empty,
        private val categorizer: (T) -> R) : NotifyingRepository<T>(undoProvider) {

    var skipNotificationsFromChildrenDueToInternalChange = false

    private val childrenListener = object : RepositoryListener<T> {
        override fun onSaved(original: T?, replacementWithID: T) {
            if (!skipNotificationsFromChildrenDueToInternalChange) {
                listeners.forEach { it.onSaved(original, replacementWithID) }
                doAfterNotify()
            }
        }

        override fun onRemoved(item: T) {
            if (!skipNotificationsFromChildrenDueToInternalChange) {
                listeners.forEach { it.onRemoved(item) }
                doAfterNotify()
            }
        }
    }

    init {
        repositoryMap.values.forEach { it.addListener(childrenListener) }
    }

    override fun list(): List<T> = repositoryMap.values.flatMap { it.list() }

    override fun find(id: ID<T>): T? {
        return repositoryMap.values.asSequence().map { it.find(id) }.find { it != null }
    }

    override fun doSave(originalWithID: T?, replacementWithID: T) {
        skipNotificationsFromChildrenDueToInternalChange = true
        try {
            val originalCategory = originalWithID?.let { categorizer.invoke(it) }
            val category = categorizer.invoke(replacementWithID)
            val replacementRepository = repositoryMap[category] ?: error("no repository found for category=$category")
            if (originalCategory == category) {
                replacementRepository.save(originalWithID, replacementWithID)
            } else {
                val originalRepository = originalCategory?.let {
                    repositoryMap[it] ?: error("no repository found for category=$category")
                }
                originalRepository?.remove(originalWithID)
                replacementRepository.save(null, replacementWithID)
            }
        } finally {
            skipNotificationsFromChildrenDueToInternalChange = false
        }
    }

    override fun doRemove(item: T): Boolean {
        skipNotificationsFromChildrenDueToInternalChange = true
        try {
            return repositoryMap.values.asSequence().any { it.remove(item) }
        } finally {
            skipNotificationsFromChildrenDueToInternalChange = false
        }
    }

    override fun generateID(): ID<T> = repositoryMap.values.firstOrNull()!!.generateID()

    override val localStorageKeys: Set<String>
        get() = repositoryMap.values.flatMap { it.localStorageKeys }.toSet()
}

open class InMemoryRepository<T : WithID<T>>(undoProvider: UndoProvider = UndoProvider.empty) : NotifyingRepository<T>(undoProvider) {
    private val list: ArrayList<T> = ArrayList()

    override fun list(): List<T> = list.toList()

    override fun doSave(originalWithID: T?, replacementWithID: T) {
        putIntoList(list, replacementWithID, originalWithID?.getID())
    }

    override fun generateID(): ID<T> = ID(idGenerator.generateID().toString())

    override fun doRemove(item: T): Boolean {
        val index = list.indexOfFirst { it.getID() == item.getID() }
        if (index >= 0) {
            list.removeAt(index)
        }
        return index >= 0
    }

    override val localStorageKeys: Set<String> = emptySet()

    companion object {
        private val idGenerator = IdGenerator()
    }
}

interface FieldSelector<T : WithID<T>, out F> {
    fun invoke(entity: T): F?
    override operator fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}

class IdFieldSelector<T : WithID<T>> : FieldSelector<T,ID<T>> {
    override fun invoke(entity: T): ID<T>? = entity.getID()
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

data class RepositoryQuery<T : WithID<T>, out F>(val selector: FieldSelector<T, F>, val criteria: RepositoryCriteria<T>)
