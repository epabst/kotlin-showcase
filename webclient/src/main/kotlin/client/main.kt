package client

import client.util.*
import common.PlatformProvider
import common.ID
import common.util.inContext
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.jquery.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.Location
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.addClass

val page: HTMLDivElement = document.getElementById("page")!! as HTMLDivElement

fun setChildWithoutSplash(element: HTMLDivElement, parentDiv: HTMLDivElement) {
    page.addClass("hide-splash")
    parentDiv.setChild(element, Fade())
}

object UI {
    val toDoId = Property<ID?>(null)
    val toDo = toDoId.mapAsDefault { it?.let { Factory.toDoRepository.find(it) } }
    val toDoMasterScreen: HTMLDivElement by lazy { inContext("toDoMasterScreen") { toDoMasterScreen(ToDoMasterModel()) } }
    val toDoDetailScreen: HTMLDivElement by lazy { inContext("toDoDetailScreen") { toDoDetailScreen(ToDoDetailModel(toDo)) } }
    val backHash = Property<String?>(null)
    val showUndo = true.toProperty()
    var windowLocation: Location = window.location
    var windowHistory: History = BrowserHistory
    fun back() {
        windowHistory.backToHash(backHash.get())
    }
    fun back(count: Int) {
        windowHistory.go(-count)
    }
}

/**
 * The main entrypoint of the app.
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
            inContext("buttonBar") { buttonBar(UI.backHash) }
            val divContainer: HTMLDivElement = div()

            var previousHash = ""

            registerHashChangeListener { hash ->
                console.info("new window.location.hash=$hash")
                inContext("hash=$hash") { console.info("new window.location.hash=$hash") }
                var showUndo = true
                when (hash[0]) {
                    "#toDos", "#", "" -> {
                        setChildWithoutSplash(UI.toDoMasterScreen, divContainer)
                        UI.backHash.set(null)
                    }
                    "#toDo" -> {
                        setChildWithoutSplash(UI.toDoDetailScreen, divContainer)
                        val toDoId = if (hash.size > 1) hash[1].toID() else null
                        UI.backHash.set(ToDoMasterModel.toUrl())
                        UI.toDoId.set(toDoId)
                    }
                }
                UI.showUndo.set(showUndo)
                if (hash.get(0) != previousHash) {
                    window.scrollTo(0.0, 0.0)
                }
                previousHash = hash[0]
            }
        }
    } catch (e: Throwable) {
        handleError(e)
    }
}
