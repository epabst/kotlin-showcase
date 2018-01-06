package client.component

import client.util.*
import client.util.JavascriptProvider
import common.util.*

/**
 * A test for [UndoComponent].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/11/16
 * Time: 3:47 PM
 */
object UndoComponentTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider
        val repository = LocalStorageRepositoryForTesting
        UndoComponent.watch(repository)

        it("should allow create, undo, redo, undo, redo") {
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

        it("should allow delete, undo, redo, undo, redo") {
            repository.addListener(object : RepositoryListener<EntityForTesting> {
                override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                    console.info(if (original == null) "created $replacementWithID" else "updated to $replacementWithID")
                }

                override fun onRemoved(item: EntityForTesting) {
                    console.info("deleted $item")
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

        it("should allow update, undo, redo, undo, redo") {
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

        it("should allow batch, undo, redo, undo, redo") {
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

        it("should support not allowing undoing") {
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

        it("should undo commands in reverse order and redo in original order") {
            repository.addListener(object : RepositoryListener<EntityForTesting> {
                override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                    if (replacementWithID.name == "Adam") {
                        repository.list().find { it.name == "Eve" }.mustBe(null)
                    }
                }

                override fun onRemoved(item: EntityForTesting) {
                    if (item.name == "Eve") {
                        repository.list().find { it.name == "Adam" }.mustNotBe(null)
                    }
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
}
