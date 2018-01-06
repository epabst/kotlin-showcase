package client

import client.util.JavascriptProvider
import client.util.*
import common.*
import common.util.*
import net.yested.ext.moment.Moment

/**
 * A test for [TimerJS], etc.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
object JSModelTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider
        val nowDateTime = Moment.now().format(dateTimeFormat)
        
        describe("TimerJS") {
            it("should serialize/deserialize") {
                val timerId = ID<Timer>(1234)
                val originalTimer = Timer("User #1", nowDateTime, 100, id = timerId)
                val json = JSON.stringify(originalTimer)
                val timerJS: TimerJS = JSON.parse(json)
                timerJS.toNormal().mustBe(originalTimer)
            }

            it("should serialize/deserialize as an Array") {
                val timer1 = Timer("User #1", nowDateTime, 101, id = ID(1234))
                val timer2 = Timer("User #2", nowDateTime, 102, id = ID(5678))
                val originalList = listOf(timer1, timer2)
                val json = JSON.stringify(originalList)
                val timers2: Array<TimerJS> = JSON.parse(json)
                timers2.toList().map { it.toNormal() }.mustBe(originalList)
            }
        }

        describe("LongJS") {
            it("should serialize/deserialize") {
                serializeDeserialize(3).toNormal().mustBe(3)
            }

            it("should convert from Javascript number") {
                val number: LongJS = js("55555555")
                number.toNormal().mustBe(55555555)
            }
        }
    }

    private fun serializeDeserialize(value: Long): LongJS {
        return JSON.parse(JSON.stringify(value))
    }
}
