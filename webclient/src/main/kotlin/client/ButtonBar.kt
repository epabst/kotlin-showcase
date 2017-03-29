package client

import client.component.flaticon
import client.component.visible
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
fun HTMLElement.buttonBar(backHash: ReadOnlyProperty<String?> = null.toProperty(), showUndo: ReadOnlyProperty<Boolean> = true.toProperty()) {
    row { id = "buttonBar"
        col(Col.Width.Xs(3)) {
            btsButton(size = ButtonSize.Small, look = ButtonLook.Default, onclick = { event -> UI.back() }) {
                backHash.onNext { visible = it != null }
                flaticon("arrow-pointing-to-left-1"); appendText("Back")
            }
        }
        col(Col.Width.Xs(1)) { div { className = "btn btn-lg"; nbsp() } }
        col(Col.Width.Xs(8)) {
            undoComponent(showUndo)
        }
    }
}
