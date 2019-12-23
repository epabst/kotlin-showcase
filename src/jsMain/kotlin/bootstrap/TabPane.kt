@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

external interface TabPaneProps : TransitionCallbacks {
    var eventKey: Any? get() = definedExternally; set(value) = definedExternally
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var transition: dynamic /* false | React.ElementType */
    var bsClass: String? get() = definedExternally; set(value) = definedExternally
    var mountOnEnter: Boolean? get() = definedExternally; set(value) = definedExternally
    var unmountOnExit: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class TabPane<As : React.ElementType> : BsPrefixComponent<As, TabPaneProps>