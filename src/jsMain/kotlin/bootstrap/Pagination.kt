@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface PaginationProps : RProps {
    var size: String /* 'sm' | 'lg' */
}
abstract external class Pagination : BsPrefixComponent<React.String /* 'ul' */, PaginationProps> {
    companion object {
        var First: Any
        var Prev: Any
        var Ellipsis: Any
        var Item: Any
        var Next: Any
        var Last: Any
    }
}