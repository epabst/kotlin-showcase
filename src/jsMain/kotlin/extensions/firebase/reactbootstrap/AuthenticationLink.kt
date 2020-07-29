package extensions.firebase.reactbootstrap

import bootstrap.*
import extensions.firebase.*
import firebase.Unsubscribe
import firebase.app.App
import firebase.app.User
import firebase.auth.AuthCredential
import firebase.auth.AuthError
import firebase.auth.AuthProviderWithCustomParameters
import firebase.requireAuth
import kotlinx.coroutines.await
import kotlinx.html.id
import kotlinx.html.title
import org.w3c.dom.HTMLElement
import react.*
import react.dom.img
import util.handleError
import util.inContext
import util.launchHandlingErrors
import kotlinx.browser.window
import kotlin.js.json

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
            if (anonymousUser == null && newUser?.isAnonymous == true) {
                console.log("setting anonymousUser=${newUser.uid}")
                anonymousUser = newUser
            }
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
                                    handleOnClick(it, providerType, user, firebaseApp)
                                }
                            }
                        }
                    }
                }
            } else if (user.anyPhotoURL != null) {
                val providerType = user.providerType
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
                            if (providerType != null) {
                                img(src = providerType.imageUrl, classes = "auth-icon") {}
                                +" "
                            }
                            user.anyEmail?.let { +it }
                        }
                        child(Dropdown.Item::class) {
                            +"Switch Account"
                            attrs.onClick = { handleOnClick(it, providerType!!, user, firebaseApp) }
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

    private fun handleOnClick(
        it: React.ClickEvent<HTMLElement>?,
        providerType: ProviderType,
        user: User?,
        firebaseApp: App
    ) {
        it?.preventDefault()
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

    private val User.providerType: ProviderType?
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
                            val authProvider = matchingProviderType.authProvider
                            if (authProvider is AuthProviderWithCustomParameters) {
                                authProvider.setCustomParameters(json("login_hint" to error.email))
                            }

                            auth.signInWithPopup(authProvider).then(onFulfilled = { result ->
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
                else -> handleError(error.message, error)
            }
        }
    }
}