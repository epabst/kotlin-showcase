@file:[JsModule("firebase/functions") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused", "DEPRECATION")
package firebase.functions

import firebase.auth.Error
import kotlin.js.*

external interface HttpsCallableResult {
    var data: Any
}
external interface HttpsCallable {
    @nativeInvoke
    operator fun invoke(data: Any? = definedExternally /* null */): Promise<HttpsCallableResult>
}
external interface HttpsCallableOptions {
    var timeout: Number? get() = definedExternally; set(value) = definedExternally
}
external open class Functions {
    open fun useFunctionsEmulator(url: String)
    open fun httpsCallable(name: String, options: HttpsCallableOptions? = definedExternally /* null */): HttpsCallable
}
external interface HttpsError : Error {
    override var code: dynamic /* 'ok' | 'cancelled' | 'unknown' | 'invalid-argument' | 'deadline-exceeded' | 'not-found' | 'already-exists' | 'permission-denied' | 'resource-exhausted' | 'failed-precondition' | 'aborted' | 'out-of-range' | 'unimplemented' | 'internal' | 'unavailable' | 'data-loss' | 'unauthenticated' */
    var details: Any? get() = definedExternally; set(value) = definedExternally
}