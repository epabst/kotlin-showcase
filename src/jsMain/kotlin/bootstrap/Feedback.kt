@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface FeedbackProps : RProps {
    var bsPrefix: Any? get() = definedExternally; set(value) = definedExternally
    var type: String /* 'valid' | 'invalid' */
}
abstract external class Feedback<As : React.ElementType> : BsPrefixComponent<As, FeedbackProps>
