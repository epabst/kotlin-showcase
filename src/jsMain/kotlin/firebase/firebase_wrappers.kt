/**
 * These are only here to force requiring "firebase/<something>" since there are no top-level functions or objects.
 */
package firebase

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
