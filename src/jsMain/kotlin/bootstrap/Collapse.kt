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

external interface CollapseProps : TransitionCallbacks, React.ClassAttributes<Collapse> {
    var `in`: Boolean? get() = definedExternally; set(value) = definedExternally
    var mountOnEnter: Boolean? get() = definedExternally; set(value) = definedExternally
    var unmountOnExit: Boolean? get() = definedExternally; set(value) = definedExternally
    var appear: Boolean? get() = definedExternally; set(value) = definedExternally
    var timeout: Number? get() = definedExternally; set(value) = definedExternally
    var dimension: dynamic /* 'height' | 'width' | () -> dynamic */
    var getDimensionValue: ((dimension: Number, element: React.ReactElement) -> Number)? get() = definedExternally; set(value) = definedExternally
    var role: String? get() = definedExternally; set(value) = definedExternally
}
external open class Collapse : React.Component<CollapseProps>