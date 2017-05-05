package client

import client.component.flaticon
import client.component.visible
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.ext.bootstrap3.*
import net.yested.ext.bootstrap3.ButtonLook
import net.yested.ext.bootstrap3.ButtonSize
import net.yested.ext.jquery.backToHash
import org.w3c.dom.HTMLElement
import kotlin.browser.window
import kotlin.dom.addClass
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
                    btsButton(size = ButtonSize.Default, look = ButtonLook.Default, onclick = { event -> window.history.backToHash(backHash.get()) }) {
                        addClass("nowrap")
                        backHash.onNext { visible = it != null }
                        flaticon("arrow-pointing-to-left-1"); appendText("Back")
                    }
                    nbsp(2)
                }
                h3 {
                    addClass("nowrap")
                    a {
                        headingHref.onNext { href = it ?: ""; headingHref.onNext { visible = it != null } }
                        heading.onNext { textContent = it }
                    }
                    span { headingHref.onNext { visible = it == null }
                        heading.onNext { textContent = it }
                    }
                }
            }
        }
    }
    br()
    br()
    br()
}
