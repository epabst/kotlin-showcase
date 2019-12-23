@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface AccordionProps : RProps {
    var activeKey: String? get() = definedExternally; set(value) = definedExternally
    var defaultActiveKey: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class Accordion<As : React.ElementType> : BsPrefixComponent<As, AccordionProps> {
    companion object {
        var Toggle: Any
        var Collapse: Any
    }
}