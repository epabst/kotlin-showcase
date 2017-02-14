package client.test

import client.IDJS
import client.JavascriptProvider
import client.LocalStorageRepository
import client.toNormal
import common.*

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
                val entity1 = LocalStorageRepositoryForTesting.list.find { it.id == id1 }!!
                val index1 = LocalStorageRepositoryForTesting.list.indexOf(entity1)
                val modifiedEntity1 = entity1.copy(name = "A2")
                LocalStorageRepositoryForTesting.save(entity1, modifiedEntity1)
                LocalStorageRepositoryForTesting.list.indexOf(modifiedEntity1).mustBe(index1)
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
        }
    }
}

data class EntityForTesting(val name: String, val id: ID? = null) : WithID<EntityForTesting> {
    override fun getID(): ID? = id

    override fun withID(id: ID): EntityForTesting = copy(id = id)
}

interface EntityForTestingJS {
    val name: String
    val id: IDJS?
}

fun EntityForTestingJS.toNormal(): EntityForTesting = EntityForTesting(name, id?.toNormal())

object LocalStorageRepositoryForTesting : LocalStorageRepository<EntityForTesting, EntityForTestingJS>("entityForTesting", { it.toNormal() }) {
    override val defaultList: List<EntityForTesting> = emptyList()
}
