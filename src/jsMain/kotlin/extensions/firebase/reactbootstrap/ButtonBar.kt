package extensions.firebase.reactbootstrap

import bootstrap.*
import extensions.firebase.ProviderType
import extensions.reactbootstrap.NavbarPlacement
import extensions.reactbootstrap.flaticonButton
import extensions.reactbootstrap.navbar
import firebase.app.App
import kotlinx.html.id
import org.w3c.dom.HTMLDivElement
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.h3
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
                    authenticationLink(props.firebaseApp, ProviderType.Google, ProviderType.Facebook)
                }
            }
        }
    }
}
