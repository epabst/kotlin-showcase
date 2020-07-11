@file:Suppress("unused")

package extensions.firebase

import firebase.Unsubscribe
import firebase.app.User
import firebase.auth.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Promise

var anonymousUser: User? = null

fun Auth.signInAnonymouslyWithReuse(): Promise<Unit> {
    return if (anonymousUser != null) {
        console.log("updateCurrentUser(${anonymousUser?.uid})")
        updateCurrentUser(anonymousUser)
    } else {
        signInAnonymously().then {
            if (it.user?.isAnonymous == true) {
                console.log("setting anonymousUser=${it.user?.uid}")
                anonymousUser = it.user
            }
        }
    }
}

fun Auth.onUserChanged(onUserChanged: (oldUser: User?, newUser: User?) -> Unit): Unsubscribe {
    var oldUser: User? = null
    return onAuthStateChanged({ newUser ->
        onUserChanged(oldUser, newUser)
        oldUser = newUser
        Unit
    })
}

suspend fun Auth.waitForUser(): User {
    val user1 = currentUser
    if (user1 != null) return user1
    lateinit var listener: () -> Any
    val user = suspendCoroutine<User> { continuation ->
        listener = onUserChanged { _, newUser ->
            if (newUser != null) {
                continuation.resume(newUser)
            }
        }
    }
    listener.invoke()
    return user
}

enum class ProviderType(
    val imageUrl: String,
    private val authProviderSource: () -> AuthProvider,
    val providerId: String
) {
    Google("img/auth_service_google.svg", { GoogleAuthProvider() }, GoogleAuthProvider.PROVIDER_ID),
    Facebook("img/auth_service_facebook.svg", { FacebookAuthProvider() }, FacebookAuthProvider.PROVIDER_ID),
    Github("img/auth_service_github.svg", { GithubAuthProvider() }, GithubAuthProvider.PROVIDER_ID),
    Phone("img/auth_service_phone.svg", { PhoneAuthProvider() }, PhoneAuthProvider.PROVIDER_ID),
    Email("img/auth_service_email.svg", { EmailAuthProvider() }, EmailAuthProvider.PROVIDER_ID),
    Twitter("img/auth_service_twitter.svg", { TwitterAuthProvider() }, TwitterAuthProvider.PROVIDER_ID);

    val authProvider: AuthProvider by kotlin.lazy { authProviderSource() }

    companion object {
        fun forProviderIdOrNull(providerId: String): ProviderType? {
            return values().find { it.providerId == providerId }
        }

        fun forProviderId(providerId: String): ProviderType {
            return forProviderIdOrNull(providerId) ?: error("Unsupported provider: $providerId")
        }
    }
}

fun User.hasProvider(provider: AuthProvider): Boolean {
    return providerData.any { it?.providerId == provider.providerId }
}

val User.anyPhotoURL: String? get() = photoURL ?: providerData.map { it?.photoURL }.firstOrNull { it != null }
val User.anyDisplayName: String?
    get() = displayName ?: providerData.map { it?.displayName }.firstOrNull { it != null }
val User.anyEmail: String? get() = email ?: providerData.map { it?.email }.firstOrNull { it != null }
