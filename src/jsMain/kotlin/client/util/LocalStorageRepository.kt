package client.util

import client.component.UndoComponent
import common.util.*
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.onChange
import net.yested.core.properties.toProperty
import kotlin.random.Random

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
                    mapInLocalStorageByKey[key]?.values?.forEach { it -> listener.onVisibilityChanged(it, false) }
                }
            }
            newKeys.minus(oldKeys).forEach { key ->
                listeners.forEach { listener ->
                    mapInLocalStorage(key).values.forEach { it -> listener.onVisibilityChanged(it, true) }
                }
            }
        }
    }

    private fun mapInLocalStorage(key: String): MapInLocalStorage<JS, T> {
        return mapInLocalStorageByKey.getOrPut(key) { MapInLocalStorage(key, toData) }
    }

    override fun generateID(): ID<T> {
        return ID(Random.nextLong().toString())
    }

    override fun list(): List<T> = localStorageKeysProperty.get().flatMap { key -> mapInLocalStorage(key).values }

    override fun find(id: ID<T>): T? {
        return localStorageKeysProperty.get().mapNotNull { mapInLocalStorage(it).currentMap[id._id] }.firstOrNull()
    }

    override fun doSave(originalWithID: T?, replacementWithID: T) {
        val originalKey = originalWithID?.let { localStorageKeyChooser.invoke(it) }
        val replacementKey = localStorageKeyChooser.invoke(replacementWithID)
        console.info("$currentContext: Saving $replacementWithID (key=$replacementKey) over original=$originalWithID (key=$originalKey)")
        if (originalKey != null && originalKey != replacementKey) {
            mapInLocalStorage(originalKey).remove(originalWithID.getID()!!._id)
        }
        mapInLocalStorage(replacementKey).put(replacementWithID.getID()!!._id, replacementWithID)
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
            console.info("$currentContext: Removed $item (key=$localStorageKey)")
        }
        return found
    }

    override val localStorageKeys: Set<String> get() = localStorageKeysProperty.get()
}
