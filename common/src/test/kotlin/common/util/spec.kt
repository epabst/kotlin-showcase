package common.util

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
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
    MatcherAssert.assertThat(this, CoreMatchers.containsString(expectedSubstring))
}

fun <E : Exception> intercept(block: () -> Unit): E {
    try {
        block()
        fail("expected exception")
    } catch (exception: E) {
        return exception
    }
}
