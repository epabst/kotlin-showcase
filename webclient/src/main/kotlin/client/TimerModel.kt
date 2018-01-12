package client

import client.component.iframe
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
import kotlin.js.Math

/**
 * UI for editing a single [Timer].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/6/18
 * Time: 6:37 AM
 */
class TimerModel {
    val timerNumber = 0.toProperty()
    val timerType = timerNumber.mapAsDefault { TimerType.values()[(Math.random() * 3).toInt()] }
    val videoKeys = listOf("Odlthx3ROwE", "vM1xswYzyVY", "lnEKzXIxcq4", "FUuMhg9NtxY", "9omhrI0Pnq8", "PIrRpgWfY4M",
            "zqD5hDbgLyU", "LZoI-MFnTHE")
    val videoKey = timerNumber.map { videoKeys[(Math.random() * videoKeys.size).toInt()] }
    val audioKeys = listOf("9MJAg0VDgO0", "GFQYaoiIFh8", "iO_WxYC34eM")
    val audioKey = timerNumber.map { audioKeys[(Math.random() * audioKeys.size).toInt()] }
    val timerRepository = Factory.timerRepository
    val startTime: Property<Moment?> = null.toProperty()
    val timerMode: Property<TimerStatus> = TimerStatus.INITIAL.toProperty()
    val userAgent = window.navigator.userAgent

    init {
        timerType.set(TimerType.NONE)
    }

    fun startNewTimer() {
        timerNumber.set(timerNumber.get() + 1)
        startTime.set(Moment.now())
        timerMode.set(TimerStatus.STARTED)
    }

    fun stopTimer(): Boolean {
        val startMoment = startTime.get()
        if (startMoment != null) {
            val duration = ((Moment.now().millisecondsSinceUnixEpoch - startMoment.millisecondsSinceUnixEpoch) / 1000).toInt()
            timerRepository.save(Timer(userAgent, nowDateTime(), timerNumber.get(), timerType.get().name, duration))
            timerMode.set(TimerStatus.DONE)
            startTime.set(null)
            return true
        } else {
            timerMode.set(TimerStatus.DONE)
            return false
        }
    }

    fun cancel() {
        startTime.set(null)
        timerMode.set(TimerStatus.DONE)
    }

    fun startOver() {
        timerType.set(TimerType.NONE)
        startTime.set(null)
        timerMode.set(TimerStatus.INITIAL)
    }

    companion object {
        fun toUrl(): String = "#timer"
    }
}

fun nowDateTime() = Moment.now().format(dateTimeFormat)

enum class TimerStatus {
    INITIAL, STARTED, DONE
}

fun timerScreen(model: TimerModel): HTMLDivElement {
    return Div {
        div {
            val divByTimerType = mapOf(
                    TimerType.NONE to Div {
                        h1 { appendText("Jack Dance's Toothbrush Time")}
                        h3 { appendText("Thank you for helping him out with his science experiment!")}
                        h3 { appendText("You are awesome!")}
                        br()
                        h3 { model.timerMode.onNext { textContent = if (it == TimerStatus.INITIAL) "" else "Enjoy some peace and quiet as you brush." } }
                    },
                    TimerType.VIDEO to Div {
                        addClass("embed-responsive embed-responsive-16by9")
                        iframe {
                            addClass("embed-responsive-item")
                            model.videoKey.onNext {
                                src = "https://www.youtube.com/embed/$it?autoplay=1"
                            }
                            setAttribute("gesture", "media")
                            setAttribute("allow", "autoplay; encrypted-media")
                        }
                    },
                    TimerType.SONG to Div {
                        h1 { appendText("Jack Dance's Toothbrush Time")}
                        h3 { appendText("Thank you for helping him out with his science experiment!")}
                        h3 { appendText("You are awesome!")}
                        br()
                        h3 { appendText("Enjoy some music as you brush.")}
                        iframe {
                            addClass("text-right")
                            width = "100"
                            height = "100"

                            model.audioKey.onNext {
                                src = "https://www.youtube.com/embed/$it?autoplay=1"
                            }
                            setAttribute("gesture", "media")
                            setAttribute("allow", "autoplay; encrypted-media")
                        }
                    })
            div {
                model.timerType.onNext { setChild(divByTimerType.get(it)!!) }
            }
            br()
            br()
            br()
        }
        navbar(NavbarCompletePosition.FixedBottom, containerWidth = ContainerWidth.Fluid) {
            navbarContainer.row {
                col(Col.Width.Tn(12) and Col.Width.Xs(12)) {
                    addClass("text-right")
                    model.timerMode.onNext { visible = it == TimerStatus.INITIAL }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Success, onclick = { _ -> model.startNewTimer() }) {
                        appendText("Start Brushing Teeth")
                    }
                }
                col(Col.Width.Tn(12) and Col.Width.Xs(6)) {
                    addClass("text-left")
                    model.timerMode.onNext { visible = it == TimerStatus.DONE }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Default, onclick = { model.startOver(); window.location.hash = TimersModel.toUrl() }) {
                        appendText("See Everyone's Results")
                    }
                }
                col(Col.Width.Tn(12) and Col.Width.Xs(6)) {
                    addClass("text-right")
                    model.timerMode.onNext { visible = it == TimerStatus.DONE }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Default, onclick = { _ -> model.startOver() }) {
                        appendText("Start Over")
                    }
                }
                col(Col.Width.Tn(5) and Col.Width.Xs(5)) {
                    model.timerMode.onNext { visible = it == TimerStatus.STARTED }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Warning, onclick = { _ -> model.cancel() }) {
                        appendText("Cancel")
                    }
                }
                col(Col.Width.Tn(7) and Col.Width.Xs(7)) {
                    addClass("text-right")
                    model.timerMode.onNext { visible = it == TimerStatus.STARTED }
                    btsButton(size = ButtonSize.Large, look = ButtonLook.Primary, onclick = { _ -> model.stopTimer() }) {
                        appendText("Stop Brushing Teeth")
                    }
                }
            }
        }
    }
}
