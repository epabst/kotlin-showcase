package extensions.react

import react.RBuilder
import react.ReactElement
import react.dom.span

/**
 * An icon from simpleicon's flaticon.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 7:06 PM
 */
fun RBuilder.flaticon(icon: String): ReactElement {
    return span("flaticon flaticon-${icon}") {}
}
