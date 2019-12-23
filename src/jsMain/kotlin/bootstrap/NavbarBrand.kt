@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface NavbarBrandProps : RProps {
    var href: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class NavbarBrand<As : React.ElementType> : BsPrefixComponent<As, NavbarBrandProps>