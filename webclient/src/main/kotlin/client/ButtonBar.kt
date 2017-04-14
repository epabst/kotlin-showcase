package client

import client.component.flaticon
import client.component.visible
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.ext.bootstrap3.*
import net.yested.ext.bootstrap3.ButtonLook
import net.yested.ext.bootstrap3.ButtonSize
import org.w3c.dom.HTMLElement

/**
 * Support for undoing user actions.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 12:03 AM
 */
fun HTMLElement.buttonBar(backHash: ReadOnlyProperty<String?> = null.toProperty(), showUndo: ReadOnlyProperty<Boolean> = true.toProperty()) {
    div { id = "buttonBar"
        btsButton(size = ButtonSize.Small, look = ButtonLook.Default, onclick = { UI.back() }) {
            backHash.onNext { visible = it != null }
            flaticon("arrow-pointing-to-left-1")
        }
        span { className = "flex1"}
        undoComponent(showUndo)
    }
}
