@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface NavProps : RProps {
    var navbarBsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var cardHeaderBsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var variant: String /* 'tabs' | 'pills' */
    var activeKey: Any? get() = definedExternally; set(value) = definedExternally
    var defaultActiveKey: Any? get() = definedExternally; set(value) = definedExternally
    var fill: Boolean? get() = definedExternally; set(value) = definedExternally
    var justify: Boolean? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var role: String? get() = definedExternally; set(value) = definedExternally
    var navbar: Boolean? get() = definedExternally; set(value) = definedExternally
    var onKeyDown: React.KeyboardEventHandler<NavProps /* this */>? get() = definedExternally; set(value) = definedExternally
}
abstract external class Nav<As : React.ElementType> : BsPrefixComponent<As, NavProps> {
    companion object {
        var Item: Any
        var Link: Any
    }
}