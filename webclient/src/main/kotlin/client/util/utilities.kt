/**
 * Utilities.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/3/17
 * Time: 9:47 AM
 */
package client.util

import common.util.ID
import common.util.WithID
import net.yested.core.properties.*
import kotlin.browser.window

/**
 * Map a List to another list, reusing the items from the time before whenever equal to the function result.
 * The function is still run each time, but the result is quickly discarded if it has a match in the prior result list.
 * @return a mutable Property that defaults to a List having the results of the transform.
 * @see mapAsDefault for more information about the return type
 */
fun <T,OUT> ReadOnlyProperty<List<T>?>.mapEachReusing(transform: (T)->OUT): Property<List<OUT>?> {
    return collectAsDefault { oldResult, list ->
        val oldResultMap = oldResult?.groupBy { it }
        list?.map(transform)?.map { oldResultMap?.get(it)?.first() ?: it }
    }
}

/**
 * Map an Entity List to another list, reusing the resulting items from the time before for the same ID.
 * @return a mutable Property that defaults to a List having the results of the transform.
 * @see mapAsDefault for more information about the return type
 */
fun <ENTITY : WithID<ENTITY>,OUT> ReadOnlyProperty<List<ENTITY>?>.mapEachReusingByID(transform: (ENTITY)->OUT): Property<List<OUT>?> {
    val mapByID: MutableMap<ID<ENTITY>,OUT> = mutableMapOf()
    return mapAsDefault { it?.map { item -> mapByID.mapWithCacheByID(item, transform) } }
}

fun <ENTITY: WithID<ENTITY>, OUT> MutableMap<ID<ENTITY>,OUT>.mapWithCacheByID(entity: ENTITY, transform: (ENTITY) -> OUT): OUT {
    return entity.getID()?.let { id -> getOrPut(id) { transform(entity) } } ?: transform(entity)
}

fun <T> Property<Set<T>>.toggleItem(item: T) {
    modify {
        if (it.contains(item)) {
            it - item
        } else {
            it + item
        }
    }
}

fun <T> ReadOnlyProperty<Collection<T>>.contains(item: T?): ReadOnlyProperty<Boolean> {
    return map { it.contains(item) }
}

fun <T> ReadOnlyProperty<Collection<T>>.contains(itemProperty: ReadOnlyProperty<T?>): ReadOnlyProperty<Boolean> {
    return mapWith(itemProperty) { collection, item -> collection.contains(item) }
}

fun String?.emptyToNull(): String? {
    return if (this == null || this.isEmpty()) null else this
}

fun <T> biasing(selector: (T) -> Boolean): Comparator<T> {
    return compareBy { !selector(it) }
}

fun <T> Comparator<T>.thenBiasing(selector: (T) -> Boolean): Comparator<T> {
    return thenBy { !selector(it) }
}

fun <T> whenStable(changingValue: () -> T, callback: (T) -> Unit) {
    fun whenStable(changingValue: () -> T, priorValue: T, callback: (T) -> Any) {
        window.requestAnimationFrame {
            val newValue = changingValue.invoke()
            if (newValue == priorValue) {
                callback.invoke(newValue)
            } else {
                whenStable(changingValue, newValue, callback)
            }
        }
    }
    whenStable(changingValue, changingValue.invoke(), callback)
}
