package client.util

import client.ID
import client.component.UndoComponent
import common.util.*
import org.w3c.dom.get
import kotlin.browser.localStorage
import kotlin.test.*

/**
 * A test for [LocalStorageRepository].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
@Suppress("unused")
class LocalStorageRepositoryTest {
    init {
        PlatformProvider.instance = JavascriptProvider
    }

    @Test
    fun LocalStorageRepository_shouldSaveIntoTheSameIndexAsTheOriginal() {
        val id1 = LocalStorageRepositoryForTesting.save(null, EntityForTesting("A"))
        LocalStorageRepositoryForTesting.save(null, EntityForTesting("B"))
        LocalStorageRepositoryForTesting.save(null, EntityForTesting("C"))
        val entity1 = LocalStorageRepositoryForTesting.list().find { it.id == id1 }!!
        val index1 = LocalStorageRepositoryForTesting.list().indexOf(entity1)
        val modifiedEntity1 = entity1.copy(name = "A2")
        LocalStorageRepositoryForTesting.save(entity1, modifiedEntity1)
        LocalStorageRepositoryForTesting.list().indexOf(modifiedEntity1).mustBe(index1)
    }

    @Test
    fun LocalStorageRepository_shouldUseTheProvidedIdWhenCreatingAnEntity() {
        val id = LocalStorageRepositoryForTesting.save(null, EntityForTesting("A", id = ID(55442)))
        id.mustBe(ID(55442))
    }

    @Test
    fun LocalStorageRepository_shouldNotifyListeners() {
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
        LocalStorageRepositoryForTesting.addListener(listener)
        onSavedCount.mustBe(0)
        onRemovedCount.mustBe(0)

        val id1 = LocalStorageRepositoryForTesting.save(null, EntityForTesting("A"))
        onSavedCount.mustBe(1)
        LocalStorageRepositoryForTesting.remove(id1)
        onRemovedCount.mustBe(1)
    }

    @Test
    fun LocalStorageRepository_shouldNotLoadFromLocalStorageUntilAMethodIsCalledOnTheRepository() {
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)

        // should not look at localStorage until a method is called
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localStorage[localRepository.localStorageKey].mustBe(null)

        // should get a snapshot of localStorage
        localRepository.list().size.mustBe(0)
        localStorage[localRepository.localStorageKey].mustBe(null)

        val localRepositoryCopy = LocalStorageRepositoryForTesting(localStorageKey)
        localRepositoryCopy.save(EntityForTesting("George"))
        // unfortunately, it doesn't read from localStorage at this point
        localRepository.list().size.mustBe(0)
    }

    @Test
    fun LocalStorageRepository_shouldIncludeIdOnOriginalWhenNotifyingListeners() {
        val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                original?.id?.mustBe(replacementWithID.id)
            }
            override fun onRemoved(item: EntityForTesting) {}
        }
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localRepository.addListener(listener)

        val originalEntity = EntityForTesting("A")
        val id1 = localRepository.save(null, originalEntity)

        localRepository.save(originalEntity.withID(id1), EntityForTesting("A"))
        localRepository.save(originalEntity, EntityForTesting("B").withID(id1))
    }

    @Test
    fun LocalStorageRepository_shouldNotNotifyListenersForNoOpSave() {
        var onSavedCount = 0
        val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                onSavedCount++
            }
            override fun onRemoved(item: EntityForTesting) {}
        }
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localRepository.addListener(listener)
        onSavedCount.mustBe(0)

        val originalEntity = EntityForTesting("A")
        val id1 = localRepository.save(null, originalEntity)
        onSavedCount.mustBe(1)

        localRepository.save(originalEntity.withID(id1), EntityForTesting("A"))
        onSavedCount.mustBe(1)

        localRepository.save(originalEntity.withID(id1), EntityForTesting("A").withID(id1))
        onSavedCount.mustBe(1)
    }

    @Test
    fun LocalStorageRepository_shouldNotNotifylistenersForNoOpRemove() {
        var onRemovedCount = 0
        val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
            override fun onRemoved(item: EntityForTesting) {
                onRemovedCount++
            }
            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {}
        }
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localRepository.addListener(listener)
        onRemovedCount.mustBe(0)

        val originalEntity = EntityForTesting("A")
        val id1 = localRepository.save(null, originalEntity)

        localRepository.remove(id1)
        onRemovedCount.mustBe(1)
        localRepository.remove(id1)
        onRemovedCount.mustBe(1)
        localRepository.remove(originalEntity.withID(id1))
        onRemovedCount.mustBe(1)
    }

    @Test
    fun LocalStorageRepository_shouldNotStoreIfListenerFailsForSave() {
        val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
            override fun onRemoved(item: EntityForTesting) {
            }

            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                throw IntentionalException()
            }
        }
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localRepository.addListener(listener)

        val originalEntity = EntityForTesting("A")
        try {
            localRepository.save(null, originalEntity)
            fail("expected failure")
        } catch (e: IntentionalException) {
            //expected
            LocalStorageRepositoryForTesting(localStorageKey).list().size.mustBe(0)
        }
    }

    @Test
    fun LocalStorageRepository_shouldIncludeListenerOperationsInUndoForSave() {
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        UndoComponent.watch(localRepository)

        val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
            override fun onRemoved(item: EntityForTesting) {
            }

            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                if (replacementWithID.name == "Sith Lord") {
                    localRepository.save(EntityForTesting("Apprentice"))
                }
            }
        }
        localRepository.addListener(listener)

        localRepository.save(EntityForTesting("Sith Lord"))
        localRepository.list().size.mustBe(2)

        UndoComponent.undo()
        localRepository.list().size.mustBe(0)
    }

    @Test
    fun LocalStorageRepository_shouldIncludeListenerOperationsInUndoForRemove() {
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        UndoComponent.watch(localRepository)
        val sithLordId = localRepository.save(EntityForTesting("Sith Lord"))
        val apprenticeId = localRepository.save(EntityForTesting("Apprentice"))

        val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
            override fun onRemoved(item: EntityForTesting) {
                if (item.name == "Sith Lord") {
                    localRepository.remove(apprenticeId)
                }
            }

            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
            }
        }
        localRepository.addListener(listener)

        localRepository.remove(sithLordId)
        localRepository.list().size.mustBe(0)

        UndoComponent.undo()
        localRepository.list().size.mustBe(2)
    }
}

data class EntityForTesting(val name: String, val id: ID<EntityForTesting>? = null) : WithID<EntityForTesting> {
    override fun getID(): ID<EntityForTesting>? = id

    override fun withID(id: ID<EntityForTesting>): EntityForTesting = copy(id = id)
}

interface EntityForTestingJS {
    val name: String
    val id: IDJS?
}

fun EntityForTestingJS.toNormal(): EntityForTesting = EntityForTesting(name, id?.toNormal())

open class LocalStorageRepositoryForTesting(localStorageKey: String) : LocalStorageRepository<EntityForTesting, EntityForTestingJS>(localStorageKey, { it.toNormal() }) {
    companion object : LocalStorageRepositoryForTesting("entityForTesting")
}

class IntentionalException : RuntimeException("intentional")
