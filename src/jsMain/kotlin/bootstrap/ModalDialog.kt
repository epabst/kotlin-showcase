@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ModalDialogProps : RProps {
    var size: String /* 'sm' | 'lg' | 'xl' */
    var centered: Boolean? get() = definedExternally; set(value) = definedExternally
    var scrollable: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class ModalDialog : BsPrefixComponent<React.String /* 'div' */, ModalDialogProps>