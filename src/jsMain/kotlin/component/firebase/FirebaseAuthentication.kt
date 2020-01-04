@file:Suppress("unused")

package component.firebase

import bootstrap.*
import firebase.Unsubscribe
import platform.showUserExpectedError
import platform.inContext
import firebase.app.User
import firebase.app.App
import firebase.auth.Auth
import firebase.auth.AuthError
import firebase.auth.AuthProvider
import kotlinx.html.id
import kotlinx.html.title
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * A link that enables signing into a Google account.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/24/18
 * Time: 12:01 AM
 */
open class AuthProviderWithResources(val provider: AuthProvider, val authenticateUrl: String)

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
    var firebaseApp: App
    /** the provider along with necessary resources for link */
    var providerWithResources: AuthProviderWithResources
    /** action to remove data from an old user when merging an anonymous account into a provider account */
    var removeFromOldUser: () -> Any
    /** action to add data to a new user when merging an anonymous account into a provider account */
    var addToNewUser: (Any) -> Unit
}

interface AuthenticationLinkState : RState {
    var user: User?
}

/** Provides a fully implemented link with button to sign-in, user profile image, display name, etc. */
class AuthenticationLink(props: AuthenticationLinkProps) : RComponent<AuthenticationLinkProps, AuthenticationLinkState>(props) {

    private var unsubscribe: Unsubscribe? = null

    override fun componentDidMount() {
        unsubscribe = props.firebaseApp.auth().onUserChanged { _, newUser ->
            setState { user = newUser }
        }
    }

    override fun componentWillUnmount() {
        unsubscribe?.invoke()
    }

    override fun RBuilder.render() {
        val user = state.user
        val authAction: (Event?) -> Unit = {
            it?.preventDefault()
            val provider = props.providerWithResources.provider
            val onReject: (Throwable) -> Unit = { error -> handleAuthError(error.unsafeCast<AuthError>(), user, provider) }
            when {
                user == null -> {
                    val dataFromOldUser = props.removeFromOldUser.invoke()
                    props.firebaseApp.auth().signInWithPopup(provider).then(onRejected = onReject, onFulfilled = {
                        props.addToNewUser.invoke(dataFromOldUser)
                    })
                }
                user.hasProvider(provider) -> {
                    props.firebaseApp.auth().signInWithPopup(provider).then(onRejected = onReject, onFulfilled = {})
                }
                else -> {
                    user.linkWithPopup(provider).then(onRejected = onReject, onFulfilled = {})
                }
            }
        }
        if (user == null || !user.hasProvider(props.providerWithResources.provider)) {
            child(Button::class) {
                attrs.variant = "link"
                attrs.onClick = authAction
                img(src = props.providerWithResources.authenticateUrl) {}
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
                    child(Dropdown.Header::class) {
                        user.anyEmail?.let { +it }
                    }
                    child(Dropdown.Item::class) {
                        +"Switch Account"
                        attrs.onClick = authAction
                    }
                    child(Dropdown.Item::class) {
                        +"Sign out"
                        attrs.onClick = { props.firebaseApp.auth().signOut() }
                    }
                }
            }
        }
    }

    private fun handleAuthError(error: AuthError, priorUser: User?, provider: AuthProvider) {
        inContext("Authentication") {
            val newCredential = error.credential
            when (error.code) {
                "auth/popup-closed-by-user" -> Unit //do nothing
                "auth/cancelled-popup-request" -> Unit // do nothing
                "auth/credential-already-in-use" -> {
                    if (newCredential != null) {
                        val dataFromOldUser = if (priorUser?.hasProvider(provider) == false) {
                            props.removeFromOldUser.invoke()
                        } else {
                            null
                        }
                        println("priorUser.uid=${priorUser?.uid} priorUser.providerId=${priorUser?.providerId}")
                        props.firebaseApp.auth().signInWithCredential(newCredential).then({ newUserCredentialPair ->
                            if (newUserCredentialPair.user != null) {
                                dataFromOldUser?.let { props.addToNewUser.invoke(it) }
                                priorUser?.delete()
                                println("Deleted priorUser.uid=${priorUser?.uid} priorUser.providerId=${priorUser?.providerId}")
                            }
                        }, {
                            //put the data back since it failed
                            dataFromOldUser?.let { props.addToNewUser.invoke(it) } ?: Unit
                        })
                    }
                }
                else -> showUserExpectedError(error.message)
            }
        }
    }
}

/**
 * Provides a fully implemented link with button to sign-in, user profile image, display name, etc.
 * @param providerWithResources the provider along with necessary resources for link
 * @param removeFromOldUser action to remove data from an old user when merging an anonymous account into a provider account
 * @param addToNewUser action to add data to a new user when merging an anonymous account into a provider account
 */
fun <T> RBuilder.authenticationLink(providerWithResources: AuthProviderWithResources,
                                    app: App,
                                    removeFromOldUser: () -> T,
                                    addToNewUser: (T) -> Unit) {
    child(AuthenticationLink::class) {
        attrs.removeFromOldUser = { removeFromOldUser.invoke() as Any }
        attrs.addToNewUser = { addToNewUser.invoke(it.unsafeCast<T>()) }
        attrs.firebaseApp = app
        attrs.providerWithResources = providerWithResources
    }
}

private fun User.hasProvider(provider: AuthProvider): Boolean {
    return providerData.any { it?.providerId == provider.providerId }
}
val User.anyPhotoURL: String? get() = photoURL ?: providerData.map { it?.photoURL }.firstOrNull { it != null }
private val User.anyDisplayName: String? get() = displayName ?: providerData. map { it?.displayName }.firstOrNull { it != null }
private val User.anyEmail: String? get() = email ?: providerData.map { it?.email }.firstOrNull { it != null }
