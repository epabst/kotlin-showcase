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

external open class AlertLink<As : React.ElementType> : BsPrefixComponent<As>
external open class AlertHeading<As : React.ElementType> : BsPrefixComponent<As>
external interface AlertProps : React.HTMLProps<HTMLDivElement> {
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var variant: dynamic /* 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info' | 'dark' | 'light' */
    var dismissible: Boolean? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var onClose: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
    var closeLabel: String? get() = definedExternally; set(value) = definedExternally
    var transition: React.ElementType? get() = definedExternally; set(value) = definedExternally
}
@JsName("default")
external var Alert: React.ForwardRefExoticComponent<AlertProps> /* React.ForwardRefExoticComponent<AlertProps> & `T$0` */