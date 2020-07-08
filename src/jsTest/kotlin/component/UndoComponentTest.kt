package component

import component.entity.EntityForTesting
import component.entity.ID
import kotlinx.atomicfu.atomic
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

    @Test
    fun itShouldAllowCreateUndoRedoUndoRedo() = runTest {
        val originalUndoCount = UndoComponent.undoCount
        val newId = doUndoableCreate(null, EntityForTesting("George"))
        read(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.undo()
        read(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(0)

        UndoComponent.redo()
        read(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.undo()
        read(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(0)

        UndoComponent.redo()
        read(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)
    }

    @Test
    fun itShouldAllowDeleteUndoRedoUndoRedo() = runTest {
        val originalUndoCount = UndoComponent.undoCount
        val newId = doUndoableCreate(null, EntityForTesting("George"))

        doUndoableDelete(newId)
        read(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        read(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        read(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        read(newId).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        read(newId).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)
    }

    @Test
    fun itShouldAllowUpdateUndoRedoUndoRedo() = runTest {
        val originalUndoCount = UndoComponent.undoCount
        val originalValue = EntityForTesting("George")
        val newId = doUndoableCreate(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        doUndoableCreate(originalValueWithId, EntityForTesting("Harry"))
        read(newId)?.name.mustBe("Harry")
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        read(newId)?.name.mustBe("George")
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        read(newId)?.name.mustBe("Harry")
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.undo()
        read(newId)?.name.mustBe("George")
        (UndoComponent.undoCount - originalUndoCount).mustBe(1)

        UndoComponent.redo()
        read(newId)?.name.mustBe("Harry")
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)
    }

    @Test
    fun itShouldAllowBatchUndoRedoUndoRedo() = runTest {
        val originalUndoCount = UndoComponent.undoCount
        val originalValue = EntityForTesting("George")
        val newId = doUndoableCreate(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        val newId2 = doUndoableCreate(null, EntityForTesting("Bob"))

        UndoComponent.undoable("batch", "undo batch") {
            doUndoableCreate(originalValueWithId, EntityForTesting("Harry"))
            doUndoableDelete(newId2)
        }

        read(newId)?.name.mustBe("Harry")
        read(newId2).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(3)

        UndoComponent.undo()
        read(newId)?.name.mustBe("George")
        read(newId2).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.redo()
        read(newId)?.name.mustBe("Harry")
        read(newId2).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(3)

        UndoComponent.undo()
        read(newId)?.name.mustBe("George")
        read(newId2).mustNotBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(2)

        UndoComponent.redo()
        read(newId)?.name.mustBe("Harry")
        read(newId2).mustBe(null)
        (UndoComponent.undoCount - originalUndoCount).mustBe(3)
    }

    @Test
    fun itShouldSupportNotAllowingUndoing() = runTest {
        val originalValue = EntityForTesting("George")
        val newId = doUndoableCreate(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        val newId2 = doUndoableCreate(EntityForTesting("Bob"))

        val originalUndoCount = UndoComponent.undoCount

        UndoComponent.notUndoable {
            doUndoableCreate(originalValueWithId, EntityForTesting("Harry"))
            doUndoableDelete(newId2)
        }

        (UndoComponent.undoCount - originalUndoCount).mustBe(0)
    }

    @Test
    fun itShouldHandleUpdatingTheSameEntityTwice() = runTest {
        val originalValue = EntityForTesting("George")
        val newId = doUndoableCreate(null, originalValue)
        val originalValueWithId = originalValue.withID(newId)

        UndoComponent.undoable("did batch", "undid batch") {
            val updatedValue = originalValueWithId.copy("Bob")
            doUndoableCreate(originalValueWithId, updatedValue)
            doUndoableCreate(updatedValue, updatedValue.copy("Harry"))
        }

        read(newId)?.name.mustBe("Harry")

        UndoComponent.undo()
        read(newId)?.name.mustBe("George")

        UndoComponent.redo()
        read(newId)?.name.mustBe("Harry")
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
        UndoComponent.undoable("Ordered Operations", "Undo Ordered Operations") {
            doUndoableCreate(EntityForTesting("Adam"))
            doUndoableCreate(EntityForTesting("Eve"))
        }
        UndoComponent.undo()
        UndoComponent.redo()
        storage.values.toList().map { it.name }.mustBe(listOf("Adam", "Eve"))
    }

    private val nextId = atomic(100L)
    val storage = mutableMapOf<ID<EntityForTesting>, EntityForTesting>()

    private fun doUndoableCreate(entityForTesting: EntityForTesting): ID<EntityForTesting> {
        return doUndoableCreate(null, entityForTesting)
    }

    private fun doUndoableCreate(previousValue: EntityForTesting?, entityForTesting: EntityForTesting): ID<EntityForTesting> {
        val newValue = entityForTesting.withID(entityForTesting.id ?: previousValue?.id ?: ID(nextId.incrementAndGet()))
        val priorValue = storage.put(newValue.requiredId, newValue)

        if (priorValue != null) {
            UndoComponent.addUndoCommand(object : Command("Reverted $newValue to $priorValue") {
                private val self = this

                override suspend fun executeAndGetOpposite(): Command {
                    storage[newValue.requiredId] = priorValue
                    return object : Command("Saved $newValue") {
                        override suspend fun executeAndGetOpposite(): Command {
                            storage[newValue.requiredId] = newValue
                            return self
                        }
                    }
                }
            })
        } else {
            UndoComponent.addUndoCommand(object : Command("Deleted $newValue") {
                private val self = this

                override suspend fun executeAndGetOpposite(): Command {
                    storage.remove(newValue.requiredId)
                    return object : Command("Added $newValue") {
                        override suspend fun executeAndGetOpposite(): Command {
                            storage[newValue.requiredId] = newValue
                            return self
                        }
                    }
                }
            })
        }

        return newValue.requiredId
    }

    private fun doUndoableDelete(id: ID<EntityForTesting>) {
        val removedValue = storage.remove(id)
        if (removedValue != null) {
            val readdCommand = object : Command("Added $removedValue") {
                private val self = this

                override suspend fun executeAndGetOpposite(): Command {
                    storage[id] = removedValue
                    return object : Command("Removed $removedValue") {
                        override suspend fun executeAndGetOpposite(): Command {
                            storage.remove(id)
                            return self
                        }
                    }
                }
            }
            UndoComponent.addUndoCommand(readdCommand)
        }
    }

    private fun read(id: ID<EntityForTesting>): EntityForTesting? {
        return storage[id]
    }
}

fun <T> runTest(block: suspend () -> T): dynamic = GlobalScope.async { block() }.asPromise()
