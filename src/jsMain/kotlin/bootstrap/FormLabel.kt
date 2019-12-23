@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface FormLabelBaseProps : RProps {
    var htmlFor: String? get() = definedExternally; set(value) = definedExternally
    var innerRef: React.LegacyRef<FormLabelBaseProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var srOnly: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface FormLabelOwnProps : FormLabelBaseProps {
    var column: String /* false */
}
external interface FormLabelWithColProps : FormLabelBaseProps, ColProps {
    var column: String /* true */
}
abstract external class FormLabel : BsPrefixComponent<React.String /* 'label' */, dynamic /* FormLabelWithColProps | FormLabelOwnProps */>