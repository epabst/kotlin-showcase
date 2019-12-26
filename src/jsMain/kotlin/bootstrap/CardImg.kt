@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface CardImgProps : RProps {
    var variant: String /* 'top' | 'bottom' */
}
abstract external class CardImg<As : React.ElementType> : BsPrefixComponent<As, CardImgProps>