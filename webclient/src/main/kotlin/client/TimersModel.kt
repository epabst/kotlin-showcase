package client

import client.component.responsiveWidth
import client.component.visible
import client.util.listProperty
import common.Timer
import common.util.Repository
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.bootstrap3.*
import org.w3c.dom.HTMLDivElement
import kotlin.browser.window
import kotlin.dom.addClass
import kotlin.dom.appendText

/**
 * UI for showing a list of ToDos.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/7/16
 * Time: 6:37 AM
 */
class TimersModel(val repository: Repository<Timer> = Factory.timerRepository) {
    val data = repository.listProperty()
    val currentSort = Property<SortSpecification<Timer>?>(null)

    fun delete(todo: Timer) {
        repository.remove(todo)
    }

    companion object {
        fun toUrl(): String = "#timers"
    }
}

fun timersScreen(model: TimersModel, animate: Boolean = true): HTMLDivElement {
    return Div {
        h3 {
            addClass("nowrap")
            textContent = "Toothbrush Timers"
        }
        div { addClass("container-fluid")
            className = "table-responsive"
            table {
                className = "table table-striped table-hover table-condensed"
                thead {
                    addClass("hidden-xxs hidden-tn")
                    tr {
                        th {
                            Col.Width.Sm(2) and Col.Width.Xs(2)
                            addClass("text-left")
                            sortControlWithArrow(model.currentSort, compareBy { it.type }, sortNow = true) {
                                div { appendText("Type") }
                            }
                        }
                        th {
                            Col.Width.Sm(3) and Col.Width.Xs(3)
                            addClass("text-left")
                            sortControlWithArrow(model.currentSort, compareBy { it.date }, sortNow = true) {
                                div { appendText("Date") }
                            }
                        }
                        th {
                            Col.Width.Sm(2) and Col.Width.Xs(2)
                            addClass("text-center")
                            sortControlWithArrow(model.currentSort, compareBy { it.durationSeconds }) {
                                div { appendText("# of Seconds") }
                            }
                        }
                        th {
                            Col.Width.Sm(5) and Col.Width.Xs(5)
                            addClass("text-left")
                            sortControlWithArrow(model.currentSort, compareBy { it.device }) { appendText("Device") }
                        }
//                        th {
//                            Col.Width.Sm(2) and Col.Width.Xs(2)
//                            addClass("text-right")
//                        }
                    }
                }
                tbody(model.data.sortedWith(model.currentSort), effect = if (animate) Collapse() else NoEffect) { item ->
                    tr {
                        td {
                            responsiveWidth = Col.Width.Xs(2) and Col.Width.Tn(2)
                            div {
                                className = "text-left"
                                textContent = item.timerType
                            }
                        }
                        td {
                            responsiveWidth = Col.Width.Xs(3) and Col.Width.Tn(3)
                            div {
                                className = "text-left"
                                textContent = item.date
                            }
                        }
                        td {
                            responsiveWidth = Col.Width.Xs(2) and Col.Width.Tn(2)
                            div {
                                addClass("text-center")
                                textContent = item.durationSeconds.toString()
                            }
                        }
                        td {
                            responsiveWidth = Col.Width.Xs(5) and Col.Width.Tn(5)
                            div {
                                className = "text-left"
                                textContent = item.device
                            }
                        }
//                        td {
//                            responsiveWidth = Col.Width.Sm(2) and Col.Width.Xs(2) and Col.Width.Xxs(3) and Col.Width.Tn(12)
//                            div {
//                                className = "text-right"
//                                btsButton(onclick = { model.delete(item.get()) }) {
//                                    appendText("Delete")
//                                }
//                            }
//                        }
                    }
                }
            }
        }
        row {
            col(Col.Width.Xs(12)) {
                model.data.onNext { visible = it.isEmpty() }
                appendText("There are no toothbrush times.")
            }
        }
        br()
        br()
        br()
        navbar(NavbarCompletePosition.FixedBottom, containerWidth = ContainerWidth.Fluid) {
            navbarContainer.row {
                col(Col.Width.Tn(12) and Col.Width.Xs(12)) {
                    addClass("text-right")
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Success, onclick = { UI.timerModel.startOver(); window.location.hash = TimerModel.toUrl() }) {
                        appendText("Use Timer")
                    }
                }
            }
        }
    }
}
