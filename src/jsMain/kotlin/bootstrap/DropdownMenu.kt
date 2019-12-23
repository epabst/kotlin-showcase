@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface DropdownMenuProps : RProps {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var flip: Boolean? get() = definedExternally; set(value) = definedExternally
    var alignRight: Boolean? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: String /* 'click' | 'mousedown' */
    var popperConfig: Any? get() = definedExternally; set(value) = definedExternally
}
abstract external class DropdownMenu<As : React.ElementType> : BsPrefixComponent<As, DropdownMenuProps>