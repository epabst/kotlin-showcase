package component

import bootstrap.*
import component.bootstrap.NavbarPlacement
import component.bootstrap.flaticonButton
import component.bootstrap.navbar
import component.firebase.authenticationLink
import firebase.app.App
import firebase.auth.FacebookAuthProvider
import firebase.auth.GoogleAuthProvider
import firebase.requireAuth
import kotlinx.html.id
import org.w3c.dom.HTMLDivElement
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.h3
import react.dom.img
import react.dom.span
import react.router.dom.RouteResultHistory

interface ButtonBarProps : HTMLElementProps<HTMLDivElement> {
    var heading: String?
    var history: RouteResultHistory?
    var firebaseApp: App?
}

interface ButtonBarState : RState

class ButtonBar(props: ButtonBarProps) : RComponent<ButtonBarProps, ButtonBarState>(props) {
    override fun RBuilder.render() {
        navbar(fixed = NavbarPlacement.Top) {
            attrs.className = "bg-white"
            child(Container::class) {
                attrs.fluid = true
                attrs.id = "buttonBar"
                if (props.history != null) {
                    span {
                        attrs.id = "backButton"
                        flaticonButton(
                            flaticon = "arrow-pointing-to-left-1",
                            text = "Back",
                            className = "nowrap mr-2"
                        ) {
                            props.history?.goBack()
                        }
                    }
                }
                props.heading?.let { h3 { +it } }
                span(classes = "ml-auto") {
                    val app = props.firebaseApp
                    if (app != null) {
                        requireAuth
                        val provider: GoogleAuthProvider.Companion? = GoogleAuthProvider
                        if (provider?.PROVIDER_ID != null) {
                            authenticationLink(app, mapOf(
                                GoogleAuthProvider() to { onClick ->
                                    child(Button::class) {
                                        attrs.variant = "link"
                                        attrs.onClick = onClick
                                        attrs.className = "auth-button form-control"
                                        img(src = "img/google.svg", classes = "auth-icon") {}
                                    }
                                },
                                FacebookAuthProvider() to { onClick ->
                                    child(Button::class) {
                                        attrs.variant = "link"
                                        attrs.onClick = onClick
                                        attrs.className = "auth-button button-facebook form-control"
                                        img(src = "img/facebook.svg", classes = "auth-icon") {}
                                    }
                                }
                            ))
                        }
                    }
                }
                Unit
            }
        }
    }
}
