package app

//import component.UndoComponent
//import client.util.*
//import platform.JavascriptProvider
//import common.*
//import common.util.*
//import net.yested.core.properties.*
//import net.yested.ext.jquery.destinationBack
//import kotlin.browser.window
//import kotlin.test.Test
//
///**
// * A test for UI such as [toDosScreen].
// * @author Eric Pabst (epabst@gmail.com)
// * Date: 8/11/16
// * Time: 3:47 PM
// */
//@Suppress("unused")
//class UITest {
//    val today: ProviderDate
//    val toDoRepository = Factory.toDoRepository
//    val testToDoIds = ArrayList<ID<ToDo>>()
//    init {
//        val stubRequestAnimationFrame: ((Double) -> Unit) -> Int = { it.invoke(0.toDouble()); 0 }
//        window.asDynamic().requestAnimationFrame = stubRequestAnimationFrame
//        PlatformProvider.instance = JavascriptProvider
//        today = JavascriptProvider.now()
//        toDoRepository.addListener(object : RepositoryListener<ToDo> {
//            override fun onSaved(original: ToDo?, replacementWithID: ToDo) {
//                testToDoIds.add(replacementWithID.id!!)
//            }
//
//            override fun onRemoved(item: ToDo) {
//            }
//
//            override fun onVisibilityChanged(item: ToDo, visible: Boolean) {
//            }
//        })
//    }
//
//    @Test
//    fun toDosScreen_shouldDeleteAToDoAndBeAbleToUndoIt() {
//        try {
//            val initialSize = toDoRepository.list().size
//            window.location.hash = ToDosModel.toUrl()
//
//            val toDoModel = ToDoModel(null.toProperty())
//            toDoModel.toDoId.set(null)
//            toDoModel.name.set("Txt#1")
//            window.location.hash = ToDoModel.toUrl(null)
//            toDoScreen(toDoModel)
//
//            toDoModel.save().mustBe(true)
//            assertWentBackTo(ToDosModel.toUrl())
//            (toDoRepository.list().size - initialSize).mustBe(1)
//
//            val originalUndoSize = UndoComponent.undoCount
//
//            window.location.hash = ToDosModel.toUrl()
//            val toDosModel = ToDosModel()
//            toDosScreen(toDosModel, animate = false)
//
//            toDoModel.delete()
//
//            (toDoRepository.list().size - initialSize).mustBe(0)
//            (UndoComponent.undoCount - originalUndoSize).mustBe(1)
//
//            UndoComponent.undo()
//            (toDoRepository.list().size - initialSize).mustBe(1)
//        } finally {
//            testToDoIds.forEach { toDoRepository.remove(it) }
//        }
//    }
//
//    @Test
//    fun toDosScreen_shouldAllowUndoSaveOfAToDo() {
//        try {
//            val originalSize = toDoRepository.list().size
//            val originalUndoCount = UndoComponent.undoCount
//            val toDoModel = ToDoModel(null.toProperty())
//            toDoModel.toDoId.set(null)
//            toDoModel.name.set("Txt#1")
//            toDoScreen(toDoModel)
//            toDoModel.save().mustBe(true)
//            val toDoId1 = toDoModel.toDoId.get()
//            toDoId1.mustNotBe(null)
//
//            toDoModel.toDoId.set(null)
//            toDoModel.name.set("Txt#2")
//            toDoModel.save().mustBe(true)
//            val toDoId2 = toDoModel.toDoId.get()
//            toDoId2.mustNotBe(toDoId1)
//            toDoId2.mustNotBe(null)
//
//            (toDoRepository.list().size - originalSize).mustBe(2)
//            val optionsModel = ToDosModel(toDoRepository)
//            toDosScreen(optionsModel)
//            (optionsModel.data.get().size - originalSize).mustBe(2)
//
//            UndoComponent.undoCount.mustBe(originalUndoCount + 2)
//            UndoComponent.undo()
//            UndoComponent.undoCount.mustBe(originalUndoCount + 1)
//            (toDoRepository.list().size - originalSize).mustBe(1)
//            (optionsModel.data.get().size - originalSize).mustBe(1)
//        } finally {
//            testToDoIds.forEach { toDoRepository.remove(it) }
//        }
//    }
//
//    @Test
//    fun toDosScreen_shouldReSortWhenAToDoIsUpdated() {
//        toDoRepository.list().filter { it.name.startsWith("Txt#") }.forEach { toDoRepository.remove(it) }
//        toDoRepository.save(null, ToDo("Txt#1"))
//        val id2 = toDoRepository.save(null, ToDo("Txt#2"))
//        toDoRepository.save(null, ToDo("Txt#3"))
//
//        val toDosModel = ToDosModel(toDoRepository)
//        val masterScreen = toDosScreen(toDosModel, animate = false)
//        masterScreen.textContent.mustContainInOrder("Txt#1", "Txt#2", "Txt#3")
//
//        val toDo2 = toDoRepository.find(id2)
//        toDoRepository.save(toDo2!!, toDo2.copy(name = "Txt#4"))
//        masterScreen.textContent.mustContainInOrder("Txt#1", "Txt#3", "Txt#4")
//    }
//
//    @Test
//    fun toDoScreen_shouldStartMostlyEmptyForEachNewToDo() {
//        try {
//            val todoId: Property<ID<ToDo>?> = null.toProperty()
//            val toDoModel = ToDoModel(todoId)
//            toDoScreen(toDoModel)
//            toDoModel.toDoId.set(null)
//            toDoModel.name.set("TestToDo1")
//            toDoModel.dueDate.set(today.toMoment())
//            toDoModel.save().mustBe(true)
//            todoId.get().mustNotBe(null)
//            testToDoIds.add(todoId.get()!!)
//
//            todoId.set(null)
//            toDoModel.name.get().mustBe("")
//            toDoModel.dueDate.get().mustBe(null)
//        } finally {
//            testToDoIds.forEach { toDoRepository.remove(it) }
//        }
//    }
//
//    @Test
//    fun toDoScreen_shouldNotStartANewToDoWithARecentlyEditedToDo() {
//        try {
//            val toDo = ToDo("Txt#1")
//            val toDoId = toDoRepository.save(null, toDo)
//            testToDoIds.add(toDoId)
//
//            val toDoIdBeingEdited = toDoId.toProperty<ID<ToDo>?>()
//            val toDoModel = ToDoModel(toDoIdBeingEdited)
//            toDoScreen(toDoModel)
//            toDoModel.name.get().mustBe("Txt#1")
//            toDoModel.dueDate.get().mustBe(null)
//
//            toDoModel.name.set("TestTxt1")
//            toDoModel.dueDate.set(today.toMoment())
//
//            toDoIdBeingEdited.set(null)
//            toDoModel.name.get().mustBe("")
//            toDoModel.dueDate.get().mustBe(null)
//        } finally {
//            testToDoIds.forEach { toDoRepository.remove(it) }
//        }
//    }
//
//    @Test
//    fun UndoComponent_shouldAllowVariousSequencesOfUndoRedoCommands() {
//        val originalUndoCount = UndoComponent.undoCount
//        val masterModel = ToDosModel()
//        toDosScreen(masterModel)
//
//        val originalSize = toDoRepository.list().size
//        val detailModel = ToDoModel(null.toProperty())
//        detailModel.toDoId.set(null)
//        detailModel.name.set("Txt#1")
//        toDoScreen(detailModel)
//        detailModel.save().mustBe(true)
//
//        detailModel.toDoId.set(null)
//        detailModel.name.set("Txt#2")
//        detailModel.save().mustBe(true)
//        (toDoRepository.list().size - originalSize).mustBe(2)
//
//        UndoComponent.undo()
//        (toDoRepository.list().size - originalSize).mustBe(1)
//
//        UndoComponent.redo()
//        (toDoRepository.list().size - originalSize).mustBe(2)
//
//        UndoComponent.undo()
//        UndoComponent.undo()
//        (toDoRepository.list().size - originalSize).mustBe(0)
//
//        UndoComponent.undoCount.mustBe(originalUndoCount)
//        UndoComponent.redoCount.mustBe(2)
//
//        UndoComponent.redo()
//        UndoComponent.redo()
//        (toDoRepository.list().size - originalSize).mustBe(2)
//
//        (UndoComponent.undoCount - originalUndoCount).mustBe(2)
//        UndoComponent.redoCount.mustBe(0)
//    }
//}
//
//fun assertWentBackTo(hash: String) {
//    val destinationBack = window.history.destinationBack
//    if (destinationBack != null) {
//        destinationBack.mustBe(hash)
//    } else {
//        window.location.hash.mustBe(hash)
//    }
//}
