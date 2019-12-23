@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ArrowProps {
    var ref: Any
    var style: Any?
}

external interface PopoverProps : RProps {
    var id: dynamic /* String | Number */
    var placement: Placement? get() = definedExternally; set(value) = definedExternally
    var title: React.ReactNode? get() = definedExternally; set(value) = definedExternally
    var arrowProps: ArrowProps? get() = definedExternally; set(value) = definedExternally
    var content: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class Popover : BsPrefixComponent<React.String /* 'div' */, PopoverProps> {
    companion object {
        var Title: Any
        var Content: Any
    }
}