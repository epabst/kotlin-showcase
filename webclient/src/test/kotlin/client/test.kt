package client

import kotlin.properties.Delegates

/**
 * A QUnit test.  To run this, build this module and open runner.html in a browser.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/10/16
 * Time: 10:33 PM
 */

@native("QUnit") val qunit: dynamic by Delegates.notNull()
val assert = qunit.assert

fun main(args: Array<String>) {

    qunit.test( "hello test")  {
        val t: Any = "1"
        assert.ok("1" == t, "1 should equal Any 1")
    }

    qunit.test( "hello test")  {
        assert.equal(1, 1, "1 should equal 1")
    }

    qunit.test( "HelloWorld test")  {
        assert.equal(HelloWorld.message, "Hello World")
    }
}
