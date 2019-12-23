@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface TableProps : RProps {
    var striped: Boolean? get() = definedExternally; set(value) = definedExternally
    var bordered: Boolean? get() = definedExternally; set(value) = definedExternally
    var borderless: Boolean? get() = definedExternally; set(value) = definedExternally
    var hover: Boolean? get() = definedExternally; set(value) = definedExternally
    var size: String? get() = definedExternally; set(value) = definedExternally
    var variant: String? get() = definedExternally; set(value) = definedExternally
    var responsive: dynamic /* Boolean | String */
}
abstract external class Table : BsPrefixComponent<React.String /* 'table' */, TableProps>