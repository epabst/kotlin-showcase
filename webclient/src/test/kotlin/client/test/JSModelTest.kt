package client.test

import client.JavascriptProvider
import client.LongJS
import client.ToDoJS
import client.toNormal
import common.*

/**
 * A test for [ToDoJS], etc.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
object JSModelTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider

        val today = PlatformProvider.instance.now()

        describe("ToDoJS") {
            it("should serialize/deserialize") {
                val toDoId = ID(1234)
                val originalToDo = ToDo("To-Do #1", today, "some notes", today, id = toDoId)
                val json = JSON.stringify(originalToDo)
                val toDoJS: ToDoJS = JSON.parse<ToDoJS>(json)
                toDoJS.toNormal().mustBe(originalToDo)
            }

            it("should serialize/deserialize as an Array") {
                val toDo1 = ToDo("To-Do #1", today, "some notes", today, id = ID(1234))
                val toDo2 = ToDo("To-Do #2", today, "other notes", today, id = ID(5678))
                val originalList = listOf(toDo1, toDo2)
                val json = JSON.stringify(originalList)
                val toDos2: Array<ToDoJS> = JSON.parse<Array<ToDoJS>>(json)
                toDos2.toList().map { it.toNormal() }.mustBe(originalList)
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
        val deserialized = JSON.parse<LongJS>(JSON.stringify(value))
        return deserialized
    }
}
