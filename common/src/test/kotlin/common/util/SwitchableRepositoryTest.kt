package common.util

import org.jetbrains.spek.api.Spek

/**
 * A test for [SwitchableRepository]
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 2/7/2018
 * Time: 5:18 AM
 */
class SwitchableRepositoryTest : Spek({
    PlatformProvider.instance = JvmProvider

    it("should notify listeners of data changes due to switching delegate Repository") {
        val listener1 = CountingListener<EntityForTesting>()
        val repository1 = InMemoryRepositoryForTesting()
        val repository2 = InMemoryRepositoryForTesting()
        repository2.save(EntityForTesting("B"))
        repository2.save(EntityForTesting("C"))

        val switchableRepository = SwitchableRepository(repository1)
        switchableRepository.addListener(listener1)

        switchableRepository.save(EntityForTesting("A"))
        listener1.onRemovedCount.mustBe(0)
        listener1.onSavedCount.mustBe(1)

        switchableRepository.delegate = repository2
        listener1.onRemovedCount.mustBe(1)
        listener1.onSavedCount.mustBe(3)
    }

    it("should not notify listeners delegate Repositories when switching delegate Repository") {
        val listener1 = CountingListener<EntityForTesting>()
        val listener2 = CountingListener<EntityForTesting>()
        val repository1 = InMemoryRepositoryForTesting()
        val repository2 = InMemoryRepositoryForTesting()
        repository2.save(EntityForTesting("B"))
        repository2.save(EntityForTesting("C"))
        repository1.addListener(listener1)
        repository2.addListener(listener2)
        listener1.onSavedCount.mustBe(0)
        listener1.onRemovedCount.mustBe(0)
        listener2.onSavedCount.mustBe(0)
        listener2.onRemovedCount.mustBe(0)

        val switchableRepository = SwitchableRepository(repository1)

        switchableRepository.save(EntityForTesting("A"))
        listener1.onSavedCount.mustBe(1)
        listener1.onRemovedCount.mustBe(0)
        listener2.onSavedCount.mustBe(0)
        listener2.onRemovedCount.mustBe(0)

        switchableRepository.delegate = repository2
        listener1.onSavedCount.mustBe(1)
        listener1.onRemovedCount.mustBe(0)
        listener2.onSavedCount.mustBe(0)
        listener2.onRemovedCount.mustBe(0)
    }

    it("should not notify a listener once the listener has been removed") {
        val listener = CountingListener<EntityForTesting>()
        val repository1 = InMemoryRepositoryForTesting()

        val switchableRepository = SwitchableRepository(repository1)
        switchableRepository.addListener(listener)
        
        val id1 = switchableRepository.save(EntityForTesting("A"))
        listener.onSavedCount.mustBe(1)
        switchableRepository.remove(id1)
        listener.onRemovedCount.mustBe(1)

        switchableRepository.removeListener(listener)

        val id2 = switchableRepository.save(EntityForTesting("B"))
        listener.onSavedCount.mustBe(1)
        switchableRepository.remove(id2)
        listener.onRemovedCount.mustBe(1)
    }

    it("should not notify listeners when setting delegate to the same value") {
        val listener1 = CountingListener<EntityForTesting>()
        val repository1 = InMemoryRepositoryForTesting()
        
        val switchableRepository = SwitchableRepository(repository1)
        switchableRepository.addListener(listener1)

        switchableRepository.save(EntityForTesting("A"))
        listener1.onRemovedCount.mustBe(0)
        listener1.onSavedCount.mustBe(1)

        switchableRepository.delegate = repository1
        listener1.onRemovedCount.mustBe(0)
        listener1.onSavedCount.mustBe(1)
    }

    it("should have list use current Repository") {
        val repository1 = InMemoryRepositoryForTesting()
        val repository2 = InMemoryRepositoryForTesting()
        repository2.save(EntityForTesting("George"))

        val switchableRepository = SwitchableRepository(repository1)
        switchableRepository.delegate = repository2
        switchableRepository.list().size.mustBe(1)

        repository2.save(EntityForTesting("Hal"))
        switchableRepository.list().size.mustBe(2)
    }

    it("should find using find (rather than list)") {
        val repository1 = object : InMemoryRepositoryForTesting() {
            override fun find(id: ID<EntityForTesting>): EntityForTesting? {
                return EntityForTesting("Xander")
            }
        }
        val switchableRepository = SwitchableRepository(repository1)
        switchableRepository.find(repository1.generateID()).mustNotBe(null)
    }

    it("should wrap switching of the repository in an undoable") {
        val repository1 = InMemoryRepositoryForTesting()
        val repository2 = InMemoryRepositoryForTesting()
        repository1.save(EntityForTesting("A"))
        repository2.save(EntityForTesting("B"))
        repository2.save(EntityForTesting("C"))

        val undoProvider = CountingUndoProvider()

        val switchableRepository = SwitchableRepository(repository1, undoProvider)
        undoProvider.undoableCount.mustBe(0)
        undoProvider.notUndoableCount.mustBe(0)

        switchableRepository.delegate = repository2
        undoProvider.undoableCount.mustBe(0)
        undoProvider.notUndoableCount.mustBe(1)
    }
})

class CountingUndoProvider : UndoProvider {
    var undoableCount: Int = 0
    var notUndoableCount: Int = 0

    override fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: () -> T): T {
        undoableCount++
        return function()
    }

    override fun <T> notUndoable(function: () -> T): T {
        notUndoableCount++
        return function()
    }
}
