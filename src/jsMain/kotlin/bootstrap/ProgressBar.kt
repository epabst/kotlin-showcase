@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ProgressBarProps : RProps {
    var min: Number? get() = definedExternally; set(value) = definedExternally
    var now: Number? get() = definedExternally; set(value) = definedExternally
    var max: Number? get() = definedExternally; set(value) = definedExternally
    var label: React.ReactNode? get() = definedExternally; set(value) = definedExternally
    var srOnly: Boolean? get() = definedExternally; set(value) = definedExternally
    var striped: Boolean? get() = definedExternally; set(value) = definedExternally
    var animated: Boolean? get() = definedExternally; set(value) = definedExternally
    var variant: String /* 'success' | 'danger' | 'warning' | 'info' */
}
abstract external class ProgressBar : BsPrefixComponent<React.String /* 'div' */, ProgressBarProps>