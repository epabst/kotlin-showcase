package client

import QUnit.module
import QUnit.test

/**
 * Kotlin extensions to QUnit.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 5:59 AM
 */
fun <T> T.mustBe(expected: T) {
    QUnit.assert.equal(this.toString(), expected.toString())
}

fun it(behavior: String, f: () -> Unit) {
    test(behavior, f)
}

fun describe(name: String, f: () -> Unit) {
    module(name, f)
}
