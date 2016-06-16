package QUnit

/**
 * The QUnit API.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 5:48 AM
 */
fun module(name: String, nested: () -> Unit) {
    //todo figure out why the nested function can't be passed in to QUnit natively so that nested modules show up right.
    qUnitModule(name)
    nested()
}

@native("QUnit.module")
private fun qUnitModule(name: String) { noImpl }

@native("QUnit.test")
fun test(name: String, nested: () -> Unit) { noImpl }

@native("QUnit.assert")
val assert: Assert

@native
interface Assert {
    fun <T> equal(actual: T, expected: T): Unit
    fun <T> notEqual(actual: T, unexpected: T): Unit
    fun <T> propEqual(actual: T, expected: T): Unit
    fun <T> notPropEqual(actual: T, unexpected: T): Unit
    fun ok(actual: Boolean): Unit
    fun notOk(actual: Boolean): Unit
    fun throws(f: () -> Any, expectedException: Throwable, message: String = "should have thrown exception")
}
