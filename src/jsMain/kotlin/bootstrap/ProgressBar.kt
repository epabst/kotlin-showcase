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

external interface ProgressBarProps {
    var min: Number? get() = definedExternally; set(value) = definedExternally
    var now: Number? get() = definedExternally; set(value) = definedExternally
    var max: Number? get() = definedExternally; set(value) = definedExternally
    var label: React.ReactNode? get() = definedExternally; set(value) = definedExternally
    var srOnly: Boolean? get() = definedExternally; set(value) = definedExternally
    var striped: Boolean? get() = definedExternally; set(value) = definedExternally
    var animated: Boolean? get() = definedExternally; set(value) = definedExternally
    var variant: dynamic /* 'success' | 'danger' | 'warning' | 'info' */
}
external open class ProgressBar : BsPrefixComponent<String /* 'div' */, ProgressBarProps>