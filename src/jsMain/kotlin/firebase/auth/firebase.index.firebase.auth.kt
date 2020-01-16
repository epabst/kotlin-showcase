@file:[JsModule("firebase/app") JsQualifier("auth") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused", "ClassName")
package firebase.auth

import firebase.Unsubscribe
import firebase.app.App
import firebase.app.Observer
import firebase.app.User
import kotlin.js.*
import kotlin.js.Json

open external class ActionCodeURL {
    open var apiKey: String
    open var code: String
    open var continueUrl: String?
    open var languageCode: String?
    open var operation: Operation
    open var tenantId: String?
    companion object {
        fun parseLink(link: String): ActionCodeURL?
    }
}
external interface `T$6` {
    var email: String? get() = definedExternally; set(value) = definedExternally
    var fromEmail: String? get() = definedExternally; set(value) = definedExternally
}
external interface ActionCodeInfo {
    var data: `T$6`
    var operation: String
}
external interface `T$7` {
    var installApp: Boolean? get() = definedExternally; set(value) = definedExternally
    var minimumVersion: String? get() = definedExternally; set(value) = definedExternally
    var packageName: String
}
external interface `T$8` {
    var bundleId: String
}
external interface ActionCodeSettings {
    var android: `T$7`? get() = definedExternally; set(value) = definedExternally
    var handleCodeInApp: Boolean? get() = definedExternally; set(value) = definedExternally
    var iOS: `T$8`? get() = definedExternally; set(value) = definedExternally
    var url: String
    var dynamicLinkDomain: String? get() = definedExternally; set(value) = definedExternally
}
external interface AdditionalUserInfo {
    var isNewUser: Boolean
    var profile: Any?
    var providerId: String
    var username: String? get() = definedExternally; set(value) = definedExternally
}
external interface ApplicationVerifier {
    var type: String
    fun verify(): Promise<String>
}
external interface AuthSettings {
    var appVerificationDisabledForTesting: Boolean
}
external interface Auth {
    var app: App
    fun applyActionCode(code: String): Promise<Unit>
    fun checkActionCode(code: String): Promise<ActionCodeInfo>
    fun confirmPasswordReset(code: String, newPassword: String): Promise<Unit>
    fun createUserWithEmailAndPassword(email: String, password: String): Promise<UserCredential>
    var currentUser: User?
    fun fetchSignInMethodsForEmail(email: String): Promise<Array<String>>
    fun isSignInWithEmailLink(emailLink: String): Boolean
    fun getRedirectResult(): Promise<UserCredential>
    var languageCode: String?
    var settings: AuthSettings
    fun onAuthStateChanged(nextOrObserver: Observer<Any, Error>, error: ((a: Error) -> Any)? = definedExternally /* null */, completed: Unsubscribe? = definedExternally /* null */): Unsubscribe
    fun onAuthStateChanged(nextOrObserver: (a: User?) -> Any, error: ((a: Error) -> Any)? = definedExternally /* null */, completed: Unsubscribe? = definedExternally /* null */): Unsubscribe
    fun onIdTokenChanged(nextOrObserver: Observer<Any,Error>, error: ((a: Error) -> Any)? = definedExternally /* null */, completed: Unsubscribe? = definedExternally /* null */): Unsubscribe
    fun onIdTokenChanged(nextOrObserver: (a: User?) -> Any, error: ((a: Error) -> Any)? = definedExternally /* null */, completed: Unsubscribe? = definedExternally /* null */): Unsubscribe
    fun sendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings): Promise<Unit>
    fun sendPasswordResetEmail(email: String, actionCodeSettings: ActionCodeSettings? = definedExternally /* null */): Promise<Unit>
    fun setPersistence(persistence: Persistence): Promise<Unit>
    fun signInAndRetrieveDataWithCredential(credential: AuthCredential): Promise<UserCredential>
    fun signInAnonymously(): Promise<UserCredential>
    fun signInWithCredential(credential: AuthCredential): Promise<UserCredential>
    fun signInWithCustomToken(token: String): Promise<UserCredential>
    fun signInWithEmailAndPassword(email: String, password: String): Promise<UserCredential>
    fun signInWithPhoneNumber(phoneNumber: String, applicationVerifier: ApplicationVerifier): Promise<ConfirmationResult>
    fun signInWithEmailLink(email: String, emailLink: String? = definedExternally /* null */): Promise<UserCredential>
    fun signInWithPopup(provider: AuthProvider): Promise<UserCredential>
    fun signInWithRedirect(provider: AuthProvider): Promise<Unit>
    fun signOut(): Promise<Unit>
    var tenantId: String?
    fun updateCurrentUser(user: User?): Promise<Unit>
    fun useDeviceLanguage()
    fun verifyPasswordResetCode(code: String): Promise<String>
}
open external class AuthCredential {
    open var providerId: String
    open var signInMethod: String
    open fun toJSON(): Any
    companion object {
        fun fromJSON(json: Any): AuthCredential?
        fun fromJSON(json: String): AuthCredential?
    }
}
open external class OAuthCredential : AuthCredential {
    open var idToken: String
    open var accessToken: String
    open var secret: String
}
external interface AuthProvider {
    var providerId: String
}
abstract external class AuthProviderWithCustomParameters : AuthProvider {
    override var providerId: String = definedExternally
    fun setCustomParameters(customOAuthParameters: Any): AuthProvider = definedExternally
}
abstract external class AuthProviderWithScopeAndCustomParameters : AuthProviderWithCustomParameters {
    fun addScope(scope: String): AuthProvider = definedExternally
}
external interface ConfirmationResult {
    fun confirm(verificationCode: String): Promise<UserCredential>
    var verificationId: String
}
open external class EmailAuthProvider : EmailAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String
        var EMAIL_PASSWORD_SIGN_IN_METHOD: String
        var EMAIL_LINK_SIGN_IN_METHOD: String
        fun credential(email: String, password: String): AuthCredential
        fun credentialWithLink(email: String, emailLink: String): AuthCredential
    }
}
open external class EmailAuthProvider_Instance : AuthProvider {
    override var providerId: String
}
external interface Error {
    var code: String
    var message: String
}
external interface AuthError : Error {
    var credential: AuthCredential? get() = definedExternally; set(value) = definedExternally
    var email: String? get() = definedExternally; set(value) = definedExternally
    var phoneNumber: String? get() = definedExternally; set(value) = definedExternally
    var tenantId: String? get() = definedExternally; set(value) = definedExternally
}
open external class FacebookAuthProvider : FacebookAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String
        var FACEBOOK_SIGN_IN_METHOD: String
        fun credential(token: String): OAuthCredential
    }
}
open external class FacebookAuthProvider_Instance : AuthProviderWithScopeAndCustomParameters
open external class GithubAuthProvider : GithubAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String
        var GITHUB_SIGN_IN_METHOD: String
        fun credential(token: String): OAuthCredential
    }
}
open external class GithubAuthProvider_Instance : AuthProviderWithScopeAndCustomParameters
open external class GoogleAuthProvider : GoogleAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String
        var GOOGLE_SIGN_IN_METHOD: String
        fun credential(idToken: String? = definedExternally /* null */, accessToken: String? = definedExternally /* null */): OAuthCredential
    }
}
open external class GoogleAuthProvider_Instance : AuthProviderWithScopeAndCustomParameters
open external class OAuthProvider(providerId: String) : AuthProviderWithScopeAndCustomParameters {
    override var providerId: String
    open fun credential(optionsOrIdToken: OAuthCredentialOptions, accessToken: String? = definedExternally /* null */): OAuthCredential
    open fun credential(optionsOrIdToken: String, accessToken: String? = definedExternally /* null */): OAuthCredential
    open fun credential(optionsOrIdToken: Nothing?, accessToken: String? = definedExternally /* null */): OAuthCredential
}
open external class SAMLAuthProvider : AuthProvider {
    override var providerId: String
}
external interface IdTokenResult {
    var token: String
    var expirationTime: String
    var authTime: String
    var issuedAtTime: String
    var signInProvider: String?
    var claims: Json
}
external interface OAuthCredentialOptions {
    var idToken: String? get() = definedExternally; set(value) = definedExternally
    var accessToken: String? get() = definedExternally; set(value) = definedExternally
    var rawNonce: String? get() = definedExternally; set(value) = definedExternally
}
open external class PhoneAuthProvider : PhoneAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String
        var PHONE_SIGN_IN_METHOD: String
        fun credential(verificationId: String, verificationCode: String): AuthCredential
    }
}
open external class PhoneAuthProvider_Instance(auth: Auth? = definedExternally /* null */) : AuthProvider {
    override var providerId: String
    open fun verifyPhoneNumber(phoneNumber: String, applicationVerifier: ApplicationVerifier): Promise<String>
}
open external class RecaptchaVerifier : RecaptchaVerifier_Instance
open external class RecaptchaVerifier_Instance : ApplicationVerifier {
    constructor(container: Any, parameters: Any?, app: App?)
    constructor(container: String, parameters: Any?, app: App?)
    open fun clear()
    open fun render(): Promise<Number>
    override var type: String
    override fun verify(): Promise<String>
}
open external class TwitterAuthProvider : TwitterAuthProvider_Instance {
    companion object {
        var PROVIDER_ID: String
        var TWITTER_SIGN_IN_METHOD: String
        fun credential(token: String, secret: String): OAuthCredential
    }
}
open external class TwitterAuthProvider_Instance : AuthProviderWithCustomParameters
external interface UserCredential {
    var additionalUserInfo: AdditionalUserInfo? get() = definedExternally; set(value) = definedExternally
    var credential: AuthCredential?
    var operationType: String? get() = definedExternally; set(value) = definedExternally
    var user: User?
}
external interface UserMetadata {
    var creationTime: String? get() = definedExternally; set(value) = definedExternally
    var lastSignInTime: String? get() = definedExternally; set(value) = definedExternally
}