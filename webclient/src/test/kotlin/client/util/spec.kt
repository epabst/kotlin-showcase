package client.util

import QUnit.*
import kotlin.test.fail

/**
 * Kotlin extensions to QUnit.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 5:59 AM
 */
fun <T> T.mustBe(expected: T) {
    val equal = equalTo(expected)
    if (!equal) {
        fail("expected '$expected' but actual was '$this'")
    }
    QUnit.assert.equal(equal, true)
}

fun <T> T.mustNotBe(expected: T) {
    val equal = equalTo(expected)
    if (equal) {
        fail("did not expect '$expected' but it was equal: '$this'")
    }
    QUnit.assert.notEqual(equal, true)
}

private fun <T> T.equalTo(expected: T): Boolean {
    if (this is ArrayList<*>) {
        if (expected is ArrayList<*>) {
            if (size != expected.size) {
                return false
            }
            var index = 0
            do {
                if (!this[index].equalTo(expected[index])) {
                    return false
                }
                index += 1
            } while (index < size)
            return true
        } else {
            return this.toString() == expected.toString()
        }
    } else {
        return this == expected
    }
}

fun String?.mustContain(expectedSubstring: String) {
    if (this == null || !contains(expectedSubstring)) {
        fail("expected string containing '$expectedSubstring' but actual was '${this}'")
    }
    QUnit.assert.equal(true, true)
}

fun String?.mustContainInOrder(vararg expectedSubstrings: String) {
    expectedSubstrings.forEach {
        if (this == null || !contains(it)) {
            fail("expected string containing '$it' but actual was '${this}'")
        }
    }
    val indices = expectedSubstrings.map { this?.indexOf(it) }.toList()
    indices.mustBe(indices.sortedBy { it })
    QUnit.assert.equal(true, true)
}

fun String?.mustNotContain(unexpectedSubstring: String) {
    if (this == null || contains(unexpectedSubstring)) {
        fail("expected string not containing '$unexpectedSubstring' but actual was '${this}'")
    }
    QUnit.assert.equal(true, true)
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

fun it(behavior: String, f: () -> Unit) {
    QUnit.test(behavior, f)
}

fun describe(name: String, f: () -> Unit) {
    module(name, f)
}