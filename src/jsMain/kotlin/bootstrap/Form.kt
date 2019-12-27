@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import react.Component
import react.RProps
import react.RState

abstract external class FormRow<As : React.ElementType> : BsPrefixComponent<As, RProps>
external interface FormProps : RProps {
    var innerRef: React.LegacyRef<FormProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var inline: Boolean? get() = definedExternally; set(value) = definedExternally
    var validated: Boolean? get() = definedExternally; set(value) = definedExternally
    var onSubmit: ((React.SubmitEvent) -> Unit)? get() = definedExternally; set(value) = definedExternally
}
external interface FormGroupProps : HTMLElementProps<HTMLDivElement> {
    var innerRef: React.LegacyRef<FormGroupProps /* this */>?
    var controlId: String?
}
external interface FormControlProps<E : HTMLElement> : RProps {
    var inputMode: String? /* "numeric" || ... */
    var min: String // for type = "number"
    var max: String // for type = "number"
    var step: String // for type = "number"
    var required: Boolean
    var size: String /* "sm" | "lg" */
    var `as`: String?
    var placeholder: String?
    var onKeyUp: ((React.ChangeEvent<E>) -> Unit)? get() = definedExternally; set(value) = definedExternally

    var innerRef: React.LegacyRef<dynamic /* HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement */>? get() = definedExternally; set(value) = definedExternally
    var plaintext: Boolean? get() = definedExternally; set(value) = definedExternally
    var readOnly: Boolean? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var defaultValue: String? get() = definedExternally; set(value) = definedExternally
    var value: String? get() = definedExternally; set(value) = definedExternally
    var onChange: ((React.ChangeEvent<E /* HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement */>) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var type: String? // see types for https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input
    var id: String? get() = definedExternally; set(value) = definedExternally
    var isValid: Boolean? get() = definedExternally; set(value) = definedExternally
    var isInvalid: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface FeedbackProps : RProps {
    var bsPrefix: Any? get() = definedExternally; set(value) = definedExternally
    var type: String /* 'valid' | 'invalid' */
}
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
external interface FormTextProps : HTMLElementProps<HTMLElement> {
    var innerRef: React.LegacyRef<FormTextProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var muted: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface FormLabelBaseProps : RProps {
    var htmlFor: String? get() = definedExternally; set(value) = definedExternally
    var innerRef: React.LegacyRef<FormLabelBaseProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var srOnly: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface FormLabelWithColProps : FormLabelBaseProps, ColProps {
    var column: String /* true */
}
abstract external class Form<As : React.ElementType> : BsPrefixComponent<As, FormProps> {
    abstract class Row(props: RProps) : Component<RProps, RState>
    abstract class Group(props: FormGroupProps) : Component<FormGroupProps, RState>
    abstract class Control<T : HTMLElement>(props: FormControlProps<T>) : Component<FormControlProps<T>, RState> {
        abstract class Feedback<As2 : React.ElementType> : BsPrefixComponent<As2, FeedbackProps>
    }
    abstract class Check(props: RProps) : Component<RProps, RState>
    abstract class Input(props: RProps) : Component<RProps, RState>
    abstract class Label(props: FormLabelWithColProps) : Component<FormLabelWithColProps, RState>
    abstract class Text(props: FormTextProps) : Component<FormTextProps, RState>
}
