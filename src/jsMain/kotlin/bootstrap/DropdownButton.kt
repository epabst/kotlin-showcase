@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RProps
import react.RState

external interface PropsFromToggle : RProps {
    var href: String?
    var size: String?
    var variant: String?
    var disabled: Boolean?
}
external interface DropdownButtonProps : PropsFromToggle {
    var id: String
    var title: React.ReactNode
    var menuRole: String? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: String /* 'click' | 'mousedown' */
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class DropdownButton : Component<DropdownButtonProps, RState>