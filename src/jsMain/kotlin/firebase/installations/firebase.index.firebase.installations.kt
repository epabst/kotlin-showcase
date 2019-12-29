@file:[JsModule("firebase/installations") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
package firebase.installations

import kotlin.js.Promise

external interface Installations {
    fun getId(): Promise<String>
    fun getToken(forceRefresh: Boolean? = definedExternally /* null */): Promise<String>
    fun delete(): Promise<Unit>
}