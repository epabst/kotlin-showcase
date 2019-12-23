@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ButtonToolbarProps : RProps {
    var role: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class ButtonToolbar : BsPrefixComponent<React.ElementType /* 'div' */, ButtonToolbarProps>