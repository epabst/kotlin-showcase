package component.firebase

import todo.model.Factory
import platform.showUserExpectedError
import platform.inContext
import firebase.User
import firebase.app.App
import firebase.auth.Auth
import firebase.auth.AuthProvider
import firebase.auth.Error
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
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

object FirebaseAuthentication {

    fun initialize(onAuthStateChanged: (oldUser: User?, newUser: User?) -> Unit) {
        Factory.firebaseApp?.auth()?.onUserChanged(onAuthStateChanged)
    }
}

fun Auth.onUserChanged(onAuthStateChanged: (oldUser: User?, newUser: User?) -> Unit): () -> Any {
    var oldUser: User? = null
    return onAuthStateChanged({ newUser ->
        onAuthStateChanged(oldUser, newUser)
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
    var onAuthStateChanged: (oldUser: User?, newUser: User?) -> Unit
    var providerWithResources: AuthProviderWithResources
    var removeFromOldUser: () -> Any
    var addToNewUser: (Any) -> Unit
}

interface AuthenticationLinkState : RState {
    var user: User?
}

class AuthenticationLink(props: AuthenticationLinkProps) : RComponent<AuthenticationLinkProps, AuthenticationLinkState>(props) {

    override fun RBuilder.render() {
        val user = state.user
        val authAction: (Event) -> Unit = {
            it.preventDefault()
            val provider = props.providerWithResources.provider
            val priorUser = user
            val onReject: (Error) -> Unit = { error -> handleAuthError(error, priorUser, provider) }
            if (priorUser == null) {
                val dataFromOldUser = props.removeFromOldUser.invoke()
                props.firebaseApp.auth().signInWithPopup(provider).then(onReject = onReject, onResolve = {
                    props.addToNewUser.invoke(dataFromOldUser)
                })
            } else if (priorUser.hasProvider(provider)) {
                props.firebaseApp.auth().signInWithPopup(provider).then(onReject = onReject)
            } else {
                priorUser.linkWithPopup(provider).then(onReject = onReject)
            }
        }
        a(href = "#") {
            attrs.onClickFunction = authAction
            val imgClass = if (user == null || !user.hasProvider(props.providerWithResources.provider)) "" else "hidden"
            img(src = props.providerWithResources.authenticateUrl, classes = imgClass) {}
        }
        val divClass = if (user?.anyPhotoURL != null) "" else "hidden"
        div(classes = "dropdown $divClass") {
            a(href = "#", classes = "dropdown-toggle") {
                attrs["data-toggle"] = "dropdown"
                img(src = user?.anyPhotoURL ?: "") {
                    attrs.id = "user-img"
                    attrs.title = user?.anyDisplayName ?: ""
                }
            }
            ul(classes = "dropdown-menu, dropdown-menu-right") {
                li(classes = "dropdown-header") {
                    user?.anyEmail?.let { +it }
                }
                li {
                    a(href = "#") {
                        +"Switch Account"
                        attrs.onClickFunction = authAction
                    }
                }
                li {
                    a(href = "#") {
                        +"Sign out"
                        attrs.onClickFunction = { props.firebaseApp.auth().signOut() }
                    }
                }
            }
            a(href = "#") {
                attrs.onClickFunction = authAction
            }
        }
    }

    private fun handleAuthError(error: Error, priorUser: User?, provider: AuthProvider) {
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
                        Factory.firebaseApp!!.auth().signInWithCredential(newCredential).then({ newUserCredentialPair ->
                            if (newUserCredentialPair.user != null) {
                                props.onAuthStateChanged.invoke(priorUser, newUserCredentialPair.user)
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
                                    removeFromOldUser: () -> T,
                                    addToNewUser: (T) -> Unit,
                                    onAuthStateChanged: (oldUser: User?, newUser: User?) -> Unit) {
    if (Factory.firebaseApp == null) return

    child(AuthenticationLink::class) {
        attrs.removeFromOldUser = { removeFromOldUser.invoke() as Any }
        attrs.addToNewUser = { addToNewUser.invoke(it.unsafeCast<T>()) }
        attrs.firebaseApp = Factory.firebaseApp
        attrs.providerWithResources = providerWithResources
        attrs.onAuthStateChanged = onAuthStateChanged
    }
}

private fun User.hasProvider(provider: AuthProvider): Boolean {
    return providerData.any { it?.providerId == provider.providerId }
}
val User.anyPhotoURL: String? get() = photoURL ?: providerData.map { it?.photoURL }.firstOrNull { it != null }
private val User.anyDisplayName: String? get() = displayName ?: providerData.map { it?.displayName }.firstOrNull { it != null }
private val User.anyEmail: String? get() = email ?: providerData.map { it?.email }.firstOrNull { it != null }
