package client.util

import common.util.*
import kotlin.browser.localStorage

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
                val localRepository = LocalStorageRepositoryForTesting(localStorageKey)
                localRepository.isInitialized().mustBe(false)
                val id = localRepository.save(null, EntityForTesting("hello"))
                localRepository.isInitialized().mustBe(true)

                localRepository.remove(id) // this should cause it to store an empty list into local storage.
                localRepository.isInitialized().mustBe(true)

                val localRepositoryCopy = LocalStorageRepositoryForTesting(localStorageKey)
                localRepositoryCopy.list().size.mustBe(0)
                localRepositoryCopy.isInitialized().mustBe(true) // the empty list in local storage should be considered initialized.
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
