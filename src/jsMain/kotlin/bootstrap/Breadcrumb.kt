@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface BreadcrumbProps : RProps {
    var label: String? get() = definedExternally; set(value) = definedExternally
    var listProps: React.OlHTMLAttributes<Any>? get() = definedExternally; set(value) = definedExternally
}
abstract external class Breadcrumb<As : React.ElementType> : BsPrefixComponent<As, BreadcrumbProps> {
    companion object {
        var Item: Any
    }
}