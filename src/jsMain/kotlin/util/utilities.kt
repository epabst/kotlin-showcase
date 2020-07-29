/**
 * Utilities.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/3/17
 * Time: 9:47 AM
 */
package util

import kotlinx.browser.window

fun String?.emptyToNull(): String? {
    return if (this == null || this.isEmpty()) null else this
}

fun Double?.fromNaNtoNull(): Double? = if (this == null || isNaN()) null else this

fun <T> biasing(selector: (T) -> Boolean): Comparator<T> {
    return compareBy { !selector(it) }
}

fun <T> Comparator<T>.thenBiasing(selector: (T) -> Boolean): Comparator<T> {
    return thenBy { !selector(it) }
}

private val whenStableVars = mutableListOf<Any>()

fun <V : Any,I> whenStable(mutableVariable: V, toImmutable: (V) -> I, callback: (I) -> Unit) {
    var priorValue2 = toImmutable.invoke(mutableVariable)

    fun invokeCallbackWhenStable() {
        window.requestAnimationFrame {
            val newValue = toImmutable.invoke(mutableVariable)
            if (newValue == priorValue2) {
                whenStableVars.removeAll { it === mutableVariable }
                callback.invoke(newValue)
            } else {
                priorValue2 = newValue
                invokeCallbackWhenStable()
            }
        }
    }

    if (whenStableVars.none { it === mutableVariable }) {
        whenStableVars.add(mutableVariable)
        invokeCallbackWhenStable()
    }
}
