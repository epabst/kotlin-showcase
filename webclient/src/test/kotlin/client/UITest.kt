package client

import client.component.UndoComponent
import client.util.*
import client.util.JavascriptProvider
import common.*
import common.util.*
import kotlin.browser.window

/**
 * A test for UI such as [timersScreen].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/11/16
 * Time: 3:47 PM
 */
object UITest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider
        val timerRepository = Factory.timerRepository
        val testTimerIds = ArrayList<ID<Timer>>()
        timerRepository.addListener(object : RepositoryListener<Timer> {
            override fun onSaved(original: Timer?, replacementWithID: Timer) {
                testTimerIds.add(replacementWithID.id!!)
            }

            override fun onRemoved(item: Timer) {
            }
        })
//        timerRepository.list.forEach { timerRepository.remove(it) }

        describe("timersScreen") {
            it("should delete a Timer and be able to undo it") {
                try {
                    val initialSize = timerRepository.list().size
                    window.location.hash = TimersModel.toUrl()

                    val timerModel = TimerModel()
                    timerModel.startNewTimer()
                    timerModel.stopTimer().mustBe(true)
                    (timerRepository.list().size - initialSize).mustBe(1)

                    val originalUndoSize = UndoComponent.undoCount

                    window.location.hash = TimersModel.toUrl()
                    val timersModel = TimersModel()
                    timersScreen(timersModel, animate = false)

                    timersModel.delete(timerRepository.list()[0])

                    (timerRepository.list().size - initialSize).mustBe(0)
                    (UndoComponent.undoCount - originalUndoSize).mustBe(1)

                    UndoComponent.undo()
                    (timerRepository.list().size - initialSize).mustBe(1)
                } finally {
                    testTimerIds.forEach { timerRepository.remove(it) }
                }
            }

            it("should re-sort when a timer is updated") {
                timerRepository.list().filter { it.device.startsWith("Device#") }.forEach { timerRepository.remove(it) }
                timerRepository.save(null, Timer("Device#1", nowDateTime(), 3, TimerType.SONG.name, 5))
                val id2 = timerRepository.save(null, Timer("Device#2", nowDateTime(), 3, TimerType.SONG.name, 5))
                timerRepository.save(null, Timer("Device#3", nowDateTime(), 3, TimerType.SONG.name, 5))

                val timersModel = TimersModel(timerRepository)
                val masterScreen = timersScreen(timersModel, animate = false)
                masterScreen.textContent.mustContainInOrder("Device#1", "Device#2", "Device#3")

                val toDo2 = timerRepository.find(id2)
                timerRepository.save(toDo2!!, toDo2.copy(device = "Device#4"))
                masterScreen.textContent.mustContainInOrder("Device#1", "Device#3", "Device#4")
            }
        }
    }
}
