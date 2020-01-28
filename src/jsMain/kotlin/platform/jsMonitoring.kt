package platform

import kotlin.browser.window

fun handleError(message: String?, internalDetail: Any) {
    logError(message, internalDetail)
    window.alert(message ?: "Failed to complete operation")
}

actual fun handleError(throwable: Throwable) {
    handleError(throwable.message, throwable)
}

fun logError(throwable: Throwable) {
    logError(throwable.message, throwable)
}

fun logError(message: String?, internalDetail: Any) {
    console.warn("ERROR: $currentContext: ", message ?: "Failed to complete operation", internalDetail)
}
