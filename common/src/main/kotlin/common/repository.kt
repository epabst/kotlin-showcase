package common

import java.util.*

/**
 * Persistence Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:01 AM
 */

data class ID(val id: Long) {
    override fun equals(other: Any?): Boolean = if (other is ID) id == other.id else false
    override fun hashCode(): Int = (id % Int.MAX_VALUE).toInt()
    override fun toString(): String = id.toLong().toString()
}

interface WithID<T : WithID<T>> {
    fun getID(): ID?

    fun withID(id: ID): T
}

class IdGenerator {
    private var next: Long = 1

    fun generateID(): ID {
        val result = next
        next = result + 1
        return ID(result)
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

    /** @return original.getID() or else replacement.getID() or else [generateID]. */
    fun save(original: T?, replacement: T): ID

    fun remove(item: T)

    fun remove(id: ID) {
        val item = find(id)
        if (item != null) {
            remove(item)
        }
    }

    fun find(id: ID): T? = list().find { it.getID() == id }

    fun generateID(): ID

    fun withID(replacement: T, id: ID = generateID()): T {
        if (replacement.getID() != null) {
            return replacement
        } else {
            return replacement.withID(id)
        }
    }

    fun addListener(listener: RepositoryListener<T>)
}

interface RepositoryListener<T> {
    fun onSaved(original: T?, replacementWithID: T)

    fun onRemoved(item: T)
}

internal fun <T : WithID<T>> Repository<T>.putIntoList(mutableList: ArrayList<T>, replacement: T, original: T?): T {
    val originalID = original?.getID()
    val newID: ID = originalID ?: generateID()
    val replacementWithID = withID(replacement, newID)
    val index = if (originalID != null) mutableList.indexOfFirst { it.getID() == originalID } else -1
    if (index >= 0) {
        mutableList[index] = replacementWithID
    } else {
        mutableList.add(replacementWithID)
    }
    return replacementWithID
}

open class InMemoryRepository<T : WithID<T>>() : Repository<T> {
    private var idGenerator = IdGenerator()
    private val list: ArrayList<T> = ArrayList()
    private val listeners: ArrayList<RepositoryListener<T>> = ArrayList(4)

    override fun list(): List<T> = list.toList()

    override fun save(original: T?, replacement: T): ID {
        val replacementWithID = putIntoList(list, replacement, original)
        listeners.forEach { it.onSaved(original, replacementWithID) }
        return replacementWithID.getID()!!
    }

    override fun generateID(): ID = idGenerator.generateID()

    override fun remove(item: T) {
        list.remove(item)
        listeners.forEach { it.onRemoved(item) }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        listeners += listener
    }
}
