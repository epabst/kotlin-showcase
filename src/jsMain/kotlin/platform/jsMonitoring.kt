package platform

import kotlin.browser.window

fun showUserExpectedError(message: String) {
    console.warn(message)
    window.alert(message)
}

actual fun handleError(throwable: Throwable) {
    console.error("ERROR: " + currentContext + ": ", throwable)
    window.alert(currentContext + ": " + throwable.toString())
}
