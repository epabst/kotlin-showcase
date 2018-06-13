package client

import client.util.JavascriptProvider
import client.util.*
import common.*
import common.util.*
import kotlin.test.Test

/**
 * A test for [ToDoJS], etc.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
@Suppress("unused")
class JSModelTest {
    init {
        PlatformProvider.instance = JavascriptProvider
    }

    @Test
    fun ToDoJS_shouldSerializeAndDeserialize() {
        val toDoId = ID<ToDo>(1234)
        val originalToDo = ToDo("To-Do #1", JavascriptProvider.now(), "some notes", JavascriptProvider.now(), id = toDoId)
        val json = JSON.stringify(originalToDo)
        val toDoJS: ToDoJS = JSON.parse<ToDoJS>(json)
        toDoJS.toNormal().mustBe(originalToDo)
    }

    @Test
    fun ToDoJS_shouldSerializeAndDeserializeAsAnArray() {
        val toDo1 = ToDo("To-Do #1", JavascriptProvider.now(), "some notes", JavascriptProvider.now(), id = ID(1234))
        val toDo2 = ToDo("To-Do #2", JavascriptProvider.now(), "other notes", JavascriptProvider.now(), id = ID(5678))
        val originalList = listOf(toDo1, toDo2)
        val json = JSON.stringify(originalList)
        val toDos2: Array<ToDoJS> = JSON.parse<Array<ToDoJS>>(json)
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

fun <T : WithID<T>> ID(id: Long): ID<T> = ID(id.toString())
