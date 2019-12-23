@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
@file:JsModule("react-bootstrap")
package bootstrap

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external open class CardTitle<As : React.ElementType> : BsPrefixComponent<As>
external open class CardSubtitle<As : React.ElementType> : BsPrefixComponent<As>
external open class CardBody<As : React.ElementType> : BsPrefixComponent<As>
external open class CardLink<As : React.ElementType> : BsPrefixComponent<As>
external open class CardText<As : React.ElementType> : BsPrefixComponent<As>
external open class CardHeader<As : React.ElementType> : BsPrefixComponent<As>
external open class CardFooter<As : React.ElementType> : BsPrefixComponent<As>
external open class CardImgOverlay<As : React.ElementType> : BsPrefixComponent<As>
external interface CardProps {
    var bg: dynamic /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' */
    var text: dynamic /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' | 'white' | 'muted' */
    var border: dynamic /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' */
    var body: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class Card<As : React.ElementType> : BsPrefixComponent<As, CardProps> {
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