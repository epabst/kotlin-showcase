package client.util

import client.component.UndoComponent
import common.util.*
import kotlin.js.Math.random

/**
 * A [Repository] that uses [browser.localStorage].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */
open class LocalStorageRepository<T : WithID<T>,JS>(val localStorageKey: String, private val toData: (JS) -> T) : NotifyingRepository<T>(UndoComponent) {
    private val mapInLocalStorage = MapInLocalStorage(localStorageKey, toData)

    override fun generateID(): ID<T> {
        return ID((random() * Long.MAX_VALUE).toString())
    }

    override fun list(): List<T> = mapInLocalStorage.values.toList()

    override fun doSave(originalWithID: T?, replacementWithID: T) {
        console.info("Saving $replacementWithID over original=$originalWithID")
        mapInLocalStorage.put(replacementWithID.getID()!!._id, replacementWithID)
    }

    fun replaceAll(entityJsonArray: Array<JS>) {
        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
        val newValues: Map<String, T> = entityJsonArray.map { toIdAndData(it) }.toMap()
        val priorEntities = mapInLocalStorage.values
        mapInLocalStorage.clear()
        mapInLocalStorage.putAll(newValues)
        listeners.forEach { listener -> priorEntities.forEach { listener.onRemoved(it) } }
        listeners.forEach { listener -> newValues.forEach { listener.onSaved(null, it.value) } }
    }

    private fun toIdAndData(it: JS): Pair<String, T> {
        val value = toData(it)
        return value.getID()!!._id to value
    }

    override fun doRemove(item: T): Boolean {
        val found = mapInLocalStorage.remove(item.getID()!!._id) != null
        if (found) {
            console.info("Removed $item")
        }
        return found
    }

    override val localStorageKeys: Set<String> get() = setOf(localStorageKey)
}
