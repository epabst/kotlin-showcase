@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import org.w3c.dom.HTMLButtonElement

external interface ButtonProps : HTMLElementProps<HTMLButtonElement> {
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var block: Boolean? get() = definedExternally; set(value) = definedExternally
    var variant: String /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' | 'link' | 'outline-primary' | 'outline-secondary' | 'outline-success' | 'outline-danger' | 'outline-warning' | 'outline-info' | 'outline-dark' | 'outline-light' */
    var size: String? /* 'sm' | 'lg' */
    var type: String /* 'button' | 'reset' | 'submit' */
    var href: String? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class Button<As : React.ElementType> : BsPrefixComponent<As, ButtonProps>