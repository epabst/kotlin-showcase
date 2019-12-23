@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
@file:JsModule("react-bootstrap")
package bootstrap

import react.Component
import react.RProps
import react.RState

external interface ThemeProviderProps : RProps {
    var prefixes: Any?
}
abstract external class ThemeProvider : Component<ThemeProviderProps, RState>
