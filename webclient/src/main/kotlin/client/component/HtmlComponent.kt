package client.component

import org.w3c.dom.HTMLElement
import kotlin.dom.addClass
import kotlin.dom.hasClass
import kotlin.dom.removeClass

/**
 * External properties and functions for [net.yested.Component] and [org.w3c.dom.html.HTMLElement]
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/16/16
 * Time: 8:38 PM
 */
var HTMLElement.visible: Boolean
    get() = !hasClass("hidden")
    set(value) {
        if (value != visible) {
            if (value) {
                removeClass("hidden")
            } else {
                addClass("hidden")
            }
        }
    }
