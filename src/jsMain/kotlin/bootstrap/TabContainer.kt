@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RProps
import react.RState

external interface TabContainerProps : RProps {
    var id: String? get() = definedExternally; set(value) = definedExternally
    var transition: dynamic /* false | React.ElementType */
    var mountOnEnter: Boolean? get() = definedExternally; set(value) = definedExternally
    var unmountOnExit: Boolean? get() = definedExternally; set(value) = definedExternally
    var generateChildId: ((eventKey: Any, type: String /* 'tab' | 'pane' */) -> String)? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var activeKey: Any? get() = definedExternally; set(value) = definedExternally
    var defaultActiveKey: Any? get() = definedExternally; set(value) = definedExternally
}
abstract external class TabContainer : Component<TabContainerProps, RState>