@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ListGroupProps : RProps {
    var variant: String /* 'flush' */
    var activeKey: Any? get() = definedExternally; set(value) = definedExternally
    var defaultActiveKey: Any? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
}
abstract external class ListGroup<As : React.ElementType> : BsPrefixComponent<As, ListGroupProps> {
    companion object {
        var Item: Any
    }
}