@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import org.w3c.dom.HTMLDivElement
import react.RProps

abstract external class AlertLink<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class AlertHeading<As : React.ElementType> : BsPrefixComponent<As, RProps>
external interface AlertProps : React.HTMLProps<HTMLDivElement> {
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var variant: String /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' */
    var dismissible: Boolean? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var onClose: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
    var closeLabel: String? get() = definedExternally; set(value) = definedExternally
    var transition: React.ElementType? get() = definedExternally; set(value) = definedExternally
}
abstract external class Alert : React.ForwardRefExoticComponent<AlertProps>
