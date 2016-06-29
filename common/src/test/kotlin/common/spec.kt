package common

import kotlin.test.assertEquals

/**
 * Kotlin extensions to QUnit.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 5:59 AM
 */
fun <T> T.mustBe(expected: T) {
    assertEquals(expected, this)
}
