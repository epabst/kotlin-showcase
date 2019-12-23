@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import org.w3c.dom.HTMLElement
import react.Component
import react.RProps
import react.RState

external interface BsPrefixProps<As : React.ElementType> : RProps {
    var `as`: As? get() = definedExternally; set(value) = definedExternally
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class BsPrefixComponent<As : React.ElementType, P: RProps> : Component<P, RState>
external interface TransitionCallbacks : RProps {
    val onEnter: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onEntered: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onEntering: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onExit: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onExited: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onExiting: ((node: HTMLElement) -> Any)? get() = definedExternally
}
external interface HTMLElementProps<E : HTMLElement> : RProps {
    var id: String?
    var className: String?
    var onClick: ((React.ClickEvent<E>) -> Unit)? get() = definedExternally; set(value) = definedExternally
}
