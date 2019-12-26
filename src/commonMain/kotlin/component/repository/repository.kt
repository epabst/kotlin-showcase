@file:Suppress("unused")

package component.repository

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

data class ByParentId<T: ChildWithID<T, P>,P: WithID<P>>(val parentId: ID<P>) : RepositoryCriteria<T> {
    override fun invoke(entity: T): Boolean = parentId == entity.parentId
}

data class ByParentIds<T: ChildWithID<T, P>,P: WithID<P>>(val parentIds: Collection<ID<P>>) : RepositoryCriteria<T> {
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

    fun <F: Any> list(query: RepositoryQuery<T, F>): List<F> {
        return list(query.criteria).map { query.selector.invoke(it) }.distinct().filterNotNull()
    }

    fun <F : Any> list(selector: FieldSelector<T, F>, criteria: RepositoryCriteria<T> = allItems()): List<F> {
        return list(RepositoryQuery(selector, criteria))
    }

    /** @return original.getID() or else replacement.getID() or else [generateID]. */
    suspend fun save(original: T?, replacement: T): ID<T>

    /** @return entity.getID() or else [generateID]. */
    suspend fun save(entity: T): ID<T> {
        val original = entity.getID()?.let { find(it) }
        return save(original, entity)
    }

    suspend fun saveAndGet(entity: T): T {
        return entity.withID(save(entity))
    }

    suspend fun remove(id: ID<T>): Boolean {
        val item = find(id)
        if (item != null) {
            return remove(item)
        } else {
            return false
        }
    }

    suspend fun remove(item: T): Boolean {
        return item.getID()?.let { remove(it) } ?: false
    }

    fun find(id: ID<T>): T? = list().find { it.getID() == id }

    suspend fun updateAll(criteria: RepositoryCriteria<T>, modify: (T) -> T) {
        list(criteria).forEach {
            save(it, modify(it))
        }
    }

    suspend fun generateID(): ID<T>

    suspend fun generateID(entityWithoutId: T): ID<T> = generateID()

    suspend fun withID(replacement: T, id: ID<T>): T {
        return if (replacement.getID() != null) {
            replacement
        } else {
            replacement.withID(id)
        }
    }

    fun addListener(listener: RepositoryListener<T>)

    fun removeListener(listener: RepositoryListener<T>)

    val localStorageKeys: Set<String>
}

suspend fun <T : WithID<T>> Repository<T>.removeAll(criteria: RepositoryCriteria<T>) {
    list(criteria).forEach { remove(it) }
}

suspend fun <T : WithID<T>> Repository<T>.removeAllAndList(criteria: RepositoryCriteria<T>): List<T> {
    val list = list(criteria)
    list.forEach { remove(it) }
    return list
}

class EmptyRepository<T : WithID<T>> : Repository<T> {
    override fun list(): List<T> = emptyList()

    override suspend fun save(original: T?, replacement: T): ID<T> = throw UnsupportedOperationException("read-only")

    override suspend fun remove(id: ID<T>): Boolean = false

    override suspend fun generateID(): ID<T> = throw UnsupportedOperationException("read-only")

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

interface Closeable {
    fun close()
}

interface RepositoryListener<in T> {
    suspend fun beforeSaving(original: T?, replacementWithID: T) {}

    suspend fun beforeRemoving(item: T) {}

    suspend fun onSaved(original: T?, replacementWithID: T) {}

    suspend fun onRemoved(item: T) {}

    suspend fun onVisibilityChanged(item: T, visible: Boolean) {}
}

internal suspend fun <T : WithID<T>> Repository<T>.getOrGenerateID(originalID: ID<T>?, replacement: T): T {
    val newID: ID<T> = originalID ?: generateID(replacement)
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
    final override suspend fun save(original: T?, replacement: T): ID<T> {
        val originalID = original?.getID() ?: replacement.getID()
        val replacementWithID = getOrGenerateID(originalID, replacement)
        val newID = replacementWithID.getID()!!
        val originalWithID = originalID?.let { original?.withID(it) }
        if (originalWithID != replacementWithID) {
            doSaveAndNotify(originalID, originalWithID, replacementWithID)
        }
        return newID
    }

    abstract suspend fun doSaveAndNotify(originalID: ID<T>?, originalWithID: T?, replacementWithID: T)
}

abstract class NotifyingRepository<T : WithID<T>>(val undoProvider: UndoProvider) : NormalizingRepository<T>() {
    private val _listeners: MutableList<RepositoryListener<T>> = ArrayList(4)

