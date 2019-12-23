@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RState

external interface CollapseProps : TransitionCallbacks, React.ClassAttributes<Collapse> {
    var `in`: Boolean? get() = definedExternally; set(value) = definedExternally
    var mountOnEnter: Boolean? get() = definedExternally; set(value) = definedExternally
    var unmountOnExit: Boolean? get() = definedExternally; set(value) = definedExternally
    var appear: Boolean? get() = definedExternally; set(value) = definedExternally
    var timeout: Number? get() = definedExternally; set(value) = definedExternally
    var dimension: dynamic /* 'height' | 'width' | () -> dynamic */
    var getDimensionValue: ((dimension: Number, element: React.ReactElement) -> Number)? get() = definedExternally; set(value) = definedExternally
    var role: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class Collapse : Component<CollapseProps, RState>