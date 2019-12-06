package platform

import kotlin.browser.window

fun showUserExpectedError(message: String) {
    console.warn(message)
    window.alert(message)
}

fun handleError(throwable: Throwable) {
    console.error("ERROR: " + currentContext + ": ", throwable)
    window.alert(currentContext + ": " + throwable.toString())
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
