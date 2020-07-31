package todo

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

    @Test
    fun shouldStartEmpty() = runTest {
        val wrapper = enzyme.mount(ToDoScreen::class) {
            attrs.history = jsObject()
        }
        wrapper.props().id.mustBe(null)
        wrapper.props().history.mustNotBe(null)
        wrapper.state().loading.mustBe(true)

        waitForAsyncProcessing()
        wrapper.state().loading.mustBe(false)
        wrapper.state().original.mustBe(null)
        wrapper.state().name.mustBe("")
        wrapper.state().validated.mustBe(false)

        wrapper.instance().setToDo(ToDo("Do homework", dueDate = PlatformProvider.toDate(2020, 5, 3)))
        wrapper.state().name.mustBe("Do homework")
        wrapper.state().dueDate?.getFullYear().mustBe(2020)
    }

    @Test
    fun shouldAllowEditing() = runTest {
        val wrapper = enzyme.mount(ToDoScreen::class) {
            attrs.history = jsObject()
        }
        waitForAsyncProcessing()
        wrapper.instance().setToDo(ToDo("Do homework", dueDate = PlatformProvider.toDate(2020, 5, 3)))
        wrapper.state().name.mustBe("Do homework")
        wrapper.state().dueDate?.getFullYear().mustBe(2020)
    }

    @Test
    fun shouldLoadData() = runTest {
        val newId = Config.toDoDb.createAndGetId(ToDo("Sleep"), ToDoJS::toNormal)

        val wrapper = enzyme.mount(ToDoScreen::class) {
            attrs.id = newId
            attrs.history = jsObject()
        }
        waitForAsyncProcessing()
        wrapper.state().name.mustBe("Sleep")
    }

    private suspend fun waitForAsyncProcessing() {
        delay(10)
    }
}
