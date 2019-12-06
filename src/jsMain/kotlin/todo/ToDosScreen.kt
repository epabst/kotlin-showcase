package todo

import bootstrap.*
import component.ButtonBar
import component.FileBackupComponent.backupButton
import react.*
import react.dom.br
import react.dom.div
import react.router.dom.RouteResultHistory
import react.router.dom.routeLink
import component.repository.Closeable
import component.repository.onListChanged
import todo.model.Factory
import todo.model.ToDo

interface ToDosProps : RProps {
    var history: RouteResultHistory
}

interface ToDosState : RState {
    var list: List<ToDo>
}

/**
 * UI for showing a list of ToDos.
 * @author Eric Pabst (epabst@gmail.com)
 */
class ToDosScreen(props: ToDosProps) : RComponent<ToDosProps, ToDosState>(props) {

    private val resources = mutableListOf<Closeable>()

    override fun ToDosState.init(props: ToDosProps) {
        list = Factory.toDoRepository.list()
    }

    override fun componentDidMount() {
        resources.add(Factory.toDoRepository.onListChanged { _, newList -> setState { list = newList } })
    }

    override fun componentWillUnmount() {
        resources.forEach { it.close() }
    }

    private fun delete(todo: ToDo) {
        Factory.toDoRepository.remove(todo)
    }

    override fun RBuilder.render() {
        child(ButtonBar::class) {
            attrs.heading = "To-Dos"
        }
        child(Container::class) {
            child(Row::class) {
                child(Col::class) { +"Description" }
                child(Col::class) {
//                    +"Due Date"
                }
                child(Col::class) {}
            }
            if (state.list.isNotEmpty()) {
                state.list.forEach { toDo ->
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
                                    attrs.onClick = { it.stopPropagation(); delete(toDo) }
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
                    attrs.xs = 14
                    attrs.sm = 18
                    backupButton()
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

fun RBuilder.toDosScreen(history: RouteResultHistory): ReactElement = child(ToDosScreen::class) {
    attrs.history = history
}
