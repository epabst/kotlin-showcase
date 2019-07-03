@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:[JsModule("firebase/auth") JsNonModule]
package firebase.auth

import firebase.User
import kotlin.js.*

external interface ActionCodeInfo
external interface Auth {
    var app: firebase.app.App
    fun applyActionCode(code: String): firebase.Promise<Any>
    fun checkActionCode(code: String): firebase.Promise<Any>
    fun confirmPasswordReset(code: String, newPassword: String): firebase.Promise<Any>
    fun createUserWithEmailAndPassword(email: String, password: String): firebase.Promise<Any>
    var currentUser: firebase.User?
    fun fetchProvidersForEmail(email: String): firebase.Promise<Any>
    fun getRedirectResult(): firebase.Promise<Any>
    fun onAuthStateChanged(nextOrObserver: (User?) -> Any, opt_error: ((a: firebase.auth.Error) -> Any)? = definedExternally /* null */, opt_completed: (() -> Any)? = definedExternally /* null */): () -> Any
    fun onAuthStateChanged(nextOrObserver: Any = definedExternally, opt_error: ((a: firebase.auth.Error) -> Any)? = definedExternally /* null */, opt_completed: (() -> Any)? = definedExternally /* null */): () -> Any
    fun sendPasswordResetEmail(email: String): firebase.Promise<Any>
    fun signInAnonymously(): firebase.Promise<IUserCredentialPair>
    fun signInWithCredential(credential: firebase.auth.AuthCredential): firebase.Promise<IUserCredentialPair>
    fun signInWithCustomToken(token: String): firebase.Promise<IUserCredentialPair>
    fun signInWithEmailAndPassword(email: String, password: String): firebase.Promise<IUserCredentialPair>
    fun signInWithPopup(provider: firebase.auth.AuthProvider): firebase.Promise<IUserCredentialPair>
    fun signInWithRedirect(provider: firebase.auth.AuthProvider): firebase.Promise<IUserCredentialPair>
    fun signOut(): firebase.Promise<Any>
    fun useDeviceLanguage()
    fun verifyPasswordResetCode(code: String): firebase.Promise<Any>
}
external interface AuthCredential {
    var provider: String
    val accessToken: String?
}
external interface AuthProvider {
    var providerId: String
}
external open class EmailAuthProvider : EmailAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String = definedExternally
    }
}
external open class EmailAuthProvider_Instance : firebase.auth.AuthProvider {
    open fun credential(email: String, password: String): firebase.auth.AuthCredential = definedExternally
    override var providerId: String = definedExternally
}
external interface Error {
    var code: String
    var message: String
    val credential: AuthCredential?
}
external open class FacebookAuthProvider : FacebookAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String = definedExternally
    }
}
external open class FacebookAuthProvider_Instance : firebase.auth.AuthProvider {
    open fun addScope(scope: String): Any = definedExternally
    open fun credential(token: String): firebase.auth.AuthCredential = definedExternally
    override var providerId: String = definedExternally
}
external open class GithubAuthProvider : GithubAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String = definedExternally
    }
}
external open class GithubAuthProvider_Instance : firebase.auth.AuthProvider {
    open fun addScope(scope: String): Any = definedExternally
    open fun credential(token: String): firebase.auth.AuthCredential = definedExternally
    override var providerId: String = definedExternally
}
external open class GoogleAuthProvider : GoogleAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String = definedExternally
    }
}
external open class GoogleAuthProvider_Instance : firebase.auth.AuthProvider {
    open fun addScope(scope: String): Any = definedExternally
    open fun credential(idToken: String? = definedExternally /* null */, accessToken: String? = definedExternally /* null */): firebase.auth.AuthCredential = definedExternally
    override var providerId: String = definedExternally
}
external open class TwitterAuthProvider : TwitterAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String = definedExternally
    }
}
external open class TwitterAuthProvider_Instance : firebase.auth.AuthProvider {
    open fun credential(token: String, secret: String): firebase.auth.AuthCredential = definedExternally
    override var providerId: String = definedExternally
}
external interface IUserCredentialPair {
    val credential: firebase.auth.AuthCredential?
    var user: firebase.User?
}
