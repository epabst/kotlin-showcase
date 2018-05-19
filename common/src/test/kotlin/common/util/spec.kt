package common.util

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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

fun String.mustContain(expectedSubstring: String) {
    assert(contains(expectedSubstring)) {
        "expected '$this' to contain '$expectedSubstring'"
    }
}
