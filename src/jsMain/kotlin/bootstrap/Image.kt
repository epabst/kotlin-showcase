@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ImageProps : RProps {
    var fluid: Boolean? get() = definedExternally; set(value) = definedExternally
    var rounded: Boolean? get() = definedExternally; set(value) = definedExternally
    var roundedCircle: Boolean? get() = definedExternally; set(value) = definedExternally
    var thumbnail: Boolean? get() = definedExternally; set(value) = definedExternally
}
abstract external class Image : BsPrefixComponent<React.String /* 'img' */, ImageProps>