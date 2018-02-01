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
            InMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
            val list1 = InMemoryRepositoryForTesting.list()

            InMemoryRepositoryForTesting.save(null, EntityForTesting("B"))
            val list2 = InMemoryRepositoryForTesting.list()
            list2.size.mustBe(list1.size + 1)
        }

        it("should save into the same index as the original") {
            val id1 = InMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
            InMemoryRepositoryForTesting.save(null, EntityForTesting("B"))
            InMemoryRepositoryForTesting.save(null, EntityForTesting("C"))
            val entity1 = InMemoryRepositoryForTesting.list().find { it.id == id1 }!!
            val index1 = InMemoryRepositoryForTesting.list().indexOf(entity1)
            val modifiedEntity1 = entity1.copy(name = "A2")
            InMemoryRepositoryForTesting.save(entity1, modifiedEntity1)
            InMemoryRepositoryForTesting.list().indexOf(modifiedEntity1).mustBe(index1)
        }

        it("should notify listeners") {
            var onSavedCount = 0
            var onRemovedCount = 0
            val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
                override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                    onSavedCount++
                }

                override fun onRemoved(item: EntityForTesting) {
                    onRemovedCount++
                }
            }
            InMemoryRepositoryForTesting.addListener(listener)
            onSavedCount.mustBe(0)
            onRemovedCount.mustBe(0)

            val id1 = InMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
            onSavedCount.mustBe(1)
            InMemoryRepositoryForTesting.remove(id1)
            onRemovedCount.mustBe(1)
        }

        it("should not notify listeners for no-op save") {
            var onSavedCount = 0
            val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
                override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                    onSavedCount++
                }
                override fun onRemoved(item: EntityForTesting) {}
            }
            InMemoryRepositoryForTesting.addListener(listener)
            onSavedCount.mustBe(0)

            val originalEntity = EntityForTesting("A")
            val id1 = InMemoryRepositoryForTesting.save(null, originalEntity)
            onSavedCount.mustBe(1)

            InMemoryRepositoryForTesting.save(originalEntity.withID(id1), EntityForTesting("A"))
            onSavedCount.mustBe(1)

            InMemoryRepositoryForTesting.save(originalEntity.withID(id1), EntityForTesting("A").withID(id1))
            onSavedCount.mustBe(1)
        }

        it("should not notify listeners for no-op remove") {
            var onRemovedCount = 0
            val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
                override fun onRemoved(item: EntityForTesting) {
                    onRemovedCount++
                }
                override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {}
            }
            InMemoryRepositoryForTesting.addListener(listener)
            onRemovedCount.mustBe(0)

            val originalEntity = EntityForTesting("A")
            val id1 = InMemoryRepositoryForTesting.save(null, originalEntity)

            InMemoryRepositoryForTesting.remove(id1)
            onRemovedCount.mustBe(1)
            InMemoryRepositoryForTesting.remove(id1)
            onRemovedCount.mustBe(1)
            InMemoryRepositoryForTesting.remove(originalEntity.withID(id1))
            onRemovedCount.mustBe(1)
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
            var onSavedCount = 0
            var onRemovedCount = 0
            val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
                override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                    onSavedCount++
                }

                override fun onRemoved(item: EntityForTesting) {
                    onRemovedCount++
                }
            }
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()
            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            compositeRepository.addListener(listener)
            onSavedCount.mustBe(0)
            onRemovedCount.mustBe(0)

            val id1 = compositeRepository.save(EntityForTesting("A"))
            onSavedCount.mustBe(1)
            compositeRepository.remove(id1)
            onRemovedCount.mustBe(1)
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
            var onSavedCount = 0
            var onRemovedCount = 0
            val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
                override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                    onSavedCount++
                }

                override fun onRemoved(item: EntityForTesting) {
                    onRemovedCount++
                }
            }
            val repositoryA = InMemoryRepository<EntityForTesting>()
            val repositoryN = InMemoryRepository<EntityForTesting>()

            val compositeRepository = CompositeRepository(mapOf('a' to repositoryA, 'n' to repositoryN)) {
                entity -> if (entity.name[0].toLowerCase() < 'n') 'a' else 'n'
            }
            val entityA = compositeRepository.saveAndGet(EntityForTesting("A"))

            compositeRepository.addListener(listener)
            onSavedCount.mustBe(0)
            onRemovedCount.mustBe(0)

            compositeRepository.save(entityA.copy(name = "N"))
            onSavedCount.mustBe(1)
            onRemovedCount.mustBe(1)
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

object InMemoryRepositoryForTesting : InMemoryRepository<EntityForTesting>()
