package client.util

import client.component.UndoComponent
import common.util.*
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.onChange
import net.yested.core.properties.toProperty
import kotlin.js.Math.random

/**
 * A [Repository] that uses [browser.localStorage].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */
open class LocalStorageRepository<T : WithID<T>,JS>(val relativePath: String,
                                                    private val localStorageKeysProperty: ReadOnlyProperty<Set<String>>,
                                                    private val toData: (JS) -> T,
                                                    private val localStorageKeyChooser: (T) -> String) : NotifyingRepository<T>(UndoComponent) {
    constructor(localStorageKey: String, toData: (JS) -> T) :
            this(localStorageKey, setOf(localStorageKey).toProperty(), toData, { localStorageKey })

    private val mapInLocalStorageByKey = mutableMapOf<String,MapInLocalStorage<JS,T>>()

    init {
        localStorageKeysProperty.onChange { oldKeys, newKeys ->
            oldKeys.minus(newKeys).forEach { key ->
                listeners.forEach { listener ->
                    // don't check localStorageKeysProperty for this to avoid access denied error
                    mapInLocalStorageByKey[key]?.values?.forEach { it -> listener.onRemoved(it) }
                }
            }
            newKeys.minus(oldKeys).forEach { key ->
                listeners.forEach { listener ->
                    mapInLocalStorage(key).values.forEach { it -> listener.onSaved(null, it) }
                }
            }
        }
    }

    private fun mapInLocalStorage(key: String): MapInLocalStorage<JS, T> {
        return mapInLocalStorageByKey.getOrPut(key) { MapInLocalStorage(key, toData) }
    }

    override fun generateID(): ID<T> {
        return ID((random() * Long.MAX_VALUE).toString())
    }

    override fun list(): List<T> = localStorageKeysProperty.get().flatMap { key -> mapInLocalStorage(key).values }

    override fun doSave(originalWithID: T?, replacementWithID: T) {
        console.info("Saving $replacementWithID over original=$originalWithID")
        mapInLocalStorage(localStorageKeyChooser.invoke(replacementWithID)).put(replacementWithID.getID()!!._id, replacementWithID)
    }

    fun replaceAll(entityJsonArray: Array<JS>) {
        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
        val newIdsAndValuesByKey: Map<String, List<Pair<String,T>>> = entityJsonArray.map { toIdAndData(it) }.groupBy { localStorageKeyChooser.invoke(it.second) }
        val priorEntities = list()
        localStorageKeysProperty.get().forEach { mapInLocalStorage(it).clear() }
        newIdsAndValuesByKey.forEach { entry -> mapInLocalStorage(entry.key).putAll(entry.value.toMap()) }
        listeners.forEach { listener -> priorEntities.forEach { listener.onRemoved(it) } }
        listeners.forEach { listener -> newIdsAndValuesByKey.forEach { it.value.forEach { listener.onSaved(null, it.second) } } }
    }

    private fun toIdAndData(it: JS): Pair<String, T> {
        val value = toData(it)
        return value.getID()!!._id to value
    }

    override fun doRemove(item: T): Boolean {
        return removeFromLocalStorage(localStorageKeyChooser.invoke(item), item)
    }

    protected fun removeFromLocalStorage(localStorageKey: String, item: T): Boolean {
        val found = mapInLocalStorage(localStorageKey).remove(item.getID()!!._id) != null
        if (found) {
            console.info("Removed $item")
        }
        return found
    }

    override val localStorageKeys: Set<String> get() = localStorageKeysProperty.get()
}
