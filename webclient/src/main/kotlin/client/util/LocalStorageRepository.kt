package client.util

import common.util.*
import kotlin.browser.localStorage
import kotlin.js.Math

/**
 * A [Repository] that uses [browser.localStorage].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */
open class LocalStorageRepository<T : WithID<T>,JS>(private val localStorageKey: String, toData: (JS) -> T) : Repository<T> {
    private val listeners: ArrayList<RepositoryListener<T>> = ArrayList(4)

    override fun generateID(): ID<T> {
        return ID((Math.random() * Long.MAX_VALUE).toLong())
    }

    private var listOrNull: List<T>? = localStorage.getItem(localStorageKey)?.let { listString ->
        try {
//            console.info(localStorageKey + ": " + listString)
            val jsArray = JSON.parse<Array<JS>>(listString)
            jsArray.map { toData(it) }
        } catch (t: Throwable) {
            console.info(localStorageKey + ": " + listString)
            console.error(t)
            null
        }
    }

    private val list: ArrayList<T> = listOrNull?.let { ArrayList(it) } ?: ArrayList()

    fun isInitialized(): Boolean = listOrNull != null

    override fun list(): List<T> = list.toList()

    override fun save(original: T?, replacement: T): ID<T> {
        val originalID = original?.getID()
        val replacementWithID = getOrGenerateID(originalID, replacement)
        val newID = replacementWithID.getID()!!
        val originalWithID = original?.withID(newID)
        if (originalWithID != replacementWithID) {
            putIntoList(list, replacementWithID, originalID)
            store()
            listeners.forEach { it.onSaved(originalWithID, replacementWithID) }
        }
        return newID
    }

    override fun remove(item: T) {
        val removed = list.remove(item)
        if (removed) {
            store()
            listeners.forEach { it.onRemoved(item) }
        }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        listeners += listener
    }

    private fun store() {
        localStorage.setItem(localStorageKey, JSON.stringify(list))
        listOrNull = list
    }
}
