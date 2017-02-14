package client

import jquery.JQuery
import org.w3c.dom.Element
import org.w3c.dom.events.Event

/**
 * Additional @native access to [JQuery] plus some convenience functions.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/5/16
 * Time: 6:26 AM
 */
@native fun JQuery.closest(selector: String): JQuery = noImpl

@native fun JQuery.find(selector: String): JQuery = noImpl

@native fun JQuery.children(selector: String): JQuery = noImpl

/**
 * Slide up the table row (tr) that is closest to this event.
 * To animate smoothly, each [td] needs to have a [div] in it, which can easily be done using [AnimatableColumn].
 */
fun Event.slideUpRow() {
    val eventTarget = target
    if (eventTarget is Element) {
        val tdElements = jquery.jq(eventTarget).closest("tr").children("td")
        tdElements.children("*").slideUp()
        tdElements.slideUp()
    }
}
