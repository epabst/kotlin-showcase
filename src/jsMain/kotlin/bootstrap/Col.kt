@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.RProps

external interface ResponsiveProps {
    var span: dynamic /* true | 'auto' | Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' */
    var offset: dynamic /* Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' */
    var order: dynamic /* Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' */
}
external interface ColProps : RProps {
    var xs: dynamic /* true | 'auto' | Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | ResponsiveSpec */
    var sm: dynamic /* true | 'auto' | Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | ResponsiveSpec */
    var md: dynamic /* true | 'auto' | Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | ResponsiveSpec */
    var lg: dynamic /* true | 'auto' | Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | ResponsiveSpec */
    var xl: dynamic /* true | 'auto' | Number | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | ResponsiveSpec */
}
abstract external class Col<As : React.ElementType> : BsPrefixComponent<As, ColProps>