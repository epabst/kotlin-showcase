@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface SafeAnchorProps : RProps {
    var href: String? get() = definedExternally; set(value) = definedExternally
    var onClick: React.MouseEventHandler<SafeAnchorProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var onKeyDown: React.KeyboardEventHandler<SafeAnchorProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var role: String? get() = definedExternally; set(value) = definedExternally
    var tabIndex: dynamic /* Number | String */
}
abstract external class SafeAnchor<As : React.ElementType> : BsPrefixComponent<As, SafeAnchorProps>