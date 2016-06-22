package common

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

/**
 * A test for [Hello].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/21/16
 * Time: 11:28 PM
 */
class HelloTest : Spek({
    it("should say hello using a name") {
        assertEquals("Hello Bob", Hello.hello("Bob"))
    }

    it("should say hello to the world by default") {
        assertEquals("Hello World", Hello.hello())
    }
})
