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

external open class InputGroupAppend<As : React.ElementType> : BsPrefixComponent<As>
external open class InputGroupPrepend<As : React.ElementType> : BsPrefixComponent<As>
external open class InputGroupText<As : React.ElementType> : BsPrefixComponent<As>
external open class InputGroupCheckbox : BsPrefixComponent<String /* 'input' */>
external open class InputGroupRadio : BsPrefixComponent<String /* 'input' */>
external interface InputGroupProps {
    var size: dynamic /* 'sm' | 'lg' */
}
external open class InputGroup<As : React.ElementType> : BsPrefixComponent<As, InputGroupProps> {
    companion object {
        var Append: Any
        var Prepend: Any
        var Text: Any
        var Checkbox: Any
        var Radio: Any
    }
}