@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface FormTextProps : RProps {
    var innerRef: React.LegacyRef<FormTextProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var muted: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class FormText<As : React.ElementType> : BsPrefixComponent<As, FormTextProps>