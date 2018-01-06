package client

import client.cordova.initializeForCordova
import client.util.*
import common.util.PlatformProvider
import common.util.inContext
import net.yested.core.html.*
import net.yested.core.utils.*
import net.yested.ext.jquery.*
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.addClass

val appName = "Jack Dance Toothbrush Time"
val appFilenamePrefix = appName.replace(Regex("\\W"), "").toLowerCase()
val page: HTMLDivElement = document.getElementById("page")!! as HTMLDivElement

fun setChildWithoutSplash(element: HTMLDivElement, parentDiv: HTMLDivElement) {
    page.addClass("hide-splash")
    parentDiv.setChild(element, Fade())
}

object UI {
    val timersScreen: HTMLDivElement by lazy { inContext("timersScreen") { timersScreen(TimersModel()) } }
    val timerModel = TimerModel()
    val timerScreen: HTMLDivElement by lazy { inContext("timerScreen") { timerScreen(timerModel) } }
}

/**
 * The main entry-point of the app.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/17/16
 * Time: 6:25 AM
 */
fun main(args: Array<String>) {
    try {
        inContext("initializeForCordova") { initializeForCordova() }
        PlatformProvider.instance = JavascriptProvider

        //when we have constructed a DOM, we can take a parent element (via div.element)
        //and append it as a child to "page" div in HTML page
        page with { addClass("container-fluid")
            val divContainer: HTMLDivElement = div()

            var previousHash = ""

            window.location.splitHashProperty.onNext { hash ->
                inContext("About to draw '$hash'") { console.info("About to draw '$hash'") }
                val firstHash = hash[0]
                when (firstHash) {
                    "#timers" -> {
                        setChildWithoutSplash(UI.timersScreen, divContainer)
                    }
                    else -> {
                        setChildWithoutSplash(UI.timerScreen, divContainer)
                    }
                }
                if (firstHash != previousHash) {
                    window.scrollTo(0.0, 0.0)
                }
                previousHash = firstHash
            }
        }
    } catch (e: Throwable) {
        handleError(e)
    }
}
