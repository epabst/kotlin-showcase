@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import org.w3c.dom.HTMLElement
import react.RProps

external interface FormControlProps : RProps {
    var innerRef: React.LegacyRef<dynamic /* HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement */>? get() = definedExternally; set(value) = definedExternally
    var size: String /* 'sm' | 'lg' */
    var plaintext: Boolean? get() = definedExternally; set(value) = definedExternally
    var readOnly: Boolean? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var value: String? get() = definedExternally; set(value) = definedExternally
    var onChange: React.FormEventHandler<HTMLElement /* HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement */>? get() = definedExternally; set(value) = definedExternally
    var type: String? get() = definedExternally; set(value) = definedExternally
    var id: String? get() = definedExternally; set(value) = definedExternally
    var isValid: Boolean? get() = definedExternally; set(value) = definedExternally
    var isInvalid: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class FormControl<As : React.ElementType> : BsPrefixComponent<As, FormControlProps> {
    companion object {
        var Feedback: Any
    }
}