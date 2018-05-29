package client

import client.component.FileBackupComponent.backupButton
import client.component.responsiveWidth
import client.component.undoComponent
import client.component.visible
import client.util.CachingRepository
import common.ToDo
import common.util.inContext
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.bootstrap3.*
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import kotlin.browser.window
import kotlin.dom.appendText

/**
 * UI for showing a list of ToDos.
 * @author Eric Pabst (epabst@gmail.com)
 */
class ToDosModel(val repository: CachingRepository<ToDo> = Factory.toDoRepository) {
    val data = repository.listProperty()
    val currentSort = Property<SortSpecification<ToDo>?>(null)

    fun delete(todo: ToDo) {
        repository.remove(todo)
    }

    companion object {
        fun toUrl(): String = "#toDos"
    }
}

fun toDosScreen(model: ToDosModel, animate: Boolean = true): HTMLDivElement {
    return Div {
        inContext("buttonBar") { buttonBar(null.toProperty(), "To-Do List".toProperty()) }
        div { addClass2("container-fluid")
            className = "table-responsive"
            table {
                className = "table table-striped table-hover table-condensed"
                thead {
                    addClass2("hidden-xxs hidden-tn")
                    tr {
                        th {
                            Col.Width.Sm(8) and Col.Width.Xs(8)
                            addClass2("text-left")
                            sortControlWithArrow(model.currentSort, compareBy { it.name }, sortNow = true) { appendText("ToDo") }
                        }
                        th {
                            Col.Width.Sm(2) and Col.Width.Xs(2)
                            addClass2("text-right")
                            sortControlWithArrow(model.currentSort, compareBy { it.dueDate }) {
                                div { appendText("Due Date") }
                            }
                        }
                        th {
                            Col.Width.Sm(2) and Col.Width.Xs(2)
                            addClass2("text-right")
                        }
                    }
                }
                tbody(model.data.sortedWith(model.currentSort), effect = if (animate) Collapse() else NoEffect) { item ->
                    tr {
                        td {
                            responsiveWidth = Col.Width.Sm(8) and Col.Width.Xs(7) and Col.Width.Xxs(9) and Col.Width.Tn(12)
                            div {
                                className = "text-left name"
                                editOnClick(item) { textContent = it.name }
                            }
                            div {
                                className = "text-left hidden-lg hidden-md hidden-sm hidden-xs"
                                editOnClick(item) { textContent = (it.dueDate ?: "").toString() }
                            }
                        }
                        td {
                            responsiveWidth = Col.Width.Sm(2) and Col.Width.Xs(3)
                            addClass2("hidden-xxs hidden-tn")
                            div {
                                addClass2("text-right")
                                editOnClick(item) { textContent = (it.dueDate ?: "").toString() }
                            }
                        }
                        td {
                            responsiveWidth = Col.Width.Sm(2) and Col.Width.Xs(2) and Col.Width.Xxs(3) and Col.Width.Tn(12)
                            div {
                                className = "text-right"
                                btsButton(onclick = { model.delete(item) }) {
                                    appendText("Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
        row {
            col(Col.Width.Xs(12)) {
                model.data.onNext { visible = it.isEmpty() }
                appendText("There are no to-dos.")
            }
        }
        br()
        br()
        br()
        br()
        br()
        navbar(NavbarCompletePosition.FixedBottom, containerWidth = ContainerWidth.Fluid) {
            navbarContainer.row {
                col(Col.Width.Tn(5) and Col.Width.Xxs(3) and Col.Width.Xs(3) and Col.Width.Sm(5)) {
                    undoComponent()
                }
                col(Col.Width.Tn(7) and Col.Width.Xxs(9) and Col.Width.Xs(9)) {
                    addClass2("text-right hidden-sm hidden-md hidden-lg")
                    backupButton()
                    btsButton(onclick = { window.location.hash = ToDoModel.toUrl(null) }) {
                        appendText("Add")
                        span { addClass2("hidden-tn"); nbsp(); appendText("To-Do") }
                    }
                }
                col(Col.Width.Sm(7)) {
                    addClass2("text-right hidden-tn hidden-xxs hidden-xs")
                    backupButton()
                    btsButton(onclick = { window.location.hash = ToDoModel.toUrl(null) }) {
                        appendText("Add To-Do")
                    }
                }
            }
        }
    }
}

private fun HTMLElement.editOnClick(toDo: ToDo, render: HTMLAnchorElement.(ToDo) -> Unit) {
    a { href = ToDoModel.toUrl(toDo.id)
        render(toDo)
    }
}
