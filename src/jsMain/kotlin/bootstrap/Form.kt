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
external interface GroupProps : HTMLElementProps<HTMLDivElement> {
    var controlId: String?
}
external interface ControlProps<E : HTMLElement> : RProps {
    var value: String
    var required: Boolean
    var size: Int?
    var `as`: String?
    var placeholder: String?
    var onKeyUp: ((React.ChangeEvent<E>) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onChange: ((React.ChangeEvent<E>) -> Unit)? get() = definedExternally; set(value) = definedExternally
}
abstract external class Form<As : React.ElementType> : BsPrefixComponent<As, FormProps> {
    abstract class Row(props: RProps) : Component<RProps, RState>
    abstract class Group(props: GroupProps) : Component<GroupProps, RState>
    abstract class Control<T : HTMLElement>(props: ControlProps<T>) : Component<ControlProps<T>, RState> {
        abstract class Feedback<As2 : React.ElementType> : bootstrap.Feedback<As2>
    }
    abstract class Check(props: RProps) : Component<RProps, RState>
    abstract class Label(props: RProps) : Component<RProps, RState>
    abstract class Text(props: RProps) : Component<RProps, RState>
}
