package common

import org.junit.Test
import kotlin.test.assertEquals

/**
 * A test for [Hello].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/21/16
 * Time: 11:28 PM
 */
class HelloTest {
    @Test
    fun hello() {
        assertEquals("Hello Bob", Hello.hello("Bob"))
    }

    @Test
    fun helloWorld() {
        assertEquals("Hello World", Hello.hello())
    }
}
