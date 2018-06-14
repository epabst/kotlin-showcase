package client

import client.Factory.addPrivateDataToNewUser
import client.Factory.removePrivateDataFromOldUser
import client.component.flaticon
import client.component.visible
import client.ext.firebase.AuthProviderWithResources
import client.ext.firebase.authenticationLink
import firebase.auth.GoogleAuthProvider
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.ext.bootstrap3.*
import net.yested.ext.bootstrap3.ButtonLook
import net.yested.ext.bootstrap3.ButtonSize
import net.yested.ext.jquery.backToHash
import org.w3c.dom.HTMLElement
import kotlin.browser.window
import kotlin.dom.appendText

/**
 * Support for undoing user actions.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 12:03 AM
 */
fun HTMLElement.buttonBar(backHash: ReadOnlyProperty<String?> = null.toProperty(),
                          heading: ReadOnlyProperty<String?> = null.toProperty(),
                          headingHref: ReadOnlyProperty<String?> = null.toProperty()) {
    navbar(NavbarCompletePosition.FixedTop, containerWidth = ContainerWidth.Fluid) {
        navbarContainer.id = "buttonBar"
        navbarContainer.row {
            col(Col.Width.Tn(12) and Col.Width.Xs(12)) {
                span { id = "backButton"
                    btsButton(size = ButtonSize.Default, look = ButtonLook.Default, onclick = { _ -> window.history.backToHash(backHash.get()) }) {
                        addClass2("nowrap")
                        backHash.onNext { visible = it != null }
                        flaticon("arrow-pointing-to-left-1"); appendText("Back")
                    }
                    nbsp(2)
                }
                h3 {
                    addClass2("nowrap")
                    a {
                        headingHref.onNext { href = it ?: ""; headingHref.onNext { visible = it != null } }
                        heading.onNext { textContent = it }
                    }
                    span { headingHref.onNext { visible = it == null }
                        heading.onNext { textContent = it }
                    }
                }
                div {
                    addClass2("pull-right")
                    val providerWithResources = AuthProviderWithResources(
                            GoogleAuthProvider(),
                            "img/google/btn_google_signin_light_normal_web.png")
                    authenticationLink(providerWithResources, { removePrivateDataFromOldUser() }, { addPrivateDataToNewUser(it) })
                }
            }
        }
    }
    div {
        addClass2("hidden-xs", "hidden-sm", "hidden-md", "hidden-lg")
        br()
        br()
    }
    br()
    br()
    br()
    br()
}
