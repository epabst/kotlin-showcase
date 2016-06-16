package client

import QUnit.module
import QUnit.test

/**
 * A QUnit test.  To run this, build this module and open runner.html in a browser.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/10/16
 * Time: 10:33 PM
 */

fun main(args: Array<String>) {
    module("Hello") {
        test("hello test") {
            val t: Any = "1"
            t.shouldBe("1")
        }

        test("hello test") {
            1.shouldBe(1)
        }

        test("HelloWorld test") {
            HelloWorld.message.shouldBe("Hello World")
        }
    }
}
