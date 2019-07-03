package client.component

/**
 * An icon from simpleicon's flaticon.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 7:06 PM
 */

import net.yested.core.html.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLSpanElement

fun HTMLElement.flaticon(icon: String): HTMLSpanElement {
    return span { className = "flaticon flaticon-${icon}" }
}
