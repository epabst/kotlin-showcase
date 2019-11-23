package client.util

import common.util.inContext
import kotlin.browser.window

fun showUserExpectedError(message: String) {
    console.warn(message)
    window.alert(message)
}

fun handleError(throwable: Throwable) {
    console.error("ERROR: " + common.util.currentContext + ": ", throwable)
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