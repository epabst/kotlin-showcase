@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface FormCheckProps : RProps {
    var bsCustomPrefix: String? get() = definedExternally; set(value) = definedExternally
    var innerRef: React.LegacyRef<FormCheckProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var id: String? get() = definedExternally; set(value) = definedExternally
    var inline: Boolean? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var title: String? get() = definedExternally; set(value) = definedExternally
    var label: React.ReactNode? get() = definedExternally; set(value) = definedExternally
    var custom: Boolean? get() = definedExternally; set(value) = definedExternally
    var type: String /* 'checkbox' | 'radio' | 'switch' */
    var isValid: Boolean? get() = definedExternally; set(value) = definedExternally
    var isInvalid: Boolean? get() = definedExternally; set(value) = definedExternally
    var feedback: React.ReactNode? get() = definedExternally; set(value) = definedExternally
}
abstract external class FormCheck<As : React.ElementType> : BsPrefixComponent<As, FormCheckProps> {
    companion object {
        var Input: Any
        var Label: Any
    }
}