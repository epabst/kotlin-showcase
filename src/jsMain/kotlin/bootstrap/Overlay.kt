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

external interface OverlayProps : TransitionCallbacks {
    var container: dynamic /* React.ReactInstance | Node | (props: Any?) -> dynamic */
    var target: dynamic /* React.ReactInstance | Node | (props: Any?) -> dynamic */
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var popperConfig: Any? get() = definedExternally; set(value) = definedExternally
    var rootClose: Boolean? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: dynamic /* 'click' | 'mousedown' */
    var onHide: ((event: React.SyntheticEvent) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var transition: dynamic /* Boolean | React.ElementType */
    var placement: dynamic /* 'auto-start' | 'auto' | 'auto-end' | 'top-start' | 'top' | 'top-end' | 'right-start' | 'right' | 'right-end' | 'bottom-end' | 'bottom' | 'bottom-start' | 'left-end' | 'left' | 'left-start' */
}
external open class Overlay : React.Component<OverlayProps>