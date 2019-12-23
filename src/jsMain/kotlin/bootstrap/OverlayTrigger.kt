@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RState

external interface DelayProps {
    var show: Number
    var hide: Number
}
external interface OverlayTriggerProps : React.ComponentPropsWithRef<Any> {
    var children: React.ReactNode
    var trigger: String /* 'hover' | 'click' | 'focus' | Array<String /* 'hover' | 'click' | 'focus' */> */
    var delay: dynamic /* Number | DelaySpec */
    var defaultShow: Boolean? get() = definedExternally; set(value) = definedExternally
    var flip: Boolean? get() = definedExternally; set(value) = definedExternally
    var overlay: dynamic /* React.ReactNode | () -> React.ReactNode */
    var target: Any? get() = definedExternally; set(value) = definedExternally
    var onHide: Any? get() = definedExternally; set(value) = definedExternally
    var show: Any? get() = definedExternally; set(value) = definedExternally
}
abstract external class OverlayTrigger : Component<OverlayTriggerProps, RState>