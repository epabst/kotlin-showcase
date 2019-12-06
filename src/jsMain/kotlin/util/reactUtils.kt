@file:Suppress("unused")

package util

import bootstrap.Navbar
import react.RBuilder
import react.ReactElement
import react.dom.div

enum class NavbarPlacement {
    Top, Bottom
}

fun RBuilder.navbar(fixed: NavbarPlacement? = null, handler: RBuilder.() -> Unit): ReactElement {
    val navbar = child(Navbar::class) {
        attrs.fixed = fixed?.name?.toLowerCase()
        handler()
    }
    if (fixed == NavbarPlacement.Top) {
        div("invisible") {
            // take up the same much space for the following content
            handler()
        }
    }
    return navbar
}
