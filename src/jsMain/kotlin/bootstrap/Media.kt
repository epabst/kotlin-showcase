@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

abstract external class MediaBody<As : React.ElementType> : BsPrefixComponent<As, RProps>
abstract external class Media<As : React.ElementType> : BsPrefixComponent<As, RProps> {
    companion object {
        var Body: Any
    }
}