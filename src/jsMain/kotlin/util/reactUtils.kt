@file:Suppress("unused")

package util

import bootstrap.Navbar
import bootstrap.NavbarProps
import react.RBuilder
import react.RElementBuilder
import react.ReactElement

enum class NavbarPlacement {
    Top, Bottom
}

fun RBuilder.navbar(fixed: NavbarPlacement? = null, handler: RElementBuilder<NavbarProps>.() -> Unit): ReactElement {
    val navbar = child(Navbar::class) {
        attrs.fixed = fixed?.name?.toLowerCase()
        handler()
    }
    if (fixed == NavbarPlacement.Top) {
        child(Navbar::class) {
            attrs.className = (attrs.className ?: "") + " invisible"
            // take up the same much space for the following content
            handler()
        }
    }
    return navbar
}
