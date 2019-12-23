@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ResponsiveEmbedProps : RProps {
    var children: React.ReactChild
    var aspectRatio: String /* '21by9' | '16by9' | '4by3' | '1by1' */
}
abstract external class ResponsiveEmbed : BsPrefixComponent<React.String /* 'div' */, ResponsiveEmbedProps>