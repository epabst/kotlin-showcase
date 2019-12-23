@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

abstract external class DropdownDivider<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class DropdownHeader<As : React.ElementType> : BsPrefixComponent<As, RProps>
external interface ToggleMetadata {
    var source: String /* 'select' | 'click' | 'rootClose' | 'keydown' */
}
external interface DropdownProps : RProps {
    var drop: String /* 'up' | 'left' | 'right' | 'down' */
    var alignRight: Boolean? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var flip: Boolean? get() = definedExternally; set(value) = definedExternally
    var onToggle: ((isOpen: Boolean, event: React.SyntheticEvent, metadata: ToggleMetadata) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
}
abstract external class Dropdown<As : React.ElementType> : BsPrefixComponent<As, DropdownProps> {
    companion object {
        var Toggle: Any
        var Menu: Any
        var Item: Any
        var Divider: Any
        var Header: Any
    }
}