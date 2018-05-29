package client.util

import client.component.UndoComponent
import common.util.*
import kotlin.test.Test

/**
 * A test for [RepositoryCache].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/29/17
 * Time: 11:17 PM
 */
@Suppress("unused")
class RepositoryCacheTest {
    init {
        PlatformProvider.instance = JavascriptProvider
    }

    @Test
    fun idListProperty_shouldWorkWhenDeletingOrReaddingAnEntity() {
        val repository = InMemoryRepository<EntityForTesting>().cached
        val id1 = repository.save(EntityForTesting("A"))

        val property = repository.idListProperty(EntityForTestingByName("A"))
        property.get().mustBe(listOf(id1))

        repository.remove(id1)
        property.get().mustBe(emptyList())

        val id2 = repository.save(EntityForTesting("A"))
        property.get().mustBe(listOf(id2))
    }

    @Test
    fun findFirstOrNullProperty_shouldWorkWhenDeletingOrReaddingAnEntity() {
        val repository = InMemoryRepository<EntityForTesting>().cached
        val id1 = repository.save(EntityForTesting("A"))

        val property = repository.findFirstOrNullProperty(EntityForTestingByName("A"))
        property.get()?.id.mustBe(id1)

        repository.remove(id1)
        property.get().mustBe(null)

        val id2 = repository.save(EntityForTesting("A"))
        property.get()?.id.mustBe(id2)
    }

    @Test
    fun findProperty_shouldWorkWhenDeletingAnEntityAndThenUndoing() {
        val repository = InMemoryRepository<EntityForTesting>().cached
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

data class EntityForTestingByName(val name: String) : RepositoryCriteria<EntityForTesting> {
    override fun invoke(entity: EntityForTesting): Boolean = entity.name == name
}
