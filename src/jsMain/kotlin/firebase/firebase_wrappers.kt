/**
 * These are only here to force requiring "firebase/<something>" since there are no top-level functions or objects.
 */
package firebase

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.asPromise
import kotlin.js.Promise

@JsModule("firebase/app") @JsNonModule
external val requireApp: Nothing? = definedExternally

@JsModule("firebase/auth") @JsNonModule
external val requireAuth: Nothing? = definedExternally

@JsModule("firebase/database") @JsNonModule
external val requireDatabase: Nothing? = definedExternally

@JsModule("firebase/firestore") @JsNonModule
external val requireFirestore: Nothing? = definedExternally

@JsModule("firebase/storage") @JsNonModule
external val requireStorage: Nothing? = definedExternally

fun <T> Deferred<T>.asFirebasePromise(): firebase.Promise<T> {
    return asPromise().asFirebasePromise()
}

fun <T> Promise<T>.asFirebasePromise(): firebase.Promise<T> {
    return unsafeCast<firebase.Promise<T>>()
}
