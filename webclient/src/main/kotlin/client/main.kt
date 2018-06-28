package client

import client.cordova.initializeForCordova
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

val appName = "ZZZAppNameZZZ"
val appNameForFilesystem = appName.replace(Regex("\\W"), "").toLowerCase()
val page: HTMLDivElement? = document.getElementById("page") as HTMLDivElement?

fun setChildWithoutSplash(element: HTMLDivElement, parentDiv: HTMLDivElement) {
    page?.addClass2("hide-splash")
    parentDiv.setChild(element, Fade())
}

object UI {
    val toDoId = Property<ID<ToDo>?>(null)
    val toDosScreen: HTMLDivElement = inContext("toDosScreen") { toDosScreen(ToDosModel()) }
    val toDoModel = ToDoModel(toDoId)
    val toDoScreen: HTMLDivElement = inContext("toDoScreen") { toDoScreen(toDoModel) }
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
        page?.with { addClass2("container-fluid")
            val divContainer: HTMLDivElement = div()

            var previousHash = ""

            window.location.splitHashProperty.async().onNext { hash ->
                inContext("About to draw '${hash.joinToString("/")}'") { console.info("About to draw '${hash.joinToString("/")}'") }
                val firstHash = hash[0]
                when (firstHash) {
//                    "#accessSpace" -> {
//                        val newHash = Factory.accessSpaceModel.addIfMissingAndExtractNewHash(hash)
//                        window.history.clearDestinationBack()
//                        window.location.hash = newHash
//                    }
                    "#toDo" -> {
                        setChildWithoutSplash(UI.toDoScreen, divContainer)
                        UI.toDoModel.setFromHash(hash)
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
