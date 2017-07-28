package common.util

import org.jetbrains.spek.api.Spek

/**
 * A test for [InMemoryRepository]
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 1:58 PM
 */
class InMemoryRepositoryTest : Spek({
    PlatformProvider.instance = JvmProvider

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
})

data class EntityForTesting(val name: String, val id: ID<EntityForTesting>? = null) : WithID<EntityForTesting> {
    override fun getID(): ID<EntityForTesting>? = id

    override fun withID(id: ID<EntityForTesting>): EntityForTesting = copy(id = id)
}

object InMemoryRepositoryForTesting : InMemoryRepository<EntityForTesting>()
