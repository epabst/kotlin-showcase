package client.util

import kotlin.browser.window

fun handleError(throwable: Throwable) {
    window.alert(common.util.currentContext + ": " + throwable.toString())
}
