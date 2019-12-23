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

external open class FormRow<As : React.ElementType> : BsPrefixComponent<As>
external interface FormProps {
    var innerRef: React.LegacyRef<FormProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var inline: Boolean? get() = definedExternally; set(value) = definedExternally
    var validated: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class Form<As : React.ElementType> : BsPrefixComponent<As, FormProps> {
    companion object {
        var Row: Any
        var Group: Any
        var Control: Any
        var Check: Any
        var Label: Any
        var Text: Any
    }
}