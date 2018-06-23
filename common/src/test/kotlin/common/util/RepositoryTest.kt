package common.util

import kotlin.test.Test

/**
 * A test for [Repository]
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 1:58 PM
 */
class RepositoryTest {
    init {
        PlatformProvider.instance = JvmProvider
    }

    @Test
    fun InMemoryRepositoryShouldSnapshotList() {
        inMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
        val list1 = inMemoryRepositoryForTesting.list()

        inMemoryRepositoryForTesting.save(null, EntityForTesting("B"))
        val list2 = inMemoryRepositoryForTesting.list()
        list2.size.mustBe(list1.size + 1)
    }

    @Test
    fun InMemoryRepositoryShouldSaveIntoTheSameIndexAsTheOriginal() {
        val id1 = inMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
        inMemoryRepositoryForTesting.save(null, EntityForTesting("B"))
        inMemoryRepositoryForTesting.save(null, EntityForTesting("C"))
        val entity1 = inMemoryRepositoryForTesting.list().find { it.id == id1 }!!
        val index1 = inMemoryRepositoryForTesting.list().indexOf(entity1)
        val modifiedEntity1 = entity1.copy(name = "A2")
        inMemoryRepositoryForTesting.save(entity1, modifiedEntity1)
        inMemoryRepositoryForTesting.list().indexOf(modifiedEntity1).mustBe(index1)
    }

    @Test
    fun InMemoryRepositoryShouldNotifyListeners() {
        val listener = CountingListener<EntityForTesting>()
        inMemoryRepositoryForTesting.addListener(listener)
        listener.onSavedCount.mustBe(0)
        listener.onRemovedCount.mustBe(0)

        val id1 = inMemoryRepositoryForTesting.save(null, EntityForTesting("A"))
        listener.onSavedCount.mustBe(1)
        inMemoryRepositoryForTesting.remove(id1)
        listener.onRemovedCount.mustBe(1)
    }

    @Test
    fun InMemoryRepositoryShouldNotNotifyAListenerOnceTheListenerHasBeenRemoved() {
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

    @Test
    fun InMemoryRepositoryShouldNotNotifyListenersForNoOpSave() {
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

    @Test
    fun InMemoryRepositoryShouldNotNotifyListenersForNoOpRemove() {
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

data class EntityForTesting(val name: String, val id: ID<EntityForTesting>? = null) : WithID<EntityForTesting> {
    override fun getID(): ID<EntityForTesting>? = id

    override fun withID(id: ID<EntityForTesting>): EntityForTesting = copy(id = id)
}

open class InMemoryRepositoryForTesting : InMemoryRepository<EntityForTesting>()

val inMemoryRepositoryForTesting = InMemoryRepositoryForTesting()

class CountingListener<in T> : RepositoryListener<T> {
    var onSavedCount: Int = 0
    var onRemovedCount: Int = 0
    var onHiddenCount: Int = 0
    var onVisibleCount: Int = 0

    override fun onSaved(original: T?, replacementWithID: T) {
        onSavedCount++
    }

    override fun onRemoved(item: T) {
        onRemovedCount++
    }

    override fun onVisibilityChanged(item: T, visible: Boolean) {
        if (visible) {
            onVisibleCount++
        } else {
            onHiddenCount++
        }
    }
}
