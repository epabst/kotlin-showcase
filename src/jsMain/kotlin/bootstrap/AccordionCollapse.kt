@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import org.w3c.dom.HTMLDivElement

external interface AccordionCollapseProps : CollapseProps, React.HTMLAttributes<HTMLDivElement> {
    var eventKey: String
}
abstract external class AccordionCollapse : BsPrefixComponent<React.ElementType, AccordionCollapseProps>