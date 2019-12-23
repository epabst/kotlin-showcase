@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RProps
import react.RState

external interface CloseButtonProps : RProps {
    var label: String? get() = definedExternally; set(value) = definedExternally
    var onClick: React.MouseEventHandler<CloseButton>? get() = definedExternally; set(value) = definedExternally
}
abstract external class CloseButton : Component<CloseButtonProps, RState>