package client

import client.component.visible
import client.util.dateTimeFormat
import common.*
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.bootstrap3.*
import net.yested.ext.moment.Moment
import org.w3c.dom.HTMLDivElement
import kotlin.browser.window
import kotlin.dom.addClass
import kotlin.dom.appendText

/**
 * UI for editing a single [Timer].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/6/18
 * Time: 6:37 AM
 */
class TimerModel {
    val timerRepository = Factory.timerRepository
    val startTime: Property<Moment?> = null.toProperty()
    val timerMode: Property<TimerMode> = TimerMode.INITIAL.toProperty()
    val userAgent = window.navigator.userAgent

    fun startNewTimer() {
        startTime.set(Moment.now())
        timerMode.set(TimerMode.STARTED)
    }

    fun stopTimer(): Boolean {
        val startMoment = startTime.get()
        if (startMoment != null) {
            val duration = ((Moment.now().millisecondsSinceUnixEpoch - startMoment.millisecondsSinceUnixEpoch) / 1000).toInt()
            timerRepository.save(Timer(userAgent, nowDateTime(), duration))
            timerMode.set(TimerMode.DONE)
            startTime.set(null)
            return true
        } else {
            timerMode.set(TimerMode.DONE)
            return false
        }
    }

    fun cancel() {
        startTime.set(null)
        timerMode.set(TimerMode.DONE)
    }

    fun startOver() {
        startTime.set(null)
        timerMode.set(TimerMode.INITIAL)
    }

    companion object {
        fun toUrl(): String = "#timer"
    }
}

fun nowDateTime() = Moment.now().format(dateTimeFormat)

enum class TimerMode {
    INITIAL, STARTED, DONE
}

fun timerScreen(model: TimerModel): HTMLDivElement {
    return Div {
        div {
            h1 { appendText("Jack Dance's Toothbrush Time")}
            h3 { appendText("Thank you for helping him out with his experiment!")}
            h3 { appendText("You are awesome!")}
            br()
            br()
            br()
        }
        navbar(NavbarCompletePosition.FixedBottom, containerWidth = ContainerWidth.Fluid) {
            navbarContainer.row {
                col(Col.Width.Tn(12) and Col.Width.Xs(12)) {
                    addClass("text-right")
                    model.timerMode.onNext { visible = it == TimerMode.INITIAL }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Success, onclick = { _ -> model.startNewTimer() }) {
                        appendText("Start Brushing Teeth")
                    }
                }
                col(Col.Width.Tn(12) and Col.Width.Xs(6)) {
                    addClass("text-left")
                    model.timerMode.onNext { visible = it == TimerMode.DONE }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Default, onclick = { window.location.hash = TimersModel.toUrl() }) {
                        appendText("See Everyone's Results")
                    }
                }
                col(Col.Width.Tn(12) and Col.Width.Xs(6)) {
                    addClass("text-right")
                    model.timerMode.onNext { visible = it == TimerMode.DONE }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Default, onclick = { _ -> model.startOver() }) {
                        appendText("Start Over")
                    }
                }
                col(Col.Width.Tn(5) and Col.Width.Xs(5)) {
                    model.timerMode.onNext { visible = it == TimerMode.STARTED }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Warning, onclick = { _ -> model.cancel() }) {
                        appendText("Cancel")
                    }
                }
                col(Col.Width.Tn(7) and Col.Width.Xs(7)) {
                    addClass("text-right")
                    model.timerMode.onNext { visible = it == TimerMode.STARTED }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Primary, onclick = { _ -> model.stopTimer() }) {
                        appendText("Stop Brushing Teeth")
                    }
                }
            }
        }
    }
}
