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

external interface `T$0` {
    var show: Number
    var hide: Number
}
external interface OverlayTriggerProps : React.ComponentPropsWithRef<Any> {
    var children: React.ReactNode
    var trigger: dynamic /* 'hover' | 'click' | 'focus' | Array<dynamic /* 'hover' | 'click' | 'focus' */> */
    var delay: dynamic /* Number | `T$0` */
    var defaultShow: Boolean? get() = definedExternally; set(value) = definedExternally
    var flip: Boolean? get() = definedExternally; set(value) = definedExternally
    var overlay: dynamic /* React.ReactNode | () -> React.ReactNode */
    var target: Any? get() = definedExternally; set(value) = definedExternally
    var onHide: Any? get() = definedExternally; set(value) = definedExternally
    var show: Any? get() = definedExternally; set(value) = definedExternally
}
external open class OverlayTrigger : React.Component<OverlayTriggerProps>