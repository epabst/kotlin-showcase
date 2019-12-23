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

external open class DropdownDivider<As : React.ElementType> : BsPrefixComponent<As>
external open class DropdownHeader<As : React.ElementType> : BsPrefixComponent<As>
external interface `T$0` {
    var source: dynamic /* 'select' | 'click' | 'rootClose' | 'keydown' */
}
external interface DropdownProps {
    var drop: dynamic /* 'up' | 'left' | 'right' | 'down' */
    var alignRight: Boolean? get() = definedExternally; set(value) = definedExternally
    var show: Boolean? get() = definedExternally; set(value) = definedExternally
    var flip: Boolean? get() = definedExternally; set(value) = definedExternally
    var onToggle: ((isOpen: Boolean, event: React.SyntheticEvent<Dropdown>, metadata: `T$0`) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var onSelect: SelectCallback? get() = definedExternally; set(value) = definedExternally
}
external open class Dropdown<As : React.ElementType> : BsPrefixComponent<As, DropdownProps> {
    companion object {
        var Toggle: Any
        var Menu: Any
        var Item: Any
        var Divider: Any
        var Header: Any
    }
}