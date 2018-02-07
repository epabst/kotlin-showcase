package common.util

import org.jetbrains.spek.api.Spek

/**
 * A test for [Repository]
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 1:58 PM
 */
class RepositoryTest : Spek({
    PlatformProvider.instance = JvmProvider

    describe("InMemoryRepository") {
        it("should snapshot list") {
            inMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
            val list1 = inMemoryRepositoryForTesting.list()

            inMemoryRepositoryForTesting.save(null, EntityForTesting("B"))
            val list2 = inMemoryRepositoryForTesting.list()
            list2.size.mustBe(list1.size + 1)
        }

        it("should save into the same index as the original") {
            val id1 = inMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
            inMemoryRepositoryForTesting.save(null, EntityForTesting("B"))
            inMemoryRepositoryForTesting.save(null, EntityForTesting("C"))
            val entity1 = inMemoryRepositoryForTesting.list().find { it.id == id1 }!!
            val index1 = inMemoryRepositoryForTesting.list().indexOf(entity1)
            val modifiedEntity1 = entity1.copy(name = "A2")
            inMemoryRepositoryForTesting.save(entity1, modifiedEntity1)
            inMemoryRepositoryForTesting.list().indexOf(modifiedEntity1).mustBe(index1)
        }

        it("should notify listeners") {
            val listener = CountingListener<EntityForTesting>()
            inMemoryRepositoryForTesting.addListener(listener)
            listener.onSavedCount.mustBe(0)
            listener.onRemovedCount.mustBe(0)

            val id1 = inMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
            listener.onSavedCount.mustBe(1)
            inMemoryRepositoryForTesting.remove(id1)
            listener.onRemovedCount.mustBe(1)
        }

        it("should not notify a listener once the listener has been removed") {
            val listener = CountingListener<EntityForTesting>()
            inMemoryRepositoryForTesting.addListener(listener)

            val id1 = inMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
            listener.onSavedCount.mustBe(1)
            inMemoryRepositoryForTesting.remove(id1)
            listener.onRemovedCount.mustBe(1)

            inMemoryRepositoryForTesting.removeListener(listener)

            val id2 = inMemoryRepositoryForTesting.save(null, EntityForTesting("B"))
            listener.onSavedCount.mustBe(1)
            inMemoryRepositoryForTesting.remove(id2)
            listener.onRemovedCount.mustBe(1)
        }

        it("should not notify listeners for no-op save") {
            val listener = CountingListener<EntityForTesting>()
            inMemoryRepositoryForTesting.addListener(listener)
            listener.onSavedCount.mustBe(0)

            val originalEntity = EntityForTesting("A")
            val id1 = inMemoryRepositoryForTesting.save(null, originalEntity)
            listener.onSavedCount.mustBe(1)

            inMemoryRepositoryForTesting.save(originalEntity.withID(id1), EntityForTesting("A"))
            listener.onSavedCount.mustBe(1)

            inMemoryRepositoryForTesting.save(originalEntity.withID(id1), EntityForTesting("A").withID(id1))
            listener.onSavedCount.mustBe(1)
        }

        it("should not notify listeners for no-op remove") {
            val listener = CountingListener<EntityForTesting>()
            inMemoryRepositoryForTesting.addListener(listener)
            listener.onRemovedCount.mustBe(0)

            val originalEntity = EntityForTesting("A")
            val id1 = inMemoryRepositoryForTesting.save(null, originalEntity)

            inMemoryRepositoryForTesting.remove(id1)
            listener.onRemovedCount.mustBe(1)
            inMemoryRepositoryForTesting.remove(id1)
            listener.onRemovedCount.mustBe(1)
            inMemoryRepositoryForTesting.remove(originalEntity.withID(id1))
            listener.onRemovedCount.mustBe(1)
        }
    }

    describe("CompositeRepository") {
        it("should delegate to the correct Repository") {
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()
            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            val georgeId = compositeRepository.save(EntityForTesting("George"))
            val xanderId = compositeRepository.save(EntityForTesting("Xander"))
            repositoryA.find(georgeId).mustNotBe(null)
            repositoryA.find(xanderId).mustBe(null)
            repositoryN.find(georgeId).mustBe(null)
            repositoryN.find(xanderId).mustNotBe(null)
            compositeRepository.find(georgeId).mustNotBe(null)
        }

        it("should have list include all Repositories") {
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()
            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            compositeRepository.save(EntityForTesting("George"))
            compositeRepository.save(EntityForTesting("Xander"))
            compositeRepository.list().size.mustBe(2)
        }

        it("should find using find (rather than list)") {
            val repositoryA = object : InMemoryRepository<EntityForTesting>() {
                override fun find(id: ID<EntityForTesting>): EntityForTesting? {
                    return null
                }
            }
            val repositoryN = object : InMemoryRepository<EntityForTesting>() {
                override fun find(id: ID<EntityForTesting>): EntityForTesting? {
                    return EntityForTesting("Xander")
                }
            }
            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            compositeRepository.find(repositoryN.generateID()).mustNotBe(null)
        }

        it("should notify listeners") {
            val listener = CountingListener<EntityForTesting>()
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()
            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            compositeRepository.addListener(listener)
            listener.onSavedCount.mustBe(0)
            listener.onRemovedCount.mustBe(0)

            val id1 = compositeRepository.save(EntityForTesting("A"))
            listener.onSavedCount.mustBe(1)
            compositeRepository.remove(id1)
            listener.onRemovedCount.mustBe(1)
        }

        it("should handle save that moves it to another Repository") {
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()

            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            val entityA = compositeRepository.saveAndGet(EntityForTesting("A"))
            repositoryA.find(entityA.getID()!!).mustNotBe(null)
            repositoryN.find(entityA.getID()!!).mustBe(null)

            val id1 = compositeRepository.save(entityA.copy(name = "N"))
            id1.mustBe(entityA.getID())
            repositoryA.find(id1).mustBe(null)
            repositoryN.find(id1).mustNotBe(null)
        }

        it("should not call categorizer for removeAll") {
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()
            val id = repositoryN.save(EntityForTesting("N"))

            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                throw UnsupportedOperationException("Should not be called")
            }
            // shouldn't throw UnsupportedOperationException
            compositeRepository.removeAll(allItems())
            repositoryN.find(id).mustBe(null)
        }

        it("should notify as removed and added when moving to another Repository") {
            val listener = CountingListener<EntityForTesting>()
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()

            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            val entityA = compositeRepository.saveAndGet(EntityForTesting("A"))

            compositeRepository.addListener(listener)
            listener.onSavedCount.mustBe(0)
            listener.onRemovedCount.mustBe(0)

            compositeRepository.save(entityA.copy(name = "N"))
            listener.onSavedCount.mustBe(1)
            listener.onRemovedCount.mustBe(1)
        }

        it("should wrap in a single undoable the removing and adding when moving to another Repository") {
            var undoableCount = 0
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()
            val entityA = repositoryA.saveAndGet(EntityForTesting("A"))

            val undoProvider: UndoProvider = object : UndoProvider {
                override fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: () -> T): T {
                    undoableCount++
                    //should not have moved yet
                    repositoryA.find(entityA.getID()!!).mustNotBe(null)
                    repositoryN.find(entityA.getID()!!).mustBe(null)
                    val result = function()
                    repositoryA.find(entityA.getID()!!).mustBe(null)
                    repositoryN.find(entityA.getID()!!).mustNotBe(null)
                    return result
                }

                override fun <T> notUndoable(function: () -> T): T {
                    return function()
                }
            }

            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN), undoProvider) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }

            undoableCount.mustBe(0)

            compositeRepository.save(entityA.copy(name = "N"))
            undoableCount.mustBe(1)
        }
    }
})

data class EntityForTesting(val name: String, val id: ID<EntityForTesting>? = null) : WithID<EntityForTesting> {
    override fun getID(): ID<EntityForTesting>? = id

    override fun withID(id: ID<EntityForTesting>): EntityForTesting = copy(id = id)
}

open class InMemoryRepositoryForTesting : InMemoryRepository<EntityForTesting>()

val inMemoryRepositoryForTesting = InMemoryRepositoryForTesting()

class CountingListener<in T> : RepositoryListener<T> {
    var onSavedCount: Int = 0
    var onRemovedCount: Int = 0

    override fun onSaved(original: T?, replacementWithID: T) {
        onSavedCount++
    }

    override fun onRemoved(item: T) {
        onRemovedCount++
    }
}
