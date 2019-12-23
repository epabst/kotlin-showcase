@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

abstract external class InputGroupAppend<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class InputGroupPrepend<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class InputGroupText<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class InputGroupCheckbox : BsPrefixComponent<React.String /* 'input' */, RProps>
abstract external class InputGroupRadio : BsPrefixComponent<React.String /* 'input' */, RProps>
external interface InputGroupProps : RProps {
    var size: String /* 'sm' | 'lg' */
}
abstract external class InputGroup<As : React.ElementType> : BsPrefixComponent<As, InputGroupProps> {
    companion object {
        var Append: Any
        var Prepend: Any
        var Text: Any
        var Checkbox: Any
        var Radio: Any
    }
}