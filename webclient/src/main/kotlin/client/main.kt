package client

import client.util.*
import common.ToDo
import common.util.PlatformProvider
import common.util.ID
import common.util.inContext
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.jquery.*
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.addClass

val page: HTMLDivElement = document.getElementById("page")!! as HTMLDivElement

fun setChildWithoutSplash(element: HTMLDivElement, parentDiv: HTMLDivElement) {
    page.addClass("hide-splash")
    parentDiv.setChild(element, Fade())
}

object UI {
    val toDoId = Property<ID<ToDo>?>(null)
    val toDosScreen: HTMLDivElement by lazy { inContext("toDosScreen") { toDosScreen(ToDosModel()) } }
    val toDoScreen: HTMLDivElement by lazy { inContext("toDoScreen") { toDoScreen(ToDoModel(toDoId)) } }
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
                    "#toDo" -> {
                        setChildWithoutSplash(UI.toDoScreen, divContainer)
                        val toDoId: ID<ToDo>? = if (hash.size > 1) hash[1].toID() else null
                        UI.toDoId.set(toDoId)
                    }
                    else -> {
                        setChildWithoutSplash(UI.toDosScreen, divContainer)
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
