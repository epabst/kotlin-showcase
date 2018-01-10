package client.component

import net.yested.core.html.tag
import net.yested.ext.bootstrap3.ColumnDefinition
import org.w3c.dom.*
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

var HTMLElement.responsiveWidth: ColumnDefinition
    get() = error("not implemented")
    set(value) {
        addClass(value.css)
    }

fun HTMLElement.iframe(init:(HTMLIFrameElement.()->Unit)? = null) = tag(this, tagName = "iframe", init = init)
fun HTMLElement.video(init:(HTMLVideoElement.()->Unit)? = null) = tag(this, tagName = "video", init = init)
fun HTMLElement.audio(init:(HTMLAudioElement.()->Unit)? = null) = tag(this, tagName = "audio", init = init)
