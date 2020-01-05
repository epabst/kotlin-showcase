package util

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.fail

/**
 * Kotlin extensions to QUnit.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 5:59 AM
 */
fun <T> T.mustBe(expected: T) {
    assertEquals(expected, this)
}

fun <T> T.mustNotBe(expected: T) {
    assertNotEquals(expected, this)
}

fun String?.mustContain(expectedSubstring: String) {
    if (this == null || !contains(expectedSubstring)) {
        fail("expected string containing '$expectedSubstring' but actual was '${this}'")
    }
}

fun String?.mustContainInOrder(vararg expectedSubstrings: String) {
    expectedSubstrings.forEach {
        if (this == null || !contains(it)) {
            fail("expected string containing '$it' but actual was '${this}'")
        }
    }
    val indices = expectedSubstrings.map { this?.indexOf(it) }.toList()
    indices.mustBe(indices.sortedBy { it })
}

fun String?.mustNotContain(unexpectedSubstring: String) {
    if (this == null || contains(unexpectedSubstring)) {
        fail("expected string not containing '$unexpectedSubstring' but actual was '${this}'")
    }
}

fun <T> Collection<T>.mustContain(expectedItem: T) {
    if (!contains(expectedItem)) {
        fail("expected collection containing '$expectedItem' but actual was '${this}'")
    }
}

fun interceptAny(block: () -> Unit): Exception {
    try {
        block()
        fail("expected exception")
    } catch (exception: Exception) {
        exception.mustNotBe(null)
        return exception
    }
}

expect fun <T> runTest(block: suspend () -> T)
