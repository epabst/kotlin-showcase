package client

import client.component.flaticon
import client.component.visible
import client.util.backToHash
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.ext.bootstrap3.*
import net.yested.ext.bootstrap3.ButtonLook
import net.yested.ext.bootstrap3.ButtonSize
import org.w3c.dom.HTMLElement
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
            col(Col.Width.Tn(4) and Col.Width.Xxs(3) and Col.Width.Xs(2) and Col.Width.Md(1)) {
                btsButton(size = ButtonSize.Default, look = ButtonLook.Default, onclick = { event -> UI.windowHistory.backToHash(backHash.get()) }) {
                    backHash.onNext { visible = it != null }
                    flaticon("arrow-pointing-to-left-1"); appendText("Back")
                }
            }
            col(Col.Width.Tn(8) and Col.Width.Xxs(9) and Col.Width.Xs(10) and Col.Width.Md(11)) {
                h3 {
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
