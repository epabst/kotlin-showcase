@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface FormCheckInputProps : RProps {
    var id: String? get() = definedExternally; set(value) = definedExternally
    var type: String /* 'checkbox' | 'radio' */
    var isStatic: Boolean? get() = definedExternally; set(value) = definedExternally
    var isValid: Boolean? get() = definedExternally; set(value) = definedExternally
    var isInvalid: Boolean? get() = definedExternally; set(value) = definedExternally
    var innerRef: React.LegacyRef<FormCheckInputProps /* this */>? get() = definedExternally; set(value) = definedExternally
}
abstract external class FormCheckInput<As : React.ElementType> : BsPrefixComponent<As, FormCheckInputProps>