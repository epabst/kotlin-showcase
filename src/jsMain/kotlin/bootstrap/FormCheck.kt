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

external interface FormCheckProps {
    var bsCustomPrefix: String? get() = definedExternally; set(value) = definedExternally
    var innerRef: React.LegacyRef<FormCheckProps /* this */>? get() = definedExternally; set(value) = definedExternally
    var id: String? get() = definedExternally; set(value) = definedExternally
    var inline: Boolean? get() = definedExternally; set(value) = definedExternally
    var disabled: Boolean? get() = definedExternally; set(value) = definedExternally
    var title: String? get() = definedExternally; set(value) = definedExternally
    var label: React.ReactNode? get() = definedExternally; set(value) = definedExternally
    var custom: Boolean? get() = definedExternally; set(value) = definedExternally
    var type: dynamic /* 'checkbox' | 'radio' | 'switch' */
    var isValid: Boolean? get() = definedExternally; set(value) = definedExternally
    var isInvalid: Boolean? get() = definedExternally; set(value) = definedExternally
    var feedback: React.ReactNode? get() = definedExternally; set(value) = definedExternally
}
external open class FormCheck<As : React.ElementType> : BsPrefixComponent<As, FormCheckProps> {
    companion object {
        var Input: Any
        var Label: Any
    }
}