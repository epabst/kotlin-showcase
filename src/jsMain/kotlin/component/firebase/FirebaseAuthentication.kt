@file:Suppress("unused")

package component.firebase

import bootstrap.*
import firebase.Unsubscribe
import firebase.app.User
import firebase.app.App
import firebase.auth.*
import firebase.requireAuth
import kotlinx.coroutines.await
import kotlinx.html.id
import kotlinx.html.title
import platform.*
import react.*
import react.dom.*
import kotlin.browser.window
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.js.json

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

interface AuthenticationLinkProps : RProps {
    var firebaseApp: App?
    var enabledProviderTypes: Array<out ProviderType>
    /** action to remove data from an old user when merging an anonymous account into a provider account */
    var removeFromOldUser: (() -> Any)?
    /** action to add data to a new user when merging an anonymous account into a provider account */
    var addToNewUser: ((Any) -> Unit)?
}

interface AuthenticationLinkState : RState {
    var user: User?
    var credentialToLink: AuthCredential?
    var providerTypeToLink: ProviderType?
    var providerTypeToPopup: ProviderType?
}

/** Provides a fully implemented link with button to sign-in, user profile image, display name, etc. */
class AuthenticationLink(props: AuthenticationLinkProps) :
    RComponent<AuthenticationLinkProps, AuthenticationLinkState>(props) {

    private var unsubscribe: Unsubscribe? = null

    override fun componentDidMount() {
        unsubscribe = props.firebaseApp?.auth()?.onUserChanged { _, newUser ->
            setState { user = newUser }
        }
    }

    override fun componentWillUnmount() {
        unsubscribe?.invoke()
    }

    override fun RBuilder.render() {
        requireAuth
        val firebaseApp = props.firebaseApp
        val enabledProviderTypes = props.enabledProviderTypes
        if (firebaseApp != null && enabledProviderTypes.firstOrNull()?.providerId != null) {
            val user = state.user
            val providerTypeToPopup = state.providerTypeToPopup
            val providerTypeToLink = state.providerTypeToLink
            val credentialToLink = state.credentialToLink
            if (providerTypeToPopup != null && providerTypeToLink != null && credentialToLink != null) {
                child(Button::class) {
                    attrs.variant = "secondary"
                    +"Link "
                    img(src = providerTypeToPopup.imageUrl, classes = "auth-icon") {}
                    +" to "
                    img(src = providerTypeToLink.imageUrl, classes = "auth-icon") {}
                    attrs.onClick = {
                        launchHandlingErrors("link auth providers") {
                            try {
                                firebaseApp.auth()
                                    .signInWithPopup(providerTypeToPopup.authProvider)
                                    .await()
                                    .user
                                    ?.linkWithCredential(credentialToLink)
                            } catch (error: Throwable) {
                                handleAuthError(error.unsafeCast<AuthError>(), user, providerTypeToPopup)
                            }
                        }
                    }
                }
            } else if (user == null || enabledProviderTypes.none { user.hasProvider(it.authProvider) }) {
                child(InputGroup::class) {
                    attrs.className = "auth"
                    child(InputGroup.Prepend::class) {
                        child(InputGroup.Text::class) {
                            +"Sign in using:"
                        }
                    }
                    child(ButtonGroup::class) {
                        enabledProviderTypes.forEach { providerType ->
                            child(Button::class) {
                                attrs.variant = "link"
                                attrs.className = "auth-button"
                                img(src = providerType.imageUrl, classes = "auth-icon") {}
                                attrs.onClick = {
                                    it.preventDefault()
                                    val provider = providerType.authProvider
                                    val onReject: (Throwable) -> Unit = { error ->
                                        handleAuthError(error.unsafeCast<AuthError>(), user, providerType)
                                    }
                                    when {
                                        user == null -> {
                                            val dataFromOldUser = props.removeFromOldUser?.invoke()
                                            firebaseApp.auth().signInWithPopup(provider)
                                                .then(onRejected = onReject, onFulfilled = {
                                                    if (dataFromOldUser != null) {
                                                        props.addToNewUser?.invoke(dataFromOldUser)
                                                    }
                                                })
                                        }
                                        user.hasProvider(provider) -> {
                                            firebaseApp.auth().signInWithPopup(provider)
                                                .then(onRejected = onReject, onFulfilled = {})
                                        }
                                        else -> {
                                            user.linkWithPopup(provider).then(onRejected = onReject, onFulfilled = {})
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (user.anyPhotoURL != null) {
                child(Dropdown::class) {
                    child(Dropdown.Toggle::class) {
                        attrs.variant = "link"
                        img(src = user.anyPhotoURL ?: "") {
                            attrs.id = "user-img"
                            attrs.title = user.anyDisplayName ?: ""
                        }
                    }
                    child(Dropdown.Menu::class) {
                        attrs.alignRight = true
                        child(Dropdown.Header::class) {
                            val providerType = user.providerType
                            if (providerType != null) {
                                img(src = providerType.imageUrl, classes = "auth-icon") {}
                                +" "
                            }
                            user.anyEmail?.let { +it }
                        }
                        child(Dropdown.Item::class) {
                            +"Sign out"
                            attrs.onClick = { firebaseApp.auth().signOut() }
                        }
                    }
                }
            }
        }
    }

    val User.providerType: ProviderType?
        get() {
            return providerData
                .mapNotNull { it?.providerId?.let { it1 -> ProviderType.forProviderIdOrNull(it1) } }
                .firstOrNull()
                ?: ProviderType.forProviderIdOrNull(providerId)
        }

    private fun handleAuthError(error: AuthError, priorUser: User?, providerType: ProviderType) {
        inContext("handleAuthError") {
            val auth = props.firebaseApp!!.auth()
            val newCredential = error.credential
            console.warn("Got error code '${error.code}'")
            when (error.code) {
                "auth/popup-closed-by-user" -> Unit //do nothing
                "auth/cancelled-popup-request" -> Unit // do nothing
                "auth/credential-already-in-use" -> {
                    if (newCredential != null) {
                        val dataFromOldUser = if (priorUser?.hasProvider(providerType.authProvider) == false) {
                            props.removeFromOldUser?.invoke()
                        } else {
                            null
                        }
                        println("priorUser.uid=${priorUser?.uid} priorUser.providerId=${priorUser?.providerId}")
                        auth.signInWithCredential(newCredential)
                            .then(onFulfilled = { newUserCredentialPair ->
                                if (newUserCredentialPair.user != null) {
                                    dataFromOldUser?.let { props.addToNewUser?.invoke(it) }
                                    priorUser?.delete()
                                    println("Deleted priorUser.uid=${priorUser?.uid} priorUser.providerId=${priorUser?.providerId}")
                                }
                            }, onRejected = {
                                //put the data back since it failed
                                dataFromOldUser?.let { props.addToNewUser?.invoke(it) } ?: Unit
                            })
                    }
                }
                "auth/account-exists-with-different-credential", "auth/email-already-in-use" -> {
                    val email = error.email
                    if (email != null && newCredential != null) {
                        auth.fetchSignInMethodsForEmail(email).then(onFulfilled = { providers ->
                            val matchingProviderId = providers.first()

                            val matchingProviderType = ProviderType.forProviderId(matchingProviderId)
                            if (matchingProviderType.authProvider is AuthProviderWithCustomParameters) {
                                matchingProviderType.authProvider.setCustomParameters(json("login_hint" to error.email))
                            }

                            auth.signInWithPopup(matchingProviderType.authProvider).then(onFulfilled = { result ->
                                result.user?.linkWithCredential(newCredential)
                            }, onRejected = { error ->
                                val authError = error.unsafeCast<AuthError>()
                                if (authError.code == "auth/popup-blocked") {
                                    setState {
                                        credentialToLink = newCredential
                                        providerTypeToLink = providerType
                                        providerTypeToPopup = matchingProviderType
                                    }
                                } else {
                                    handleAuthError(authError, priorUser, matchingProviderType)
                                }
                            })
                        }, onRejected = { error -> handleError(error) })
                    }
                }
                "auth/popup-blocked" -> window.alert("Unable to sign in since popup was blocked")
                else -> showUserExpectedError(error.message)
            }
        }
    }
}

enum class ProviderType(
    val imageUrl: String,
    val authProvider: AuthProvider,
    val providerId: String
) {
    Google("img/auth_service_google.svg", GoogleAuthProvider(), GoogleAuthProvider.PROVIDER_ID),
    Facebook("img/auth_service_facebook.svg", FacebookAuthProvider(), FacebookAuthProvider.PROVIDER_ID),
    Github("img/auth_service_github.svg", GithubAuthProvider(), GithubAuthProvider.PROVIDER_ID),
    Phone("img/auth_service_phone.svg", PhoneAuthProvider(), PhoneAuthProvider.PROVIDER_ID),
    Email("img/auth_service_email.svg", EmailAuthProvider(), EmailAuthProvider.PROVIDER_ID),
    Twitter("img/auth_service_twitter.svg", TwitterAuthProvider(), TwitterAuthProvider.PROVIDER_ID);

    companion object {
        fun forProviderIdOrNull(providerId: String): ProviderType? {
            return values().find { it.providerId == providerId }
        }

        fun forProviderId(providerId: String): ProviderType {
            return forProviderIdOrNull(providerId) ?: error("Unsupported provider: $providerId")
        }
    }
}

/**
 * Provides a fully implemented link with button to sign-in, user profile image, display name, etc.
 */
fun RBuilder.authenticationLink(app: App?, vararg enabledProviderTypes: ProviderType) {
    authenticationLink(app, { Unit }, {}, *enabledProviderTypes)
}

/**
 * Provides a fully implemented link with button to sign-in, user profile image, display name, etc.
 * @param removeFromOldUser action to remove data from an old user when merging an anonymous account into a provider account
 * @param addToNewUser action to add data to a new user when merging an anonymous account into a provider account
 */
fun <T> RBuilder.authenticationLink(
    app: App?,
    removeFromOldUser: () -> T,
    addToNewUser: (T) -> Unit,
    vararg enabledProviderTypes: ProviderType
) {
    child(AuthenticationLink::class) {
        attrs.removeFromOldUser = { removeFromOldUser.invoke() as Any }
        attrs.addToNewUser = { addToNewUser.invoke(it.unsafeCast<T>()) }
        attrs.firebaseApp = app
        attrs.enabledProviderTypes = enabledProviderTypes
    }
}

private fun User.hasProvider(provider: AuthProvider): Boolean {
    return providerData.any { it?.providerId == provider.providerId }
}

val User.anyPhotoURL: String? get() = photoURL ?: providerData.map { it?.photoURL }.firstOrNull { it != null }
private val User.anyDisplayName: String?
    get() = displayName ?: providerData.map { it?.displayName }.firstOrNull { it != null }
private val User.anyEmail: String? get() = email ?: providerData.map { it?.email }.firstOrNull { it != null }
