@file:[JsModule("firebase/app") JsQualifier("auth.Auth") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
package firebase.auth

import kotlin.js.*

external object Persistence {
    var LOCAL: Persistence
    var NONE: Persistence
    var SESSION: Persistence
}