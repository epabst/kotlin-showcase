@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RProps
import react.RState

external interface NavDropdownProps : RProps {
    var id: String
    var title: React.ReactNode
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var menuRole: String? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: String /* 'click' | 'mousedown' */
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class NavDropdown : Component<NavDropdownProps, RState> {
    companion object {
        var Item: Any
        var Divider: Any
        var Header: Any
    }
}