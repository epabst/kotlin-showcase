package client.util

import client.ID
import client.component.UndoComponent
import common.util.*
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.toProperty
import org.w3c.dom.get
import kotlin.browser.localStorage
import kotlin.browser.window
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
        val stubRequestAnimationFrame: ((Double) -> Unit) -> Int = { it.invoke(0.toDouble()); 0 }
        window.asDynamic().requestAnimationFrame = stubRequestAnimationFrame
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
        val listener = CountingListener<EntityForTesting>()
        LocalStorageRepositoryForTesting.addListener(listener)
        listener.onSavedCount.mustBe(0)
        listener.onRemovedCount.mustBe(0)

        val id1 = LocalStorageRepositoryForTesting.save(null, EntityForTesting("A"))
        listener.onSavedCount.mustBe(1)
        LocalStorageRepositoryForTesting.remove(id1)
        listener.onRemovedCount.mustBe(1)
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
            override fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {}
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
        val listener = CountingListener<EntityForTesting>()
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localRepository.addListener(listener)
        listener.onSavedCount.mustBe(0)

        val originalEntity = EntityForTesting("A")
        val id1 = localRepository.save(null, originalEntity)
        listener.onSavedCount.mustBe(1)

        localRepository.save(originalEntity.withID(id1), EntityForTesting("A"))
        listener.onSavedCount.mustBe(1)

        localRepository.save(originalEntity.withID(id1), EntityForTesting("A").withID(id1))
        listener.onSavedCount.mustBe(1)
    }

    @Test
    fun LocalStorageRepository_shouldNotNotifylistenersForNoOpRemove() {
        val listener = CountingListener<EntityForTesting>()
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localRepository.addListener(listener)
        listener.onRemovedCount.mustBe(0)

        val originalEntity = EntityForTesting("A")
        val id1 = localRepository.save(null, originalEntity)

        localRepository.remove(id1)
        listener.onRemovedCount.mustBe(1)
        localRepository.remove(id1)
        listener.onRemovedCount.mustBe(1)
        localRepository.remove(originalEntity.withID(id1))
        listener.onRemovedCount.mustBe(1)
    }

    @Test
    fun LocalStorageRepository_shouldStoreEvenIfListenerFailsForSave() {
        val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
            override fun onRemoved(item: EntityForTesting) {
            }

            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                throw IntentionalException()
            }
            override fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {}
        }
        val localStorageKey = "unitTest"
        localStorage.removeItem(localStorageKey)
        val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
        localRepository.addListener(listener)

        try {
            localRepository.save(EntityForTesting("A"))
            fail("expected failure")
        } catch (e: IntentionalException) {
            //expected
            LocalStorageRepositoryForTesting(localStorageKey).list().size.mustBe(1)
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
            override fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {}
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
            override fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {}
        }
        localRepository.addListener(listener)

        localRepository.remove(sithLordId)
        localRepository.list().size.mustBe(0)

        UndoComponent.undo()
        localRepository.list().size.mustBe(2)
    }

    @Test
    fun LocalStorageRepository_shouldNotifyRemovalWhenLocalStorageKeyIsRemoved() {
        val relativePath = "unitTest"
        localStorage.removeItem(relativePath + "/a")
        localStorage.removeItem(relativePath + "/b")
        val localStorageKeysProperty = setOf(relativePath + "/a", relativePath + "/b").toProperty()
        val localRepository = LocalStorageRepositoryForTesting(relativePath, localStorageKeysProperty, { relativePath + "/" + it.name.take(1).toLowerCase() })
        localRepository.save(EntityForTesting("Adam"))

        val countingListener = CountingListener<EntityForTesting>()
        localRepository.addListener(countingListener)
        localRepository.addListener(object : RepositoryListener<EntityForTesting> {
            override fun onRemoved(item: EntityForTesting) {}
            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {}
            override fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {}
        })

        localStorageKeysProperty.set(emptySet())
        countingListener.onRemovedCount.mustBe(0)
        countingListener.onSavedCount.mustBe(0)
        countingListener.onVisibleCount.mustBe(0)
        countingListener.onHiddenCount.mustBe(1)
    }

    @Test
    fun LocalStorageRepository_shouldNotifyOfSaveWhenLocalStorageKeyIsAdded() {
        val relativePath = "unitTest"
        localStorage.removeItem(relativePath + "/a")
        localStorage.removeItem(relativePath + "/b")
        val setOfAll = setOf(relativePath + "/a", relativePath + "/b")
        val localStorageKeysProperty = setOfAll.toProperty()
        val localRepository = LocalStorageRepositoryForTesting(relativePath, localStorageKeysProperty, { relativePath + "/" + it.name.take(1).toLowerCase() })
        localRepository.save(EntityForTesting("Adam"))

        localStorageKeysProperty.set(emptySet())
        val localRepositoryCopy = LocalStorageRepositoryForTesting(relativePath, localStorageKeysProperty, { relativePath + "/" + it.name.take(1).toLowerCase() })

        val countingListener = CountingListener<EntityForTesting>()
        localRepositoryCopy.addListener(countingListener)
        localRepositoryCopy.addListener(object : RepositoryListener<EntityForTesting> {
            override fun onRemoved(item: EntityForTesting) {}
            override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {}
            override fun onVisibilityChanged(item: EntityForTesting, visible: Boolean) {}
        })

        localStorageKeysProperty.set(setOfAll)
        countingListener.onRemovedCount.mustBe(0)
        countingListener.onSavedCount.mustBe(0)
        countingListener.onHiddenCount.mustBe(0)
        countingListener.onVisibleCount.mustBe(1)
    }

    @Test
    fun LocalStorageRepository_shouldRemoveFromOldLocalStorageKeyWhenChangingKeys() {
        val relativePath = "unitTest"
        localStorage.removeItem(relativePath + "/a")
        localStorage.removeItem(relativePath + "/b")
        val setOfAll = setOf(relativePath + "/a", relativePath + "/b")
        val localStorageKeysProperty = setOfAll.toProperty()
        val localRepository = LocalStorageRepositoryForTesting(relativePath, localStorageKeysProperty, { relativePath + "/" + it.name.take(1).toLowerCase() })
        localRepository.list().size.mustBe(0)
        val original = localRepository.saveAndGet(EntityForTesting("Adam"))
        localRepository.save(original, original.copy(name = "Bob"))

        val localRepositoryCopy = LocalStorageRepositoryForTesting(relativePath, localStorageKeysProperty, { relativePath + "/" + it.name.take(1).toLowerCase() })
        localRepositoryCopy.list().size.mustBe(1)
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

open class LocalStorageRepositoryForTesting(relativePath: String,
                                            localStorageKeysProperty: ReadOnlyProperty<Set<String>>,
                                            localStorageKeyChooser: (EntityForTesting) -> String)
    : LocalStorageRepository<EntityForTesting, EntityForTestingJS>(
        relativePath, localStorageKeysProperty, { it.toNormal() }, localStorageKeyChooser) {

    constructor(localStorageKey: String) : this(localStorageKey, setOf(localStorageKey).toProperty(), { localStorageKey })

    val localStorageKey: String get() = localStorageKeys.first()

    companion object : LocalStorageRepositoryForTesting("entityForTesting")
}

class IntentionalException : RuntimeException("intentional")


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
