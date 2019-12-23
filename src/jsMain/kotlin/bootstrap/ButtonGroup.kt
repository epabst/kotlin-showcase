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

external interface ButtonGroupProps {
    var role: String? get() = definedExternally; set(value) = definedExternally
    var size: dynamic /* 'sm' | 'lg' */
    var toggle: Boolean? get() = definedExternally; set(value) = definedExternally
    var vertical: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class ButtonGroup<As : React.ElementType> : BsPrefixComponent<As, ButtonGroupProps>
