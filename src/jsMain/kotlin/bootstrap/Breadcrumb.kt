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

external interface BreadcrumbProps {
    var label: String? get() = definedExternally; set(value) = definedExternally
    var listProps: React.OlHTMLAttributes<Any>? get() = definedExternally; set(value) = definedExternally
}
external open class Breadcrumb<As : React.ElementType> : BsPrefixComponent<As, BreadcrumbProps> {
    companion object {
        var Item: Any
    }
}