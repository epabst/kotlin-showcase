package client.util

import common.util.inContext
import kotlin.browser.window

fun handleError(throwable: Throwable) {
    window.alert(common.util.currentContext + ": " + throwable.toString())
}

fun handlingErrors(contextName: String, f: ()->Any) {
    return inContext(contextName) {
        try {
            f()
        } catch (e: Throwable) {
            handleError(e)
        }
    }
}
