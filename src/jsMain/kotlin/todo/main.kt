package todo

import component.entity.toID
import platform.handleError
import platform.inContext
import cordova.initializeForCordova
import org.w3c.dom.Element
import react.RProps
import react.dom.*
import react.router.dom.*
import kotlin.browser.document

const val appName = "ZZZAppNameZZZ"
val appNameForFilesystem = appName.replace(Regex("\\W"), "").toLowerCase()

interface IdProps : RProps {
    var id: String
}

fun main() {
    try {
        inContext("initializeForCordova") { initializeForCordova() }

        val container = document.getElementById("page")
        if (container !is Element) return
        render(container) {
            hashRouter {
                switch {
                    redirect("/", exact = true, to = "/toDos")
                    route<RProps>(path = "/toDos", exact = true) { props ->
                        toDosScreen(props.history)
                    }
                    route<IdProps>(path = "/toDos/:id") { props ->
                        val id = props.match.params.id
                        toDoScreen(if (id == "new") null else id.toID(), props.history)
                    }
                }
            }
        }
    } catch (e: Throwable) {
        handleError(e)
    }
}
