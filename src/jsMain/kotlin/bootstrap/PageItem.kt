@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface PageItemProps : RProps {
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var activeLabel: String? get() = definedExternally; set(value) = definedExternally
}
abstract external class PageItem : BsPrefixComponent<React.Any, PageItemProps>
abstract external class First : PageItem
abstract external class Prev : PageItem
abstract external class Ellipsis : PageItem
abstract external class Next : PageItem
abstract external class Last : PageItem