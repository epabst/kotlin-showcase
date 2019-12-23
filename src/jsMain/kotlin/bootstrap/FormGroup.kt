@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface FormGroupProps : RProps {
    var innerRef: React.LegacyRef<FormGroupProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var controlId: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class FormGroup<As : React.ElementType> : BsPrefixComponent<As, FormGroupProps>