@file:JsModule("firebase/app")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
package firebase.app

import firebase.CompleteFn
import firebase.ErrorFn
import firebase.NextFn
import firebase.analytics.Analytics
import firebase.auth.*
import firebase.database.Database
import firebase.firestore.Firestore
import firebase.functions.Functions
import firebase.messaging.Messaging
import firebase.performance.Performance
import firebase.remoteConfig.RemoteConfig
import firebase.storage.Storage
import kotlin.js.*

external interface FirebaseError {
    var code: String
    var message: String
    var name: String
    var stack: String? get() = definedExternally; set(value) = definedExternally
}
external interface Observer<T, E> {
    var next: NextFn<T>
    var error: ErrorFn<E>
    var complete: CompleteFn
}
external var SDK_VERSION: String
external fun registerVersion(library: String, version: String, variant: String? = definedExternally /* null */)
external interface Profile {
    var displayName: String? get() = definedExternally; set(value) = definedExternally
    var photoURL: String? get() = definedExternally; set(value) = definedExternally
}
external interface User : UserInfo {
    fun delete(): Promise<Unit>
    var emailVerified: Boolean
    fun getIdTokenResult(forceRefresh: Boolean? = definedExternally /* null */): Promise<IdTokenResult>
    fun getIdToken(forceRefresh: Boolean? = definedExternally /* null */): Promise<String>
    var isAnonymous: Boolean
    fun linkAndRetrieveDataWithCredential(credential: AuthCredential): Promise<UserCredential>
    fun linkWithCredential(credential: AuthCredential): Promise<UserCredential>
    fun linkWithPhoneNumber(phoneNumber: String, applicationVerifier: ApplicationVerifier): Promise<ConfirmationResult>
    fun linkWithPopup(provider: AuthProvider): Promise<UserCredential>
    fun linkWithRedirect(provider: AuthProvider): Promise<Unit>
    var metadata: UserMetadata
    override var phoneNumber: String?
    var providerData: Array<UserInfo?>
    fun reauthenticateAndRetrieveDataWithCredential(credential: AuthCredential): Promise<UserCredential>
    fun reauthenticateWithCredential(credential: AuthCredential): Promise<UserCredential>
    fun reauthenticateWithPhoneNumber(phoneNumber: String, applicationVerifier: ApplicationVerifier): Promise<ConfirmationResult>
    fun reauthenticateWithPopup(provider: AuthProvider): Promise<UserCredential>
    fun reauthenticateWithRedirect(provider: AuthProvider): Promise<Unit>
    var refreshToken: String
    fun reload(): Promise<Unit>
    fun sendEmailVerification(actionCodeSettings: ActionCodeSettings? = definedExternally /* null */): Promise<Unit>
    var tenantId: String?
    fun toJSON(): Any
    fun unlink(providerId: String): Promise<User>
    fun updateEmail(newEmail: String): Promise<Unit>
    fun updatePassword(newPassword: String): Promise<Unit>
    fun updatePhoneNumber(phoneCredential: AuthCredential): Promise<Unit>
    fun updateProfile(profile: Profile): Promise<Unit>
}
external interface UserInfo {
    var displayName: String?
    var email: String?
    var phoneNumber: String?
    var photoURL: String?
    var providerId: String
    var uid: String
}
external fun app(name: String? = definedExternally /* null */): App
external var apps: Array<App>
external fun auth(app: App? = definedExternally /* null */): Auth
external fun database(app: App? = definedExternally /* null */): Database
external fun initializeApp(options: Any, name: String? = definedExternally /* null */): App
external fun messaging(app: App? = definedExternally /* null */): Messaging
external fun storage(app: App? = definedExternally /* null */): Storage
external fun firestore(app: App? = definedExternally /* null */): Firestore
external fun functions(app: App? = definedExternally /* null */): Functions
external fun performance(app: App? = definedExternally /* null */): Performance
external fun remoteConfig(app: App? = definedExternally /* null */): RemoteConfig
external fun analytics(app: App? = definedExternally /* null */): Analytics
