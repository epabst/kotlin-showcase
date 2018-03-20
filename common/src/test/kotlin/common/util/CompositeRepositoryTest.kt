package common.util

import org.jetbrains.spek.api.Spek

/**
 * A test for [CompositeRepository]
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 3/19/2018
 * Time: 3:36 PM
 */
class CompositeRepositoryTest : Spek({
    PlatformProvider.instance = JvmProvider

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

    it("should not notify listeners of remove due to moving to another Repository") {
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
        listener.onRemovedCount.mustBe(0)
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
})
