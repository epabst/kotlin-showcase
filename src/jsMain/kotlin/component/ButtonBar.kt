package component

import bootstrap.*
import component.firebase.AuthProviderWithResources
import component.firebase.authenticationLink
import firebase.auth.GoogleAuthProvider
import kotlinx.html.id
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h3
import react.dom.span
import react.router.dom.RouteResultHistory
import util.NavbarPlacement
import util.navbar

interface ButtonBarProps : RProps {
    var heading: String?
    var history: RouteResultHistory?
}

interface ButtonBarState : RState

class ButtonBar(props: ButtonBarProps) : RComponent<ButtonBarProps, ButtonBarState>(props) {
    override fun RBuilder.render() {
        navbar(fixed = NavbarPlacement.Top) {
            child(Container::class) {
                attrs.fluid = true
                attrs.id = "buttonBar"
                child(Row::class) {
                    child(Col::class) {
                        attrs.xs = 12
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
                        props.heading?.let { h3("nowrap") { +it } }
                        val provider: GoogleAuthProvider.Companion? = GoogleAuthProvider
                        if (provider?.PROVIDER_ID != null) {
                            div("pull-right") {
                                val providerWithResources = AuthProviderWithResources(
                                        GoogleAuthProvider(),
                                        "img/google/btn_google_signin_light_normal_web.png")
                                authenticationLink(providerWithResources, { Unit }, { Unit }) { _, _ -> }
                            }
                        }
                    }
                }
            }
        }
    }
}
