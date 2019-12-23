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

external open class NavbarText<As : React.ElementType> : BsPrefixComponent<As>
external interface NavbarProps {
    var variant: dynamic /* 'light' | 'dark' */
    var expand: dynamic /* Boolean | 'sm' | 'md' | 'lg' | 'xl' */
    var bg: String? get() = definedExternally; set(value) = definedExternally
    var fixed: dynamic /* 'top' | 'bottom' */
    var sticky: dynamic /* 'top' | 'bottom' */
    var onToggle: ((expanded: Boolean) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
    var collapseOnSelect: Boolean? get() = definedExternally; set(value) = definedExternally
    var expanded: Boolean? get() = definedExternally; set(value) = definedExternally
    var role: String? get() = definedExternally; set(value) = definedExternally
}
external open class Navbar<As : React.ElementType> : BsPrefixComponent<As, NavbarProps> {
    companion object {
        var Brand: Any
        var Toggle: Any
        var Collapse: Any
        var Text: Any
    }
}