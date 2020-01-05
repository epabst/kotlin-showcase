package util

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asPromise
import kotlinx.coroutines.async

actual fun <T> runTest(block: suspend () -> T): dynamic {
    return GlobalScope.async { block() }.asPromise()
}
