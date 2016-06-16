package client

/**
 * Kotlin extensions to QUnit.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 5:59 AM
 */
fun <T> T.shouldBe(expected: T) {
    QUnit.assert.equal(this.toString(), expected.toString())
}
