package common

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * A test for [Hello].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/21/16
 * Time: 11:28 PM
 */
class HelloTest {
    @Test
    fun itShouldSayHelloUsingAName() {
        assertEquals("Hello Bob", Hello.hello("Bob"))
    }

    @Test
    fun itShouldSayHelloToTheWorldByDefault() {
        assertEquals("Hello World", Hello.hello())
    }
}
