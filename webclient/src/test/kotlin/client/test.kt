package client

import common.Hello

/**
 * A QUnit test.  To run this, build this module and open runner.html in a browser.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/10/16
 * Time: 10:33 PM
 */

fun main(args: Array<String>) {
    describe("mustBe") {
        it("must handle strings") {
            val t: Any = "1"
            t.mustBe("1")
        }

        it("must handle numbers") {
            1.mustBe(1)
        }

    }

    describe("Hello") {
        it("must be able to say Hello World") {
            Hello.hello().mustBe("Hello World")
        }
    }
}
