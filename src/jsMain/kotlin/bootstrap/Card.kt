@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

abstract external class CardTitle<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class CardSubtitle<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class CardBody<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class CardLink<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class CardText<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class CardHeader<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class CardFooter<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class CardImgOverlay<As : React.ElementType> : BsPrefixComponent<As, RProps>
external interface CardProps : RProps {
    var bg: String /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' */
    var text: String /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' | 'white' | 'muted' */
    var border: String /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' */
    var body: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class Card<As : React.ElementType> : BsPrefixComponent<As, CardProps> {
    companion object {
        var Img: Any
        var Title: Any
        var Subtitle: Any
        var Body: Any
        var Link: Any
        var Text: Any
        var Header: Any
        var Footer: Any
        var ImgOverlay: Any
    }
}