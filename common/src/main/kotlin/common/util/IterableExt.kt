package common.util

/**
 * Enhancements to [Iterable].
 * @author Eric Pabst (epabst@gmail.com)
 */
fun <T> Iterable<T>.nonEmpty(): Boolean = iterator().hasNext()

fun <T> Iterable<T>.isEmpty(): Boolean = !nonEmpty()

fun <T> Sequence<T>.nonEmpty(): Boolean = iterator().hasNext()

fun <T> Sequence<T>.isEmpty(): Boolean = !nonEmpty()

fun <T : Comparable<T>> max(item1: T, item2: T): T {
    if (item1 >= item2) {
        return item1
    } else {
        return item2
    }
}
