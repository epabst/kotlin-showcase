@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
@file:JsModule("react-bootstrap")
package bootstrap

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external interface ModalProps : TransitionCallbacks {
    var size: dynamic /* 'sm' | 'lg' | 'xl' */
    var centered: Boolean? get() = definedExternally; set(value) = definedExternally
    var backdrop: dynamic /* 'static' | Boolean */
    var backdropClassName: String? get() = definedExternally; set(value) = definedExternally
    var keyboard: Boolean? get() = definedExternally; set(value) = definedExternally
    var animation: Boolean? get() = definedExternally; set(value) = definedExternally
    var dialogClassName: String? get() = definedExternally; set(value) = definedExternally
    var dialogAs: React.ElementType? get() = definedExternally; set(value) = definedExternally
    var autoFocus: Boolean? get() = definedExternally; set(value) = definedExternally
    var enforceFocus: Boolean? get() = definedExternally; set(value) = definedExternally
    var restoreFocus: Boolean? get() = definedExternally; set(value) = definedExternally
    var restoreFocusOptions: FocusOptions? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var onShow: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onHide: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
    var container: Any? get() = definedExternally; set(value) = definedExternally
    var scrollable: Boolean? get() = definedExternally; set(value) = definedExternally
    var onEscapeKeyDown: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
}
external open class Modal<As : React.ElementType> : BsPrefixComponent<As, ModalProps> {
    companion object {
        var Body: Any
        var Header: Any
        var Title: Any
        var Footer: Any
        var Dialog: Any
    }
}