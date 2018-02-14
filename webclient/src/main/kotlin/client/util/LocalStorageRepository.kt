package client.util

import client.component.UndoComponent
import common.util.*
import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.browser.localStorage
import kotlin.js.Math

/**
 * A [Repository] that uses [browser.localStorage].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */
open class LocalStorageRepository<T : WithID<T>,JS>(val localStorageKey: String, private val toData: (JS) -> T) : Repository<T> {
    private val listeners: ArrayList<RepositoryListener<T>> = ArrayList(4)

    override fun generateID(): ID<T> {
        return ID((Math.random() * Long.MAX_VALUE).toString())
    }

    private val listForLocalStorage: ArrayList<T> by lazy {
        val listOrNull: List<T>? = localStorage[localStorageKey]?.let { listString ->
            try {
//                console.info(localStorageKey + ": " + listString)
                val jsArray = JSON.parse<Array<JS>>(listString)
                jsArray.map { toData(it) }
            } catch (t: Throwable) {
                console.info(localStorageKey + ": " + listString)
                console.error(t)
                null
            }
        }
        listOrNull?.let { ArrayList(it) } ?: ArrayList()
    }

    override fun list(): List<T> = listForLocalStorage.toList()

    override fun save(original: T?, replacement: T): ID<T> {
        val originalID = original?.getID() ?: replacement.getID()
        val replacementWithID = getOrGenerateID(originalID, replacement)
        val newID = replacementWithID.getID()!!
        val originalWithID = originalID?.let { original?.withID(it) }
        if (originalWithID != replacementWithID) {
            UndoComponent.undoable(
                    if (originalID == null) "Added $replacementWithID" else "Updated $replacementWithID",
                    if (originalID == null) "Deleted $replacementWithID" else "Reverted $originalWithID") {
                putIntoList(listForLocalStorage, replacementWithID, originalID)
                listeners.forEach { it.onSaved(originalWithID, replacementWithID) }
                store()
            }
        }
        return newID
    }

    fun replaceAll(entityJsonArray: Array<JS>) {
        val parsedEntities = emptyList<T>().toMutableList()
        entityJsonArray.mapTo(parsedEntities, toData)
        val priorEntities = listForLocalStorage.toList()
        listForLocalStorage.clear()
        listForLocalStorage.addAll(parsedEntities)
        listeners.forEach { listener -> priorEntities.forEach { listener.onRemoved(it) } }
        listeners.forEach { listener -> parsedEntities.forEach { listener.onSaved(null, it) } }
        store()
    }

    override fun remove(id: ID<T>) {
        val index = listForLocalStorage.indexOfFirst { it.getID() == id }
        if (index >= 0) {
            val item = listForLocalStorage.get(index)
            UndoComponent.undoable("Deleted $item", "Restored $item") {
                listForLocalStorage.removeAt(index)
                listeners.forEach { it.onRemoved(item) }
                store()
            }
        }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        listeners += listener
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        listeners -= listener
    }

    private fun store() {
        localStorage[localStorageKey] = JSON.stringify(listForLocalStorage)
    }

    override val localStorageKeys: Set<String> = setOf(localStorageKey)
}
