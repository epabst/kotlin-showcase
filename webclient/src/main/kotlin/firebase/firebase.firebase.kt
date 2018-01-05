@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:[JsQualifier("firebase"), JsModule("firebase")]
package firebase

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external interface FirebaseError {
    var code: String
    var message: String
    var name: String
    var stack: String
}
external open class Promise<T> : Promise_Instance<T> {
    companion object {
        fun all(values: Array<firebase.Promise<Any>>): firebase.Promise<Array<Any>> = definedExternally
        fun reject(error: Error): firebase.Promise<Any> = definedExternally
        fun <T> resolve(value: T): firebase.Promise<T> = definedExternally
    }
}
external open class Promise_Instance<T>(resolver: (a: ((a: T) -> Nothing?)? /*= null*/, b: ((a: Error) -> Nothing?)? /*= null*/) -> Any) : firebase.Thenable<Any> {
    open fun catch(onReject: ((a: Error) -> Any)? = definedExternally /* null */): firebase.Thenable<Any> = definedExternally
    open fun then(onResolve: ((a: T) -> Any)? = definedExternally /* null */, onReject: ((a: Error) -> Any)? = definedExternally /* null */): firebase.Promise<Any> = definedExternally
}
external var SDK_VERSION: String = definedExternally
external interface Thenable<T> {
    fun catch(onReject: ((a: Error) -> Any)? = definedExternally /* null */): Any
    fun then(onResolve: ((a: T) -> Any)? = definedExternally /* null */, onReject: ((a: Error) -> Any)? = definedExternally /* null */): firebase.Thenable<Any>
}
external interface `T$0` {
    var displayName: String?
    var photoURL: String?
}
external interface User : firebase.UserInfo {
    fun delete(): firebase.Promise<Any>
    var emailVerified: Boolean
    fun getToken(opt_forceRefresh: Boolean? = definedExternally /* null */): firebase.Promise<Any>
    var isAnonymous: Boolean
    fun link(credential: firebase.auth.AuthCredential): firebase.Promise<Any>
    fun linkWithPopup(provider: firebase.auth.AuthProvider): firebase.Promise<Any>
    fun linkWithRedirect(provider: firebase.auth.AuthProvider): firebase.Promise<Any>
    var providerData: Array<firebase.UserInfo?>
    fun reauthenticate(credential: firebase.auth.AuthCredential): firebase.Promise<Any>
    var refreshToken: String
    fun reload(): firebase.Promise<Any>
    fun sendEmailVerification(): firebase.Promise<Any>
    fun unlink(providerId: String): firebase.Promise<Any>
    fun updateEmail(newEmail: String): firebase.Promise<Any>
    fun updatePassword(newPassword: String): firebase.Promise<Any>
    fun updateProfile(profile: `T$0`): firebase.Promise<Any>
}
external interface UserInfo {
    var displayName: String?
    var email: String?
    var photoURL: String?
    var providerId: String
    var uid: String
}
external fun app(name: String): firebase.app.App = definedExternally
external var apps: Array<firebase.app.App?> = definedExternally
external fun auth(app: firebase.app.App? = definedExternally /* null */): firebase.auth.Auth = definedExternally
external fun database(app: firebase.app.App? = definedExternally /* null */): firebase.database.Database = definedExternally
external fun initializeApp(options: Any, name: String? = definedExternally /* null */): firebase.app.App = definedExternally
external fun storage(app: firebase.app.App? = definedExternally /* null */): firebase.storage.Storage = definedExternally
