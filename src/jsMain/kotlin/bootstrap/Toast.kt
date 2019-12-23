@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ToastProps : RProps {
    var animation: Boolean? get() = definedExternally; set(value) = definedExternally
    var autohide: Boolean? get() = definedExternally; set(value) = definedExternally
    var delay: Number? get() = definedExternally; set(value) = definedExternally
    var onClose: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var transition: dynamic /* Boolean | React.ElementType */
}
abstract external class Toast : BsPrefixComponent<React.String /* 'div' */, ToastProps> {
    companion object {
        var Body: Any
        var Header: Any
    }
}