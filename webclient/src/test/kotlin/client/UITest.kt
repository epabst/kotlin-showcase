package client

import client.component.UndoComponent
import client.util.*
import client.util.JavascriptProvider
import common.*
import common.util.*
import kotlin.test.fail
import net.yested.core.properties.*
import net.yested.ext.jquery.destinationBack
import kotlin.browser.window

/**
 * A test for UI such as [toDoMasterScreen].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/11/16
 * Time: 3:47 PM
 */
object UITest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider
        val today = RichDate.today()
        val toDoRepository = Factory.toDoRepository
        val testToDoIds = ArrayList<ID<ToDo>>()
        toDoRepository.addListener(object : RepositoryListener<ToDo> {
            override fun onSaved(original: ToDo?, replacementWithID: ToDo) {
                testToDoIds.add(replacementWithID.id!!)
            }

            override fun onRemoved(item: ToDo) {
            }
        })
//        toDoRepository.list.forEach { toDoRepository.remove(it) }

        describe("ToDoMasterScreen") {
            it("should delete a ToDo and be able to undo it") {
                try {
                    val initialSize = toDoRepository.list().size
                    window.location.hash = ToDoMasterModel.toUrl()

                    val toDoDetailModel = ToDoDetailModel(null.toProperty())
                    toDoDetailModel.toDo.set(ToDo("Txt#1"))
                    window.location.hash = ToDoDetailModel.toUrl(null)
                    toDoDetailScreen(toDoDetailModel)

                    toDoDetailModel.save().mustBe(true)
                    assertWentBackTo(ToDoMasterModel.toUrl())
                    (toDoRepository.list().size - initialSize).mustBe(1)

                    val originalUndoSize = UndoComponent.undoCount

                    window.location.hash = ToDoMasterModel.toUrl()
                    val toDoMasterModel = ToDoMasterModel()
                    toDoMasterScreen(toDoMasterModel, animate = false)

                    toDoDetailModel.delete()

                    (toDoRepository.list().size - initialSize).mustBe(0)
                    (UndoComponent.undoCount - originalUndoSize).mustBe(1)

                    UndoComponent.undo()
                    (toDoRepository.list().size - initialSize).mustBe(1)
                } finally {
                    testToDoIds.forEach { toDoRepository.remove(it) }
                }
            }

            it("should allow undo save of a to-do") {
                try {
                    val originalSize = toDoRepository.list().size
                    val originalUndoCount = UndoComponent.undoCount
                    val toDoDetailModel = ToDoDetailModel(null.toProperty())
                    toDoDetailModel.toDo.set(ToDo("Txt#1"))
                    toDoDetailScreen(toDoDetailModel)
                    toDoDetailModel.save().mustBe(true)

                    toDoDetailModel.toDo.set(ToDo("Txt#2"))
                    toDoDetailModel.save().mustBe(true)

                    (toDoRepository.list().size - originalSize).mustBe(2)
                    val maserModel = ToDoMasterModel(toDoRepository)
                    toDoMasterScreen(maserModel)
                    ((maserModel.dataProperties.get() ?: fail("grid.list should be set")).size - originalSize).mustBe(2)

                    UndoComponent.undoCount.mustBe(originalUndoCount + 2)
                    UndoComponent.undo()
                    UndoComponent.undoCount.mustBe(originalUndoCount + 1)
                    (toDoRepository.list().size - originalSize).mustBe(1)
                    ((maserModel.dataProperties.get() ?: fail("grid.list should be set")).size - originalSize).mustBe(1)
                } finally {
                    testToDoIds.forEach { toDoRepository.remove(it) }
                }
            }

            it("should re-sort when a to-do is updated") {
                toDoRepository.list().filter { it.name.startsWith("Txt#") }.forEach { toDoRepository.remove(it) }
                toDoRepository.save(null, ToDo("Txt#1"))
                val id2 = toDoRepository.save(null, ToDo("Txt#2"))
                toDoRepository.save(null, ToDo("Txt#3"))

                val toDoMasterModel = ToDoMasterModel(toDoRepository)
                val masterScreen = toDoMasterScreen(toDoMasterModel, animate = false)
                masterScreen.textContent.mustContainInOrder("Txt#1", "Txt#2", "Txt#3")

                val toDo2 = toDoRepository.find(id2)
                toDoRepository.save(toDo2!!, toDo2.copy(name = "Txt#4"))
                masterScreen.textContent.mustContainInOrder("Txt#1", "Txt#3", "Txt#4")
            }
        }

        describe("toDoDetailScreen") {
            it("should start (mostly) empty for each new to-do") {
                try {
                    val todoId: Property<ID<ToDo>?> = null.toProperty()
                    val toDoDetailModel = ToDoDetailModel(todoId)
                    toDoDetailScreen(toDoDetailModel)
                    toDoDetailModel.name.set("TestToDo1")
                    toDoDetailModel.dueDate.set(today.toMoment())
                    toDoDetailModel.save().mustBe(true)
                    todoId.get().mustNotBe(null)
                    testToDoIds.add(todoId.get()!!)

                    todoId.set(null)
                    toDoDetailModel.name.get().mustBe("")
                    toDoDetailModel.dueDate.get().mustBe(null)
                } finally {
                    testToDoIds.forEach { toDoRepository.remove(it) }
                }
            }

            it("should reuse Property instances on the screen after adding and removing others") {
                toDoRepository.list().filter { it.name.startsWith("Txt#") }.forEach { toDoRepository.remove(it) }
                val toDo = (ToDo("Txt#1", today) as ToDo?).toProperty()
                toDo.set(ToDo("Txt#2", today))

                val id1 = toDoRepository.save(null, ToDo("Txt#1", today))
                val id2 = toDoRepository.save(null, ToDo("Txt#2", today))

                val masterModel = ToDoMasterModel(toDoRepository)
                val dataProperty2 = masterModel.dataProperties.get()?.find { it.get().id == id2 }!!

                val masterScreen = toDoMasterScreen(masterModel, animate = false)
                masterScreen.textContent.mustContain("Txt#1")
                masterScreen.textContent.mustContain("Txt#2")
                masterScreen.textContent.mustNotContain("Txt#Was2")
                masterScreen.textContent.mustNotContain("Txt#3")

                toDoRepository.save(null, ToDo("Txt#3", today))
                toDoRepository.remove(id1)
                val toDo2 = toDoRepository.find(id2)!!
                dataProperty2.set(toDo2.copy(name = "Txt#Was2"))
                masterScreen.textContent.mustContain("Txt#Was2")
                masterScreen.textContent.mustContain("Txt#3")
                masterScreen.textContent.mustNotContain("Txt#2")
                masterScreen.textContent.mustNotContain("Txt#1")

                (masterModel.dataProperties.get()?.find { it.get().id == id2 } === dataProperty2).mustBe(true)
            }

            it("should not start a new to-do with a recently edited to-do") {
                try {
                    val toDo = ToDo("Txt#1")
                    val toDoId = toDoRepository.save(null, toDo)
                    testToDoIds.add(toDoId)

                    val toDoIdBeingEdited = toDoId.toProperty<ID<ToDo>?>()
                    val toDoDetailModel = ToDoDetailModel(toDoIdBeingEdited)
                    toDoDetailScreen(toDoDetailModel)
                    toDoDetailModel.name.get().mustBe("Txt#1")
                    toDoDetailModel.dueDate.get().mustBe(null)

                    toDoDetailModel.name.set("TestTxt1")
                    toDoDetailModel.dueDate.set(today.toMoment())

                    toDoIdBeingEdited.set(null)
                    toDoDetailModel.name.get().mustBe("")
                    toDoDetailModel.dueDate.get().mustBe(null)
                } finally {
                    testToDoIds.forEach { toDoRepository.remove(it) }
                }
            }
        }

        describe("UndoComponent") {
            it("should allow various sequences of undo/redo/commands") {
                val originalUndoCount = UndoComponent.undoCount
                val masterModel = ToDoMasterModel()
                toDoMasterScreen(masterModel)

                val originalSize = toDoRepository.list().size
                val detailModel = ToDoDetailModel(null.toProperty())
                detailModel.toDo.set(ToDo("Txt#1"))
                toDoDetailScreen(detailModel)
                detailModel.save().mustBe(true)

                detailModel.toDo.set(ToDo("Txt#2"))
                detailModel.save().mustBe(true)
                (toDoRepository.list().size - originalSize).mustBe(2)

                UndoComponent.undo()
                (toDoRepository.list().size - originalSize).mustBe(1)

                UndoComponent.redo()
                (toDoRepository.list().size - originalSize).mustBe(2)

                UndoComponent.undo()
                UndoComponent.undo()
                (toDoRepository.list().size - originalSize).mustBe(0)

                UndoComponent.undoCount.mustBe(originalUndoCount)
                UndoComponent.redoCount.mustBe(2)

                UndoComponent.redo()
                UndoComponent.redo()
                (toDoRepository.list().size - originalSize).mustBe(2)

                (UndoComponent.undoCount - originalUndoCount).mustBe(2)
                UndoComponent.redoCount.mustBe(0)
            }
        }
    }

    fun assertWentBackTo(hash: String) {
        val destinationBack = window.history.destinationBack
        if (destinationBack != null) {
            destinationBack.mustBe(hash)
        } else {
            window.location.hash.mustBe(hash)
        }
    }
}
