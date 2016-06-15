package common

/**
 * A sample class that is shared between Javascript and the JVM.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/21/16
 * Time: 11:26 PM
 */
object Hello {
    fun hello(name: String = "World"): String = "Hello ${name}"
}
