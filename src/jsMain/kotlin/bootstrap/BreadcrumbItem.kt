@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface BreadcrumbItemProps : RProps {
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var href: String? get() = definedExternally; set(value) = definedExternally
    var target: String? get() = definedExternally; set(value) = definedExternally
    var title: React.ReactNode? get() = definedExternally; set(value) = definedExternally
}
abstract external class BreadcrumbItem<As : React.ElementType> : BsPrefixComponent<As, BreadcrumbItemProps>