package client

import client.component.undoComponent
import client.component.visible
import client.util.mapEachReusingByID
import common.*
import common.util.Repository
import common.util.RepositoryListener
import common.util.inContext
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.bootstrap3.*
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import kotlin.browser.window
import kotlin.dom.addClass
import kotlin.dom.appendText

/**
 * UI for showing a list of ToDos.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/7/16
 * Time: 6:37 AM
 */
class ToDoMasterModel(val repository: Repository<ToDo> = Factory.toDoRepository) {
    val data = Property<List<ToDo>?>(repository.list())
    val dataProperties = data.mapEachReusingByID { it.toProperty() }
    val currentSort = Property<SortSpecification<Property<ToDo>>?>(null)

    init {
        repository.addListener(object : RepositoryListener<ToDo> {
            override fun onSaved(original: ToDo?, replacementWithID: ToDo) {
                if (original == null) {
                    data.set(repository.list())
                } else {
                    dataProperties.get()?.find { it.get().id == original.id }?.set(replacementWithID)
                    data.set(repository.list())
                }
            }

            override fun onRemoved(item: ToDo) {
                data.set(repository.list())
            }
        })
    }

    fun delete(todo: ToDo) {
        repository.remove(todo)
    }

    companion object {
        fun toUrl(): String = "#toDos"
    }
}

fun toDoMasterScreen(model: ToDoMasterModel, animate: Boolean = true): HTMLDivElement {
    return Div {
        inContext("buttonBar") { buttonBar(null.toProperty(), "To-Do List".toProperty()) }
        div { addClass("container-fluid")
            className = "table-responsive"
            table {
                className = "table table-striped table-hover table-condensed"
                thead {
                    tr {
                        th {
                            className = "text-left"
                            sortControlWithArrow(model.currentSort, compareByProperty<ToDo> { it.name }, sortNow = true) { appendText("ToDo") }
                        }
                        th {
                            className = "text-right"
                            sortControlWithArrow(model.currentSort, compareByProperty<ToDo> { it.dueDate }) {
                                div { appendText("Due Date") }
                            }
                        }
                        th {
                            className = "text-right"
                        }
                    }
                }
                tbody(model.dataProperties.sortedWith(model.currentSort), effect = if (animate) Collapse() else NoEffect) { item ->
                    tr {
                        td {
                            div {
                                className = "text-left name"
                                editOnClick(item) { it.onNext { textContent = it.name } }
                            }
                        }
                        td {
                            div {
                                className = "text-right"
                                editOnClick(item) { it.onNext { textContent = (it.dueDate ?: "").toString() } }
                            }
                        }
                        td {
                            div {
                                className = "text-right"
                                btsButton(onclick = { model.delete(item.get()) }) {
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
                model.data.onNext { visible = it?.isEmpty() ?: true }
                appendText("There are no to-dos.")
            }
        }
        br()
        br()
        navbar(NavbarCompletePosition.FixedBottom, containerWidth = ContainerWidth.Fluid) {
            navbarContainer.row {
                col(Col.Width.Tn(3) and Col.Width.Xs(3)) {
                    btsButton(onclick = { window.location.hash = ToDoDetailModel.toUrl(null) }) {
                        appendText("Add")
                    }
                }
                col(Col.Width.Tn(9) and Col.Width.Xs(9)) {
                    addClass("text-right")
                    undoComponent()
                }
            }
        }
    }
}

private fun HTMLElement.editOnClick(toDo: ReadOnlyProperty<ToDo>, render: HTMLAnchorElement.(ReadOnlyProperty<ToDo>) -> Unit) {
    a { toDo.onNext { href = ToDoDetailModel.toUrl(it.id) }
        render(toDo)
    }
}
