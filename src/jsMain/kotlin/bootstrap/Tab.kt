@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

external interface TabProps : React.ComponentPropsWithRef<Any> {
    var eventKey: Any? get() = definedExternally; set(value) = definedExternally
    var title: String
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var tabClassName: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class Tab<As : React.ElementType> : BsPrefixComponent<As, TabProps> {
    companion object {
        var Container: Any
        var Content: Any
        var Pane: Any
    }
}