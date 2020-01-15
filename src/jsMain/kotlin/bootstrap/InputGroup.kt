@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "unused"
)
@file:JsModule("react-bootstrap")

package bootstrap

import org.w3c.dom.HTMLDivElement
import react.RProps

external interface InputGroupProps : HTMLElementProps<HTMLDivElement> {
    var size: String /* 'sm' | 'lg' */
}

abstract external class InputGroup<As : React.ElementType> : BsPrefixComponent<As, InputGroupProps> {
    abstract class Append<As : React.ElementType> : BsPrefixComponent<As, RProps>
    abstract class Prepend<As : React.ElementType> : BsPrefixComponent<As, RProps>
    abstract class Text<As : React.ElementType> : BsPrefixComponent<As, RProps>
    abstract class Checkbox : BsPrefixComponent<React.String /* 'input' */, RProps>
    abstract class Radio : BsPrefixComponent<React.String /* 'input' */, RProps>
}
