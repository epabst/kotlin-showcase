@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface BadgeProps : RProps {
    var bsPreOkfix: String? get() = definedExternally; set(value) = definedExternally
    var variant: String /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'light' | 'dark' */
    var pill: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class Badge<As : React.ElementType> : BsPrefixComponent<As, BadgeProps>