package client

import common.PlatformProvider
import common.ID
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.jquery.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.Location
import kotlin.browser.document
import kotlin.browser.window

val page: HTMLDivElement = document.getElementById("page")!! as HTMLDivElement

object UI {
    val toDoId = Property<ID?>(null)
    val toDo = toDoId.mapAsDefault { it?.let { Factory.toDoRepository.find(it) } }
    val toDoMasterScreen: HTMLDivElement by lazy { toDoMasterScreen(ToDoMasterModel()) }
    val toDoDetailScreen: HTMLDivElement by lazy { toDoDetailScreen(ToDoDetailModel(toDo)) }
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

    initializeForCordova()
    PlatformProvider.instance = JavascriptProvider

    //when we have constructed a DOM, we can take a parent element (via div.element)
    //and append it as a child to "page" div in HTML page
    page with {
        buttonBar(UI.backHash, UI.showUndo)
        val divContainer: HTMLDivElement = div()

        var previousHash = ""

    	registerHashChangeListener { hash ->
            console.info("new window.location.hash=$hash")
            UI.showUndo.set(true)
            when (hash[0]) {
    			"#toDos", "#", "" -> {
                    UI.backHash.set(null)
                    divContainer.setChild(UI.toDoMasterScreen, Fade())
                }
    			"#toDo" -> {
                    val toDoId = if (hash.size > 1) hash[1].toID() else null
                    UI.backHash.set(ToDoMasterModel.toUrl())
                    UI.toDoId.set(toDoId)
                    divContainer.setChild(UI.toDoDetailScreen, Fade())
                }
    		}
            if (hash.get(0) != previousHash) {
                window.scrollTo(0.0, 0.0)
            }
            previousHash = hash[0]
    	}
    }
}