    protected val listeners: List<RepositoryListener<T>> get() = _listeners

    override suspend fun doSaveAndNotify(originalID: ID<T>?, originalWithID: T?, replacementWithID: T) {
        undoProvider.undoable(
                if (originalID == null) "Added $replacementWithID" else "Updated $replacementWithID",
                if (originalID == null) "Deleted $replacementWithID" else "Reverted $originalWithID") {
            listeners.forEach { it.beforeSaving(originalWithID, replacementWithID) }
            doSave(originalWithID, replacementWithID)
            listeners.forEach { it.onSaved(originalWithID, replacementWithID) }
            doAfterNotify()
        }
    }

    abstract suspend fun doSave(originalWithID: T?, replacementWithID: T)

    final override suspend fun remove(item: T): Boolean {
        val id = item.getID()
        if (id != null && find(id) == null) return false
        return undoProvider.undoable("Deleted $item", "Restored $item") {
            listeners.forEach { it.beforeRemoving(item) }
            val didRemoval = doRemove(item)
            if (didRemoval) {
                listeners.forEach { it.onRemoved(item) }
            }
            doAfterNotify()
            didRemoval
        }
    }

    abstract suspend fun doRemove(item: T): Boolean

    open fun doAfterNotify() {}

    final override fun addListener(listener: RepositoryListener<T>) {
        _listeners += listener
    }

    final override fun removeListener(listener: RepositoryListener<T>) {
        _listeners -= listener
    }
}

fun <T : WithID<T>> Repository<T>.onList(criteria: RepositoryCriteria<T> = allItems(),
                                         onList: (list: List<T>) -> Unit): Closeable {

    val initialList = list().filter { criteria.invoke(it) }
    onList(initialList)
    val listener = ListMaintainingListener(initialList, criteria) { _, newList -> onList(newList) }
    return withListener(listener)
}

fun <T : WithID<T>> Repository<T>.onListChanged(criteria: RepositoryCriteria<T> = allItems(),
                                                onListChanged: (oldList: List<T>, newList: List<T>) -> Unit): Closeable {

    val initialList = list().filter { criteria.invoke(it) }
    val listener = ListMaintainingListener(initialList, criteria, onListChanged)
    return withListener(listener)
}

private fun <T : WithID<T>> Repository<T>.withListener(listener: RepositoryListener<T>): Closeable {
    addListener(listener)
    return object : Closeable {
        override fun close() {
            removeListener(listener)
        }
    }
}

private class ListMaintainingListener<T : WithID<T>>(initialList: List<T>,
                                             private val criteria: RepositoryCriteria<T>,
                                             private val onListChanged: (oldList: List<T>, newList: List<T>) -> Unit) : RepositoryListener<T> {
    private var _priorList: List<T> = initialList

    var priorList: List<T>
        get() = _priorList
        set(value) {
            onListChanged.invoke(priorList, value)
            _priorList = value
        }

    override suspend fun onSaved(original: T?, replacementWithID: T) {
        if (criteria.invoke(replacementWithID)) {
            priorList = if (original != null && criteria.invoke(original)) {
                priorList.map { if (it == original) replacementWithID else it }
            } else {
                priorList + replacementWithID
            }
        } else if (original != null && criteria.invoke(original)) {
            priorList = priorList.filter { it != original }
        }
    }

    override suspend fun onRemoved(item: T) {
        if (criteria.invoke(item)) {
            priorList = priorList.filter { it != item }
        }
    }

    override suspend fun onVisibilityChanged(item: T, visible: Boolean) {
        if (visible) {
            onSaved(null, item)
        } else {
            onRemoved(item)
        }
    }
}

interface UndoProvider {
    suspend fun <T : WithID<T>, F> undoableSave(original: T?, replacementWithID: T, function: suspend () -> F): F {
        val isUpdate = original != null && original.getID() != null
        val pastTenseDescription = if (isUpdate) "Updated $original" else "Added $replacementWithID"
        val undoPastTenseDescription = if (isUpdate) "Reverted $original" else "Deleted $replacementWithID"
        return undoable(pastTenseDescription, undoPastTenseDescription, function)
    }


    suspend fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: suspend () -> T): T

    suspend fun <T> notUndoable(function: suspend () -> T): T

