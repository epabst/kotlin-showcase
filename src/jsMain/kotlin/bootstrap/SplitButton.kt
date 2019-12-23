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

typealias PropsFromToggle = Partial<Pick<React.ComponentPropsWithRef<Any>, dynamic /* 'size' | 'variant' | 'disabled' */>>
external interface SplitButtonProps : PropsFromToggle {
    var id: dynamic /* String | Number */
    var toggleLabel: String? get() = definedExternally; set(value) = definedExternally
    var href: String? get() = definedExternally; set(value) = definedExternally
    var target: String? get() = definedExternally; set(value) = definedExternally
    var onClick: React.MouseEventHandler<SplitButtonProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var title: React.ReactNode
    var menuRole: String? get() = definedExternally; set(value) = definedExternally
    var rootCloseEvent: dynamic /* 'click' | 'mousedown' */
    var bsPrefix: String? get() = definedExternally; set(value) = definedExternally
}
external open class SplitButton : React.Component<ReplaceProps<Any, SplitButtonProps>>