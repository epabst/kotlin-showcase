@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

abstract external class NavbarText<As : React.ElementType> : BsPrefixComponent<As, RProps>
external interface NavbarProps : RProps {
    var variant: String /* 'light' | 'dark' */
    var expand: dynamic /* Boolean | 'sm' | 'md' | 'lg' | 'xl' */
    var bg: String? get() = definedExternally; set(value) = definedExternally
    var fixed: String? /* 'top' | 'bottom' */
    var sticky: String? /* 'top' | 'bottom' */
    var onToggle: ((expanded: Boolean) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var collapseOnSelect: Boolean? get() = definedExternally; set(value) = definedExternally
    var expanded: Boolean? get() = definedExternally; set(value) = definedExternally
    var role: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class Navbar<As : React.ElementType> : BsPrefixComponent<As, NavbarProps> {
    companion object {
        var Brand: Any
        var Toggle: Any
        var Collapse: Any
        var Text: Any
    }
}