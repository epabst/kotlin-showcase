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

external interface CarouselProps {
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var slide: Boolean? get() = definedExternally; set(value) = definedExternally
    var fade: Boolean? get() = definedExternally; set(value) = definedExternally
    var wrap: Boolean? get() = definedExternally; set(value) = definedExternally
    var indicators: Boolean? get() = definedExternally; set(value) = definedExternally
    var interval: Number? get() = definedExternally; set(value) = definedExternally
    var controls: Boolean? get() = definedExternally; set(value) = definedExternally
    var pauseOnHover: Boolean? get() = definedExternally; set(value) = definedExternally
    var keyboard: Boolean? get() = definedExternally; set(value) = definedExternally
    var onSelect: ((eventKey: Any, direction: dynamic /* 'prev' | 'next' */, event: Any?) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onSlideEnd: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
    var activeIndex: Number? get() = definedExternally; set(value) = definedExternally
    var prevIcon: React.ReactNode? get() = definedExternally; set(value) = definedExternally
    var prevLabel: String? get() = definedExternally; set(value) = definedExternally
    var nextIcon: React.ReactNode? get() = definedExternally; set(value) = definedExternally
    var nextLabel: String? get() = definedExternally; set(value) = definedExternally
    var touch: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class Carousel<As : React.ElementType> : BsPrefixComponent<As, CarouselProps> {
    companion object {
        var Item: Any
        var Caption: Any
    }
}