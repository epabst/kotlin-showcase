@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("firebase.app")
package firebase.app

import kotlin.js.*

external interface App {
    fun auth(): firebase.auth.Auth
    fun database(): firebase.database.Database
    fun delete(): firebase.Promise<Any>
    var name: String
    var options: Any
    fun storage(): firebase.storage.Storage
}
