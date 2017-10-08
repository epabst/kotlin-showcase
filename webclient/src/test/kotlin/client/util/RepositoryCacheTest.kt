package client.util

import client.component.UndoComponent
import common.util.*

/**
 * A test for [RepositoryCache].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/29/17
 * Time: 11:17 PM
 */
object RepositoryCacheTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider

        describe("RepositoryCache") {
            describe("idListProperty") {
                it("should work when deleting or re-adding an entity") {
                    val repository = InMemoryRepository<EntityForTesting>()
                    val id1 = repository.save(EntityForTesting("A"))

                    val property = repository.idListProperty(EntityForTestingByName("A"))
                    property.get().mustBe(listOf(id1))

                    repository.remove(id1)
                    property.get().mustBe(emptyList())

                    val id2 = repository.save(EntityForTesting("A"))
                    property.get().mustBe(listOf(id2))
                }
            }

            describe("findFirstOrNullProperty") {
                it("should work when deleting or re-adding an entity") {
                    val repository = InMemoryRepository<EntityForTesting>()
                    val id1 = repository.save(EntityForTesting("A"))

                    val property = repository.findFirstOrNullProperty(EntityForTestingByName("A"))
                    property.get()?.id.mustBe(id1)

                    repository.remove(id1)
                    property.get().mustBe(null)

                    val id2 = repository.save(EntityForTesting("A"))
                    property.get()?.id.mustBe(id2)
                }
            }

            describe("findProperty") {
                it("should work when deleting an entity and then undoing") {
                    val repository = InMemoryRepository<EntityForTesting>()
                    UndoComponent.watch(repository)
                    val id1 = repository.save(EntityForTesting("A"))

                    val property = repository.findProperty(id1)
                    property.get()?.id.mustBe(id1)

                    repository.remove(id1)
                    property.get().mustBe(null)

                    UndoComponent.undo()
                    property.get()?.id.mustBe(id1)
                }
            }
        }
    }
}

data class EntityForTestingByName(val name: String) : RepositoryCriteria<EntityForTesting> {
    override fun invoke(entity: EntityForTesting): Boolean = entity.name == name
}
