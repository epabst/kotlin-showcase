@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import org.w3c.dom.HTMLAnchorElement
import react.RProps

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
external interface DropdownItemProps : RProps {
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var eventKey: String? get() = definedExternally; set(value) = definedExternally
    var href: String? get() = definedExternally; set(value) = definedExternally
    var onClick: ((event: React.ClickEvent<HTMLAnchorElement>? /* = null */) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
}
external interface DropdownMenuProps : RProps {
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var flip: Boolean? get() = definedExternally; set(value) = definedExternally
    var alignRight: Boolean? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: String /* 'click' | 'mousedown' */
    var popperConfig: Any? get() = definedExternally; set(value) = definedExternally
}
external interface DropdownToggleProps : RProps {
    var id: String
    var split: Boolean? get() = definedExternally; set(value) = definedExternally
    var childBsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var variant: String?
}
abstract external class Dropdown<As : React.ElementType> : BsPrefixComponent<As, DropdownProps> {
    abstract class Item<As : React.ElementType> : BsPrefixComponent<As, DropdownItemProps>
    abstract class Menu<As : React.ElementType> : BsPrefixComponent<As, DropdownMenuProps>
    abstract class Toggle<As : React.ElementType> : BsPrefixComponent<As, DropdownToggleProps>
    abstract class Divider<As : React.ElementType> : BsPrefixComponent<As, RProps>
    abstract class Header<As : React.ElementType> : BsPrefixComponent<As, RProps>
}
