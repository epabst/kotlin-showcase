package QUnit

/**
 * The QUnit API.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 5:48 AM
 */
val moduleStack = ArrayList<String>()

fun module(name: String, nested: () -> Unit) {
    //todo figure out why the nested function can't be passed in to QUnit natively so that nested modules show up right
    // without having to emulate it here.
    moduleStack.add(name)
    QUnit.qUnitModule(moduleStack.joinToString(" "))
    nested()
    moduleStack.removeAt(moduleStack.size - 1)
    QUnit.qUnitModule(moduleStack.joinToString(" "))
}

external interface QUnitX {
    @JsName("module") fun qUnitModule(name: String)
    fun test(name: String, nested: () -> Unit)
    val assert: Assert
}

@JsName("QUnit") external val QUnit: QUnitX = definedExternally

external interface Assert {
    fun <T> equal(actual: T, expected: T): Unit
    fun <T> notEqual(actual: T, unexpected: T): Unit
    fun <T> propEqual(actual: T, expected: T): Unit
    fun <T> notPropEqual(actual: T, unexpected: T): Unit
    fun ok(actual: Boolean): Unit
    fun notOk(actual: Boolean): Unit
    fun throws(f: () -> Any, expectedException: Throwable, message: String)
}