    companion object {
        val empty: UndoProvider = object : UndoProvider {
            override suspend fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: suspend () -> T): T {
                return function()
            }

            override suspend fun <T> notUndoable(function: suspend () -> T): T {
                return function()
            }
        }
    }
}

open class CompositeRepository<T : WithID<T>,R>(
        private val repositoryMap: Map<R, Repository<T>>,
        undoProvider: UndoProvider = UndoProvider.empty,
        private val categorizer: (T) -> R) : NotifyingRepository<T>(undoProvider) {

    var skipNotificationsFromChildrenDueToInternalChange = false

    private val childrenListener: RepositoryListener<T> = object : RepositoryListener<T> {
        override suspend fun beforeSaving(original: T?, replacementWithID: T) {
            if (!skipNotificationsFromChildrenDueToInternalChange) {
                listeners.forEach { it.beforeSaving(original, replacementWithID) }
            }
        }

        override suspend fun beforeRemoving(item: T) {
            if (!skipNotificationsFromChildrenDueToInternalChange) {
                listeners.forEach { it.beforeRemoving(item) }
            }
        }

        override suspend fun onSaved(original: T?, replacementWithID: T) {
            if (!skipNotificationsFromChildrenDueToInternalChange) {
                listeners.forEach { it.onSaved(original, replacementWithID) }
                doAfterNotify()
            }
        }

        override suspend fun onRemoved(item: T) {
            if (!skipNotificationsFromChildrenDueToInternalChange) {
                listeners.forEach { it.onRemoved(item) }
                doAfterNotify()
            }
        }

        override suspend fun onVisibilityChanged(item: T, visible: Boolean) {
            listeners.forEach { it.onVisibilityChanged(item, visible) }
            doAfterNotify()
        }
    }

    init {
        repositoryMap.values.forEach { it.addListener(childrenListener) }
    }

    override fun list(): List<T> = repositoryMap.values.flatMap { it.list() }

    override fun find(id: ID<T>): T? {
        return repositoryMap.values.asSequence().map { it.find(id) }.find { it != null }
    }

    override suspend fun doSave(originalWithID: T?, replacementWithID: T) {
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

    override suspend fun doRemove(item: T): Boolean {
        skipNotificationsFromChildrenDueToInternalChange = true
        try {
            val category = categorizer.invoke(item)
            val repositories = repositoryMap[category]?.let { listOf(it) } ?: repositoryMap.values
            return repositories.asSequence().any { it.remove(item) }
        } finally {
            skipNotificationsFromChildrenDueToInternalChange = false
        }
    }

    override suspend fun generateID(): ID<T> = repositoryMap.values.firstOrNull()!!.generateID()

    override suspend fun generateID(entityWithoutId: T): ID<T> {
        val category = categorizer.invoke(entityWithoutId)
        val repositories = repositoryMap[category]?.let { listOf(it) } ?: repositoryMap.values
        return repositories.firstOrNull()?.generateID(entityWithoutId) ?: generateID()
    }

    override val localStorageKeys: Set<String>
        get() = repositoryMap.values.flatMap { it.localStorageKeys }.toSet()
}

open class InMemoryRepository<T : WithID<T>>(undoProvider: UndoProvider = UndoProvider.empty) : NotifyingRepository<T>(undoProvider) {
    private val list: ArrayList<T> = ArrayList()

    override fun list(): List<T> = list.toList()

    override suspend fun doSave(originalWithID: T?, replacementWithID: T) {
        putIntoList(list, replacementWithID, originalWithID?.getID())
    }

    override suspend fun generateID(): ID<T> = ID(idGenerator.generateID().toString())

    override suspend fun doRemove(item: T): Boolean {
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

class IdFieldSelector<T : WithID<T>> : FieldSelector<T, ID<T>> {
    override fun invoke(entity: T): ID<T>? = entity.getID()
    override fun equals(other: Any?): Boolean = other is IdFieldSelector<*>
    override fun hashCode(): Int = toString().hashCode()
    override fun toString(): String = "IdFieldSelector"
}

class SelfSelector<T : WithID<T>> : FieldSelector<T, T> {
    override fun invoke(entity: T): T = entity
    override fun equals(other: Any?): Boolean = other is SelfSelector<*>
    override fun hashCode(): Int = toString().hashCode()
    override fun toString(): String = "SelfSelector"
}

data class RepositoryQuery<T : WithID<T>, out F>(val selector: FieldSelector<T, F>, val criteria: RepositoryCriteria<T>)
