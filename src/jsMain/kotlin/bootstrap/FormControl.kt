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

external interface FormControlProps {
    var innerRef: React.LegacyRef<dynamic /* HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement */>? get() = definedExternally; set(value) = definedExternally
    var size: dynamic /* 'sm' | 'lg' */
    var plaintext: Boolean? get() = definedExternally; set(value) = definedExternally
    var readOnly: Boolean? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var value: String? get() = definedExternally; set(value) = definedExternally
    var onChange: React.FormEventHandler<dynamic /* HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement */>? get() = definedExternally; set(value) = definedExternally
    var type: String? get() = definedExternally; set(value) = definedExternally
    var id: String? get() = definedExternally; set(value) = definedExternally
    var isValid: Boolean? get() = definedExternally; set(value) = definedExternally
    var isInvalid: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class FormControl<As : React.ElementType> : BsPrefixComponent<As, FormControlProps> {
    companion object {
        var Feedback: Any
    }
}