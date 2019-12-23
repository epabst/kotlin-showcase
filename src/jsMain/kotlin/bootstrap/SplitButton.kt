@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RState

external interface SplitButtonProps : PropsFromToggle {
    var id: dynamic /* String | Number */
    var toggleLabel: String? get() = definedExternally; set(value) = definedExternally
    override var href: String? get() = definedExternally; set(value) = definedExternally
    var target: String? get() = definedExternally; set(value) = definedExternally
    var onClick: React.MouseEventHandler<SplitButtonProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var title: React.ReactNode
    var menuRole: String? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: String /* 'click' | 'mousedown' */
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class SplitButton : Component<SplitButtonProps, RState>
