package component

import bootstrap.*
import component.firebase.AuthProviderWithResources
import component.firebase.authenticationLink
import firebase.app.App
import firebase.auth.GoogleAuthProvider
import firebase.requireAuth
import kotlinx.html.id
import org.w3c.dom.HTMLDivElement
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.div
import react.dom.h3
import react.dom.span
import react.router.dom.RouteResultHistory
import util.NavbarPlacement
import util.navbar

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
                child(Row::class) {
                    child(Col::class) {
                        attrs.sm = 8
                        if (props.history != null) {
                            span {
                                attrs.id = "backButton"
                                child(Button::class) {
                                    attrs.variant = "secondary"
                                    attrs.className = "nowrap mr-2"
                                    attrs.onClick = { props.history?.goBack() }
                                    flaticon("arrow-pointing-to-left-1")
                                    +"Back"
                                }
                            }
                        }
                        props.heading?.let { h3 { +it } }
                    }
                    child(Col::class) {
                        attrs.sm = 4
                        attrs.className = "text-right"
                        val app = props.firebaseApp
                        if (app != null) {
                            requireAuth
                            val provider: GoogleAuthProvider.Companion? = GoogleAuthProvider
                            if (provider?.PROVIDER_ID != null) {
                                val providerWithResources = AuthProviderWithResources(
                                        GoogleAuthProvider(),
                                        "img/google/btn_google_signin_light_normal_web.png")
                                authenticationLink(providerWithResources, app, { Unit }, { Unit })
                            }
                        }
                    }
                }
            }
        }
    }
}
