@file:[JsModule("firebase/messaging") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
package firebase.messaging

import firebase.CompleteFn
import firebase.ErrorFn
import firebase.NextFn
import firebase.Unsubscribe
import firebase.app.Observer
import kotlin.js.*
import org.w3c.workers.*

external interface Messaging {
    fun deleteToken(token: String): Promise<Boolean>
    fun getToken(): Promise<String>
    fun onMessage(nextOrObserver: NextFn<Any>, error: ErrorFn<Error>? = definedExternally /* null */, completed: CompleteFn? = definedExternally /* null */): Unsubscribe
    fun onMessage(nextOrObserver: Observer<Any,Error>, error: ErrorFn<Error>? = definedExternally /* null */, completed: CompleteFn? = definedExternally /* null */): Unsubscribe
    fun onTokenRefresh(nextOrObserver: NextFn<Any>, error: ErrorFn<Error>? = definedExternally /* null */, completed: CompleteFn? = definedExternally /* null */): Unsubscribe
    fun onTokenRefresh(nextOrObserver: Observer<Any,Error>, error: ErrorFn<Error>? = definedExternally /* null */, completed: CompleteFn? = definedExternally /* null */): Unsubscribe
    fun requestPermission(): Promise<Unit>
    fun setBackgroundMessageHandler(callback: (payload: Any) -> dynamic)
    fun useServiceWorker(registration: ServiceWorkerRegistration)
    fun usePublicVapidKey(b64PublicKey: String)
}
external fun isSupported(): Boolean