package component

/**
 * An icon from simpleicon's flaticon.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 7:06 PM
 */

import react.RBuilder
import react.ReactElement
import react.dom.span

fun RBuilder.flaticon(icon: String): ReactElement {
    return span("flaticon flaticon-${icon}") {}
}
