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

external interface NavProps {
    var navbarBsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var cardHeaderBsPrefix: String? get() = definedExternally; set(value) = definedExternally
    var variant: dynamic /* 'tabs' | 'pills' */
    var activeKey: Any? get() = definedExternally; set(value) = definedExternally
    var defaultActiveKey: Any? get() = definedExternally; set(value) = definedExternally
    var fill: Boolean? get() = definedExternally; set(value) = definedExternally
    var justify: Boolean? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var role: String? get() = definedExternally; set(value) = definedExternally
    var navbar: Boolean? get() = definedExternally; set(value) = definedExternally
    var onKeyDown: React.KeyboardEventHandler<NavProps /* this */>? get() = definedExternally; set(value) = definedExternally
}
external open class Nav<As : React.ElementType> : BsPrefixComponent<As, NavProps> {
    companion object {
        var Item: Any
        var Link: Any
    }
}