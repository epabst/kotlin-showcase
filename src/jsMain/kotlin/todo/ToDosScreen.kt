package todo

import bootstrap.*
import component.ButtonBar
import component.UndoButtons
import component.entity.*
import react.*
import react.dom.br
import react.dom.div
import react.router.dom.RouteResultHistory
import react.router.dom.routeLink
import platform.launchHandlingErrors
import pouchdb.Document
import pouchdb.core.Changes
import todo.model.ToDo
import todo.model.ToDoJS
import todo.model.parse
import todo.model.toNormal

interface ToDosProps : RProps {
    var history: RouteResultHistory
}

interface ToDosState : RState {
    var list: List<ToDo>?
}

/**
 * UI for showing a list of ToDos.
 * @author Eric Pabst (epabst@gmail.com)
 */
class ToDosScreen(props: ToDosProps) : RComponent<ToDosProps, ToDosState>(props) {

    private val resources = mutableListOf<Changes<*>>()

    override fun ToDosState.init(props: ToDosProps) {
        list = null
    }

    override fun componentDidMount() {
        resources.add(Config.toDoDb.onChangedMap(Document<ToDoJS>::parse) { toDoById ->
            setState { list = toDoById.values.toList() }
        })
    }

    override fun componentWillUnmount() {
        resources.forEach { it.cancel() }
    }

    private suspend fun delete(todo: ToDo) {
        Config.toDoDb.removeAllowingUndo(todo, ToDoJS::toNormal)
    }

    override fun RBuilder.render() {
        child(ButtonBar::class) {
            attrs.heading = "To-Dos"
        }
        val list = state.list
        child(Container::class) {
            if (list == null) {
                div { +"Loading..." }
            } else {
                child(Row::class) {
                    child(Col::class) { +"Description" }
                    child(Col::class) {
                        //                    +"Due Date"
                    }
                    child(Col::class) {}
                }
                if (list.isNotEmpty()) {
                    list.forEach { toDo ->
                        child(Card::class) {
                            attrs.body = true
                            child(Row::class) {
                                attrs.onClick = {
                                    props.history.push("/toDos/${toDo.id}")
                                }
                                child(Col::class) {
                                    div("text-left name") { +toDo.name }
                                }
                                child(Col::class) {
                                    div("text-left name") {
                                        +(toDo.dueDate?.toDisplayDateTimeString() ?: "")
                                    }
                                }
                                child(Col::class) {
                                    child(Button::class) {
                                        attrs.variant = "secondary"
                                        attrs.onClick =
                                            { it.stopPropagation(); launchHandlingErrors("delete $toDo") { delete(toDo) } }
                                        +"Delete"
                                    }
                                }
                            }
                        }
                    }
                } else {
                    child(Row::class) {
                        child(Col::class) {
                            attrs.xs = 12
                            child(Card::class) {
                                attrs.body = true
                                +"There are no to-dos."
                            }
                        }
                    }
                }
                br {}
                br {}
                br {}
                br {}
                br {}
                child(Row::class) {
                    child(Col::class) {
                        attrs.xs = 6
                        child(UndoButtons::class) { }
                    }
                    child(Col::class) {
                        attrs.xs = 6
                        routeLink(to = "/toDos/new") {
                            child(Button::class) {
                                +"Add ToDo"
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.toDosScreen(history: RouteResultHistory): ReactElement = child(ToDosScreen::class) {
    attrs.history = history
}
