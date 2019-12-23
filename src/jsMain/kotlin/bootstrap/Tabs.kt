@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface TabsProps : RProps {
    var activeKey: Any? get() = definedExternally; set(value) = definedExternally
    var defaultActiveKey: Any? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var variant: String /* 'tabs' | 'pills' */
    var transition: dynamic /* false | React.ElementType */
    var id: String
    var mountOnEnter: Boolean? get() = definedExternally; set(value) = definedExternally
    var unmountOnExit: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class Tabs<As : React.ElementType> : BsPrefixComponent<As, TabsProps>