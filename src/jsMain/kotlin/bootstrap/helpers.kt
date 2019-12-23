@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
@file:JsModule("react-bootstrap")
package bootstrap

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*
import react.RProps

typealias Omit<T, U> = Pick<T, Exclude<Any, Any>>
external interface BsPrefixProps<As : React.ElementType> : RProps {
    var `as`: As? get() = definedExternally; set(value) = definedExternally
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
}
external open class BsPrefixComponent<As : React.ElementType, P> : React.Component<Omit<React.ComponentPropsWithRef<As>, BsPrefixProps<As> /* BsPrefixProps<As> & P */> /* Omit<React.ComponentPropsWithRef<As>, BsPrefixProps<As> /* BsPrefixProps<As> & P */> & BsPrefixProps */>
typealias BsPrefixComponentClass<As, P> = React.ComponentClass<Omit<React.ComponentPropsWithRef<As>, BsPrefixProps<As> /* BsPrefixProps<As> & P */> /* Omit<React.ComponentPropsWithRef<As>, BsPrefixProps<As> /* BsPrefixProps<As> & P */> & BsPrefixProps */>
typealias SelectCallback = (eventKey: String, e: React.SyntheticEvent<Any>) -> Unit
external interface TransitionCallbacks {
    val onEnter: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onEntered: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onEntering: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onExit: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onExited: ((node: HTMLElement) -> Any)? get() = definedExternally
    val onExiting: ((node: HTMLElement) -> Any)? get() = definedExternally
}