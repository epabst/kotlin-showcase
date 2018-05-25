package client.util

import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.browser.localStorage
import kotlin.js.Json
import kotlin.js.json

/**
 * A map that is backed by a browser localStorage key/value pair.
 *
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/25/18
 * Time: 5:19 PM
 * @param localStorageKey the key in the browser localStorage
 * @param fromJson transforms a raw JSON object to an object that serializes to the same JSON via JSON.stringify.
 */
data class MapInLocalStorage<JS,T>(private val localStorageKey: String, val fromJson: (JS) -> T) {
    @Suppress("UNCHECKED_CAST")
    private val originalMap: Map<String,T>? by lazy {
        stringToMap(localStorage[localStorageKey], fromJson)
    }

    private val _currentMap: MutableMap<String,T> by lazy { originalMap?.toMutableMap() ?: mutableMapOf() }

    val currentMap: Map<String,T> = _currentMap
    val entries: Set<Map.Entry<String, T>> get() = currentMap.entries
    val keys: Set<String> get() = currentMap.keys
    val values: Collection<T> get() = currentMap.values

    fun <R> mapValues(transform: (Map.Entry<String, T>) -> R): Map<String, R> {
        return currentMap.mapValues(transform)
    }

    fun put(key: String, value: T) {
        _currentMap[key] = value
        storeWhenStable()
    }

    fun remove(key: String): T? {
        val value = _currentMap.remove(key)
        if (value != null) {
            storeWhenStable()
        }
        return value
    }

    fun clear() {
        _currentMap.clear()
    }

    private fun storeWhenStable() {
        whenStable(_currentMap, { it.map { it.key to it.value } }) {
            println("stable so writing to localStorage[$localStorageKey]")
            localStorage[localStorageKey] = JSON.stringify(json(*it.toTypedArray()))
        }
    }

    fun putAll(newValues: Map<String, T>) {
        _currentMap.putAll(newValues)
    }
}

external interface EntityJS {
    val id: IDJS
}

private fun <JS,T> stringToMap(string: String?, fromJson: (JS) -> T): Map<String, T>? {
    return string?.let { mapString ->
        try {
            // this check is only for backward-compatibility to support an array in localStorage
            if (mapString.startsWith("[")) {
                val jsArray = JSON.parse<Array<EntityJS>>(mapString)
                jsArray.map { it.id._id!! to fromJson(it as JS) }
            } else {
                val json = JSON.parse<Json>(mapString)
                json.keys.map { it to fromJson(json[it] as JS) }
            }
        } catch (t: Throwable) {
            console.info("error parsing json: $mapString")
            console.error(t)
            null
        }
    }?.toMap()
}

val Json.keys: Array<String> get() = js("Object").keys(this) as Array<String>
