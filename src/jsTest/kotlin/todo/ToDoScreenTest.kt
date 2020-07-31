package todo

import enzyme.mount
import extensions.pouchdb.createAndGetId
import kotlinext.js.jsObject
import kotlinx.coroutines.delay
import util.PlatformProvider
import util.mustBe
import util.mustNotBe
import util.runTest
import kotlin.test.Test

/** A test for [ToDoScreen]. */
@Suppress("unused")
class ToDoScreenTest {
    private val date = PlatformProvider.toDate(2020, 5, 3)

    @Test
    fun shouldStartEmpty() = runTest {
        mount(ToDoScreen::class) {
            attrs.history = jsObject()
        }.apply {
            props().id.mustBe(null)
            props().history.mustNotBe(null)
            state().loading.mustBe(true)

            waitForAsyncProcessing()
            state().loading.mustBe(false)
            state().original.mustBe(null)
            state().name.mustBe("")
            state().validated.mustBe(false)

            instance().setToDo(ToDo("Do homework", dueDate = date))
            state().name.mustBe("Do homework")
            state().dueDate?.getFullYear().mustBe(2020)
        }
    }

    @Test
    fun shouldAllowEditing() = runTest {
        mount(ToDoScreen::class) {
            attrs.history = jsObject()
        }.apply {
            waitForAsyncProcessing()
            instance().setToDo(ToDo("Do homework", dueDate = date))
            state().name.mustBe("Do homework")
            state().dueDate?.getFullYear().mustBe(2020)
        }
    }

    @Test
    fun shouldLoadData() = runTest {
        val newId = Config.toDoDb.createAndGetId(ToDo("Sleep"), ToDoJS::toNormal)

        mount(ToDoScreen::class) {
            attrs.id = newId
            attrs.history = jsObject()
        }.apply {
            waitForAsyncProcessing()
            state().name.mustBe("Sleep")
        }
    }
}

private suspend fun waitForAsyncProcessing() {
    delay(10)
}
