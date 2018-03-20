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
open class LocalStorageRepository<T : WithID<T>,JS>(val localStorageKey: String, private val toData: (JS) -> T) : NotifyingRepository<T>(UndoComponent) {
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

    override fun doSave(originalWithID: T?, replacementWithID: T) {
        console.info("Saving $replacementWithID over original=$originalWithID")
        putIntoList(listForLocalStorage, replacementWithID, originalWithID?.getID())
    }

    override fun doAfterNotify() {
        super.doAfterNotify()
        store()
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

    override fun doRemove(item: T): Boolean {
        val index = listForLocalStorage.indexOfFirst { it.getID() == item.getID() }
        val found = index >= 0
        if (found) {
            console.info("Removing $item")
            listForLocalStorage.removeAt(index)
        }
        return found
    }

    private fun store() {
        localStorage[localStorageKey] = JSON.stringify(listForLocalStorage)
    }

    override val localStorageKeys: Set<String> = setOf(localStorageKey)
}
