@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RState

external interface OverlayProps : TransitionCallbacks {
    var container: dynamic /* React.ReactInstance | Node | (props: Any?) -> dynamic */
    var target: dynamic /* React.ReactInstance | Node | (props: Any?) -> dynamic */
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var popperConfig: Any? get() = definedExternally; set(value) = definedExternally
    var rootClose: Boolean? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: String /* 'click' | 'mousedown' */
    var onHide: ((event: React.SyntheticEvent) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var transition: dynamic /* Boolean | React.ElementType */
    var placement: String /* 'auto-start' | 'auto' | 'auto-end' | 'top-start' | 'top' | 'top-end' | 'right-start' | 'right' | 'right-end' | 'bottom-end' | 'bottom' | 'bottom-start' | 'left-end' | 'left' | 'left-start' */
}
abstract external class Overlay : Component<OverlayProps, RState>
