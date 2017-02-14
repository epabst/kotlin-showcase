package client

import kotlin.browser.window

/**
 * A set of utility classes for use in Javascript.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 12/7/16
 * Time: 3:37 PM
 */
interface History {
    val length: kotlin.Int
    fun back(): kotlin.Unit
    fun forward(): kotlin.Unit
    fun go(delta: kotlin.Int): kotlin.Unit
}

object BrowserHistory : History {
    override val length: Int get() = window.history.length
    override fun back() { window.history.back() }
    override fun forward() { window.history.forward() }
    override fun go(delta: Int) { window.history.go(delta) }
}

fun History.backToHash(hashUrl: String?) {
    if (hashUrl != null) {
        UI.windowLocation.hash = hashUrl
    } else {
        back()
    }
}
