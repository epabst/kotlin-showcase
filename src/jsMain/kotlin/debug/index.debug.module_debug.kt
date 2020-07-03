@file:JsQualifier("debug")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION",
    "DEPRECATION"
)
package debug

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

external interface Debug {
    @nativeInvoke
    operator fun invoke(namespace: String): Debugger
    var coerce: (`val`: Any) -> Any
    var disable: () -> String
    var enable: (namespaces: String) -> Unit
    var enabled: (namespaces: String) -> Boolean
    var log: (args: Any) -> Any
    var names: Array<RegExp>
    var skips: Array<RegExp>
    var formatters: Formatters
}

external interface Formatters {
    @nativeGetter
    operator fun get(formatter: String): ((v: Any) -> String)?
    @nativeSetter
    operator fun set(formatter: String, value: (v: Any) -> String)
}

external interface Debugger {
    @nativeInvoke
    operator fun invoke(formatter: Any, vararg args: Any)
    var color: String
    var enabled: Boolean
    var log: (args: Any) -> Any
    var namespace: String
    var destroy: () -> Boolean
    var extend: (namespace: String, delimiter: String? /* = null */) -> Debugger
}