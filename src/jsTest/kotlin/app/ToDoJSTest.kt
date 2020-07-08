package app

import todo.model.ToDo
import todo.model.ToDoJS
import todo.model.toNormal
import component.entity.LongJS
import component.entity.toNormal
import platform.PlatformProvider
import component.entity.ID
import component.entity.Revision
import util.mustBe
import kotlin.test.Test

/**
 * A test for [ToDoJS], etc.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
@Suppress("unused")
class ToDoJSTest {

    @Test
    fun ToDoJS_shouldSerializeAndDeserialize() {
        val toDoId = ID<ToDo>(1234)
        val originalToDo = ToDo("To-Do #1", PlatformProvider.now(), "some notes", PlatformProvider.now(), id = toDoId, rev = Revision("123"))
        val json = JSON.stringify(originalToDo)
        val toDoJS = JSON.parse<ToDoJS>(json)
        toDoJS.toNormal().mustBe(originalToDo)
    }

    @Test
    fun ToDoJS_shouldSerializeAndDeserializeAsAnArray() {
        val toDo1 = ToDo("To-Do #1", PlatformProvider.now(), "some notes", PlatformProvider.now(), id = ID(1234))
        val toDo2 = ToDo("To-Do #2", PlatformProvider.now(), "other notes", PlatformProvider.now(), id = ID(5678))
        val originalList = listOf(toDo1, toDo2)
        val json = JSON.stringify(originalList)
        val toDos2 = JSON.parse<Array<ToDoJS>>(json)
        toDos2.toList().map { it.toNormal() }.mustBe(originalList)
    }

    @Test
    fun LongJS_shouldSerializeAndDeserialize() {
        serializeDeserialize(3).toNormal().mustBe(3)
    }

    @Test
    fun LongJS_shouldConvertFromJavascriptNumber() {
        val number: LongJS = js("55555555")
        number.toNormal().mustBe(55555555)
    }

    private fun serializeDeserialize(value: Long): LongJS {
        val deserialized = JSON.parse<LongJS>(JSON.stringify(value))
        return deserialized
    }
}
