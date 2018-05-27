package client.component

import net.yested.core.html.addClass2
import net.yested.core.html.hasClass2
import net.yested.core.html.removeClass2
import net.yested.ext.bootstrap3.ColumnDefinition
import org.w3c.dom.HTMLElement

/**
 * External properties and functions for [net.yested.Component] and [org.w3c.dom.html.HTMLElement]
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/16/16
 * Time: 8:38 PM
 */
var HTMLElement.visible: Boolean
    get() = !hasClass2("hidden")
    set(value) {
        if (value != visible) {
            if (value) {
                removeClass2("hidden")
            } else {
                addClass2("hidden")
            }
        }
    }

var HTMLElement.responsiveWidth: ColumnDefinition
    get() = error("not implemented")
    set(value) {
        addClass2(value.css)
    }
