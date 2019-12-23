@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

external interface ToggleButtonRadioProps<T> {
    var type: String /* 'radio' */
    var name: String
    var value: T? get() = definedExternally; set(value) = definedExternally
    var defaultValue: T? get() = definedExternally; set(value) = definedExternally
    var onChange: ((value: T) -> Unit)? get() = definedExternally; set(value) = definedExternally
}
external interface ToggleButtonCheckboxProps<T> {
    var type: String /* 'checkbox' */
    var name: String? get() = definedExternally; set(value) = definedExternally
    var value: Array<T>? get() = definedExternally; set(value) = definedExternally
    var defaultValue: Array<T>? get() = definedExternally; set(value) = definedExternally
    var onChange: ((value: Array<T>) -> Unit)? get() = definedExternally; set(value) = definedExternally
}
abstract external class ToggleButtonGroup<T, As : React.ElementType> : BsPrefixComponent<As, dynamic /* ToggleButtonRadioProps<T> | ToggleButtonCheckboxProps<T> */>