@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ToggleButtonProps : RProps {
    var type: String /* 'checkbox' | 'radio' */
    var name: String? get() = definedExternally; set(value) = definedExternally
    var checked: Boolean? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var onChange: React.ChangeEventHandler<ToggleButtonProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var value: Any
    var inputRef: React.LegacyRef<ToggleButtonProps /* this */>? get() = definedExternally; set(value) = definedExternally
}
abstract external class ToggleButton<As : React.ElementType> : BsPrefixComponent<As, ToggleButtonProps>