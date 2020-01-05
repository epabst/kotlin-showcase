package component

import component.repository.EntityForTesting
import component.repository.LocalStorageRepositoryForTesting
import component.repository.RepositoryListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asPromise
import kotlinx.coroutines.async
import util.mustBe
import util.mustNotBe
import kotlin.test.Test

/**
 * A test for [UndoComponent].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/11/16
 * Time: 3:47 PM
 */
@Suppress("unused")
class UndoComponentTest {
    val repository: LocalStorageRepositoryForTesting
    init {
        repository = LocalStorageRepositoryForTesting
        UndoComponent.watch(repository)
    }

    @Test
    fun itShouldAllowCreateUndoRedoUndoRedo() = runTest {
        val originalUndoCount = UndoComponent.undoCount
        val newId = repository.save(null, EntityForTesting("George"))
        repository.find(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.undo()
        repository.find(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(0)

        UndoComponent.redo()
        repository.find(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.undo()
        repository.find(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(0)

        UndoComponent.redo()
        repository.find(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)
    }

    @Test
    fun itShouldAllowDeleteUndoRedoUndoRedo() = runTest {
        repository.addListener(object : RepositoryListener<EntityForTesting> {
            override suspend fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                console.info(if (original == null) "created $replacementWithID" else "updated to $replacementWithID")
            }

            override suspend fun onRemoved(item: EntityForTesting) {
                console.info("deleted $item")
            }

            override suspend fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {
            }
        })

        val originalUndoCount = UndoComponent.undoCount
        val newId = repository.save(null, EntityForTesting("George"))

        repository.remove(newId)
        repository.find(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        repository.find(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        repository.find(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        repository.find(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        repository.find(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)
    }

    @Test
    fun itShouldAllowUpdateUndoRedoUndoRedo() = runTest {
        val originalUndoCount = UndoComponent.undoCount
        val originalValue = EntityForTesting("George")
        val newId = repository.save(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        repository.save(originalValueWithId, EntityForTesting("Harry"))
        repository.find(newId)?.name.mustBe("Harry")
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        repository.find(newId)?.name.mustBe("George")
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        repository.find(newId)?.name.mustBe("Harry")
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        repository.find(newId)?.name.mustBe("George")
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        repository.find(newId)?.name.mustBe("Harry")
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)
    }

    @Test
    fun itShouldAllowBatchUndoRedoUndoRedo() = runTest {
        val originalUndoCount = UndoComponent.undoCount
        val originalValue = EntityForTesting("George")
        val newId = repository.save(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        val newId2 = repository.save(null, EntityForTesting("Bob"))

        UndoComponent.undoable("batch", "undo batch") {
            repository.save(originalValueWithId, EntityForTesting("Harry"))
            repository.remove(newId2)
        }

        repository.find(newId)?.name.mustBe("Harry")
        repository.find(newId2).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(3)

        UndoComponent.undo()
        repository.find(newId)?.name.mustBe("George")
        repository.find(newId2).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.redo()
        repository.find(newId)?.name.mustBe("Harry")
        repository.find(newId2).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(3)

        UndoComponent.undo()
        repository.find(newId)?.name.mustBe("George")
        repository.find(newId2).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.redo()
        repository.find(newId)?.name.mustBe("Harry")
        repository.find(newId2).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(3)
    }

    @Test
    fun itShouldSupportNotAllowingUndoing() = runTest {
        val originalValue = EntityForTesting("George")
        val newId = repository.save(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        val newId2 = repository.save(EntityForTesting("Bob"))

        val originalUndoCount = UndoComponent.undoCount

        UndoComponent.notUndoable {
            repository.save(originalValueWithId, EntityForTesting("Harry"))
            repository.remove(newId2)
        }

        (UndoComponent.undoCount - originalUndoCount).mustBe(0)
    }

    @Test
    fun itShouldHandleUpdatingTheSameEntityTwice() = runTest {
        val originalValue = EntityForTesting("George")
        val newId = repository.save(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        UndoComponent.undoable("did batch", "undid batch") {
            val updatedValue = originalValueWithId.copy("Bob")
            repository.save(originalValueWithId, updatedValue)
            repository.save(updatedValue, updatedValue.copy("Harry"))
        }

        repository.find(newId)?.name.mustBe("Harry")

        UndoComponent.undo()
        repository.find(newId)?.name.mustBe("George")

        UndoComponent.redo()
        repository.find(newId)?.name.mustBe("Harry")
    }

    @Test
    fun itShouldIgnoreANoOpUndoable() = runTest {
        val originalUndoCount = UndoComponent.undoCount

        UndoComponent.undoable("no-op", "no-op") {
            println("Hello")
        }

        (UndoComponent.undoCount - originalUndoCount).mustBe(0)
    }

    @Test
    fun itShouldUndoCommandsInReverseOrderAndRedoInOriginalOrder() = runTest {
        repository.addListener(object : RepositoryListener<EntityForTesting> {
            override suspend fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                if (replacementWithID.name == "Adam") {
                    repository.list().find { it.name == "Eve" }.mustBe(null)
                }
            }

            override suspend fun onRemoved(item: EntityForTesting) {
                if (item.name == "Eve") {
                    repository.list().find { it.name == "Adam" }.mustNotBe(null)
                }
            }

            override suspend fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {
            }
        })

        UndoComponent.undoable("Ordered Operations", "Undo Ordered Operations") {
            repository.save(EntityForTesting("Adam"))
            repository.save(EntityForTesting("Eve"))
        }
        UndoComponent.undo()
        UndoComponent.redo()
    }
}

fun <T> runTest(block: suspend () -> T): dynamic = GlobalScope.async { block() }.asPromise()
