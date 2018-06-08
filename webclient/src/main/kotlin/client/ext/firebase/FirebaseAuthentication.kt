package client.ext.firebase

import client.*
import client.component.visible
import client.util.handleError
import firebase.Promise
import firebase.User
import firebase.auth.AuthProvider
import firebase.auth.Error
import net.yested.core.html.*
import net.yested.core.properties.Property
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import kotlin.dom.appendText

/**
 * A link that enables signing into a Google account.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/24/18
 * Time: 12:01 AM
 */
open class AuthProviderWithResources(val provider: AuthProvider, val authenticateUrl: String)

object FirebaseAuthentication {
    fun initialize(user: Property<User?>) {
        Factory.firebaseApp?.auth()?.useDeviceLanguage()
        Factory.firebaseApp?.auth()?.onAuthStateChanged({ newUser ->
            if (newUser == null) {
                println("newUser == null so calling signInAnonymously()")
                Factory.firebaseApp.auth().signInAnonymously().then { error ->
                    handleError(Error(error.message))
                }
            }
            val providerData = newUser?.providerData?.map { "uid=${it?.uid} displayName=${it?.displayName} photoURL=${it?.photoURL} email=${it?.email}" }?.joinToString(";", "[", "]")
            console.log("uid=${newUser?.uid} isAnonymous=${newUser?.isAnonymous} displayName=${newUser?.displayName} email=${newUser?.email} photoURL=${newUser?.anyPhotoURL} $providerData")
            user.set(newUser)
        }, opt_error = {
            handleError(Exception("Error ${it.code}: ${it.message}"))
        })
    }
}

/**
 * Provides a fully implemented link with button to sign-in, user profile image, display name, etc.
 * @param providerWithResources the provider along with necessary resources for link
 * @param removeFromOldUser action to remove data from an old user when merging an anonymous account into a provider account
 * @param addToNewUser action to add data to a new user when merging an anonymous account into a provider account
 */
fun <T> HTMLElement.authenticationLink(providerWithResources: AuthProviderWithResources, removeFromOldUser: () -> T, addToNewUser: (T) -> Unit) {
    val provider = providerWithResources.provider
    val authAction: (Event) -> Promise<Any> = {
        it.preventDefault()
        val priorUser = Factory.user.get()
        val onReject: (Error) -> Unit = { handleAuthError(it, priorUser, provider, removeFromOldUser, addToNewUser) }
        if (priorUser == null || priorUser.hasProvider(provider)) {
            Factory.firebaseApp!!.auth().signInWithPopup(provider).then(onReject = onReject)
        } else {
            priorUser.linkWithPopup(provider).then(onReject = onReject)
        }
    }
    a {
        href = "#"
        onclick = authAction
        img {
            Factory.user.onNext {
                src = providerWithResources.authenticateUrl
                visible = it == null || !it.hasProvider(provider)
            }
        }
    }
    div {
        addClass2("dropdown")
        Factory.user.onNext {
            val photoURL = it?.anyPhotoURL
            visible = photoURL != null
        }
        a {
            href = "#"
            addClass2("dropdown-toggle")
            setAttribute("data-toggle", "dropdown")
            img {
                id = "user-img"
                Factory.user.onNext {
                    val photoURL = it?.anyPhotoURL
                    src = photoURL ?: ""
                    title = it?.anyDisplayName ?: ""
                }
            }
        }
        ul {
            addClass2("dropdown-menu", "dropdown-menu-right")
            li {
                addClass2("dropdown-header")
                Factory.user.onNext {
                    textContent = it?.anyEmail
                }
            }
            li {
                a {
                    href = "#"
                    appendText("Switch Account")
                    onclick = authAction
                }
            }
            li {
                a {
                    href = "#"
                    appendText("Sign out")
                    onclick = { Factory.firebaseApp!!.auth().signOut() }
                }
            }
        }
        a {
            href = "#"
            onclick = authAction
        }
    }
}

private fun <T> handleAuthError(error: Error, priorUser: User?, provider: AuthProvider, removeFromOldUser: () -> T, addToNewUser: (T) -> Unit) {
    val newCredential = error.credential
    when (error.code) {
        "auth/popup-closed-by-user" -> Unit //do nothing
        "auth/cancelled-popup-request" -> Unit // do nothing
        "auth/credential-already-in-use" -> {
            if (newCredential != null) {
                val dataFromOldUser = if (priorUser?.hasProvider(provider) == false) {
                    removeFromOldUser.invoke()
                } else {
                    null
                }
                println("priorUser.uid=${priorUser?.uid} priorUser.providerId=${priorUser?.providerId}")
                Factory.firebaseApp!!.auth().signInWithCredential(newCredential).then({ newUserCredentialPair ->
                    if (newUserCredentialPair.user != null) {
                        Factory.user.set(newUserCredentialPair.user)
                        dataFromOldUser?.let { addToNewUser.invoke(it) }
                        priorUser?.delete()
                        println("Deleted priorUser.uid=${priorUser?.uid} priorUser.providerId=${priorUser?.providerId}")
                    }
                }, {
                    //put the data back since it failed
                    dataFromOldUser?.let { addToNewUser.invoke(it) } ?: Unit
                })
            }
        }
        else -> handleError(Exception("Error ${error.code}: ${error.message}"))
    }
}

private fun User.hasProvider(provider: AuthProvider): Boolean {
    return providerData.any { it?.providerId == provider.providerId }
}
val User.anyPhotoURL: String? get() = photoURL ?: providerData.map { it?.photoURL }.firstOrNull { it != null }
private val User.anyDisplayName: String? get() = displayName ?: providerData.map { it?.displayName }.firstOrNull { it != null }
private val User.anyEmail: String? get() = email ?: providerData.map { it?.email }.firstOrNull { it != null }
