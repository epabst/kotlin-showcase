package client.util

import client.ID
import client.component.UndoComponent
import common.util.*
import org.w3c.dom.get
import kotlin.browser.localStorage
import kotlin.test.fail

/**
 * A test for [LocalStorageRepository].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
object LocalStorageRepositoryTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider

        describe("LocalStorageRepository") {
            it("should save into the same index as the original") {
                val id1 = LocalStorageRepositoryForTesting.save(null, EntityForTesting("A"))
                LocalStorageRepositoryForTesting.save(null, EntityForTesting("B"))
                LocalStorageRepositoryForTesting.save(null, EntityForTesting("C"))
                val entity1 = LocalStorageRepositoryForTesting.list().find { it.id == id1 }!!
                val index1 = LocalStorageRepositoryForTesting.list().indexOf(entity1)
                val modifiedEntity1 = entity1.copy(name = "A2")
                LocalStorageRepositoryForTesting.save(entity1, modifiedEntity1)
                LocalStorageRepositoryForTesting.list().indexOf(modifiedEntity1).mustBe(index1)
            }

            it("should use the provided ID when creating an entity") {
                val id = LocalStorageRepositoryForTesting.save(null, EntityForTesting("A", id = ID(55442)))
                id.mustBe(ID(55442))
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
                LocalStorageRepositoryForTesting.addListener(listener)
                onSavedCount.mustBe(0)
                onRemovedCount.mustBe(0)

                val id1 = LocalStorageRepositoryForTesting.save(null, EntityForTesting("A"))
                onSavedCount.mustBe(1)
                LocalStorageRepositoryForTesting.remove(id1)
                onRemovedCount.mustBe(1)
            }

            it("should distinguish between empty and uninitialized") {
                val localStorageKey = "unitTest"
                localStorage.removeItem(localStorageKey)

                // should not look at localStorage until a method is called
                val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
                localStorage[localRepository.localStorageKey].mustBe(null)

                // should initialize localStorage
                val id = localRepository.save(null, EntityForTesting("hello"))
                localStorage[localRepository.localStorageKey].mustNotBe(null)

                localRepository.remove(id) // this should cause it to store an empty list into local storage.
                localStorage[localRepository.localStorageKey].mustNotBe(null)

                val localRepositoryCopy = LocalStorageRepositoryForTesting(localStorageKey)
                localRepositoryCopy.list().size.mustBe(0)
                localStorage[localRepository.localStorageKey].mustNotBe(null)
            }

            it("shouldn't load from localStorage until a method is called on the Repository") {
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

            it("should include ID on original when notifying listeners") {
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

            it("should not notify listeners for no-op save") {
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

            it("should not notify listeners for no-op remove") {
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

            it("should not store if listener fails for save") {
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

            it("should not store if listener fails for remove") {
                val listener: RepositoryListener<EntityForTesting> = object : RepositoryListener<EntityForTesting> {
                    override fun onRemoved(item: EntityForTesting) {
                        throw IntentionalException()
                    }
                    override fun onSaved(original: EntityForTesting?, replacementWithID: EntityForTesting) {
                    }
                }
                val localStorageKey = "unitTest"
                localStorage.removeItem(localStorageKey)
                val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
                localRepository.addListener(listener)

                val id = localRepository.save(EntityForTesting("A"))
                try {
                    localRepository.remove(id)
                    fail("expected failure")
                } catch (e: IntentionalException) {
                    //expected
                    LocalStorageRepositoryForTesting(localStorageKey).list().size.mustBe(1)
                }
            }

            it("should include listener operations in undo for save") {
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

            it("should include listener operations in undo for remove") {
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
