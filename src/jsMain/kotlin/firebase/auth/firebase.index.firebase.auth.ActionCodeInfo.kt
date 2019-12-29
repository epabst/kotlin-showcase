@file:[JsModule("firebase/auth") JsNonModule]
@file:JsQualifier("ActionCodeInfo")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
package firebase.auth

import kotlin.js.*

external object Operation {
    var EMAIL_SIGNIN: Operation
    var PASSWORD_RESET: Operation
    var RECOVER_EMAIL: Operation
    var VERIFY_EMAIL: Operation
}