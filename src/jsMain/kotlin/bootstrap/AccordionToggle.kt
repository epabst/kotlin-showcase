@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface AccordionToggleProps : RProps {
    var `as`: String
    var eventKey: String
    var onClick: ((event: React.SyntheticEvent? /* = null */) -> Unit)? get() = definedExternally; set(value) = definedExternally
}
external fun useAccordionToggle(eventKey: String, onClick: (event: React.SyntheticEvent? /* = null */) -> Unit): (event: React.SyntheticEvent? /* = null */) -> Unit
abstract external class AccordionToggle<As : React.ElementType> : BsPrefixComponent<As, AccordionToggleProps>