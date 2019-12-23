@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface SpinnerProps : RProps {
    var animation: String /* 'border' | 'grow' */
    var role: String? get() = definedExternally; set(value) = definedExternally
    var size: String /* 'sm' */
    var variant: String /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'light' | 'dark' */
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class Spinner<As : React.ElementType> : BsPrefixComponent<As, SpinnerProps>