package common.util

/**
 * Persistence Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:01 AM
 */

@Suppress("unused") // Although T isn't used here it provides type-safety between IDs of different types.
data class ID<T : WithID<T>>(val id: Long) {
    override fun equals(other: Any?): Boolean = if (other is ID<*>) id == other.id else false
    override fun hashCode(): Int = (id % Int.MAX_VALUE).toInt()
    override fun toString(): String = id.toString()
}

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

    fun remove(item: T)

    fun remove(id: ID<T>) {
        val item = find(id)
        if (item != null) {
            remove(item)
        }
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
}

interface RepositoryCriteria<T : WithID<T>> {
    fun invoke(entity: T): Boolean

    override operator fun equals(other: Any?): Boolean

    override fun hashCode(): Int
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

open class InMemoryRepository<T : WithID<T>> : Repository<T> {
    private var idGenerator = IdGenerator()
    private val list: ArrayList<T> = ArrayList()
    private val listeners: ArrayList<RepositoryListener<T>> = ArrayList(4)

    override fun list(): List<T> = list.toList()

    override fun save(original: T?, replacement: T): ID<T> {
        val originalID = original?.getID()
        val replacementWithID = getOrGenerateID(originalID, replacement)
        if (original?.withID(replacementWithID.getID()!!) != replacementWithID) {
            putIntoList(list, replacementWithID, originalID)
            listeners.forEach { it.onSaved(original, replacementWithID) }
        }
        return replacementWithID.getID()!!
    }

    override fun generateID(): ID<T> = ID(idGenerator.generateID())

    override fun remove(item: T) {
        val removed = list.remove(item)
        if (removed) {
            listeners.forEach { it.onRemoved(item) }
        }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        listeners += listener
    }
}
