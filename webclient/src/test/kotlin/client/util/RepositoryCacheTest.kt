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
    fun listProperty_shouldNotAddOrRemoveItemThatDoesNotMatchCriteria() {
        val repository = InMemoryRepository<EntityForTesting>().cached
        val criteria: RepositoryCriteria<EntityForTesting> = object : RepositoryCriteria<EntityForTesting> {
            override fun invoke(entity: EntityForTesting): Boolean = entity.name.toLowerCase().startsWith("b")
            override fun equals(other: Any?): Boolean = this === other
            override fun hashCode(): Int = 55
        }
        val listProperty = repository.listProperty(criteria)
        listProperty.get().size.mustBe(0)

        val id1 = repository.save(EntityForTesting("A"))
        listProperty.get().size.mustBe(0)

        val id2 = repository.save(EntityForTesting("B"))
        listProperty.get().size.mustBe(1)

        repository.remove(id1)
        listProperty.get().size.mustBe(1)

        repository.remove(id2)
        listProperty.get().size.mustBe(0)
    }

    @Test
    fun listProperty_shouldRemoveOriginalItemIfNoLongerMatchesCriteria() {
        val repository = InMemoryRepository<EntityForTesting>().cached
        val criteria: RepositoryCriteria<EntityForTesting> = object : RepositoryCriteria<EntityForTesting> {
            override fun invoke(entity: EntityForTesting): Boolean = entity.name.toLowerCase().startsWith("b")
            override fun equals(other: Any?): Boolean = this === other
            override fun hashCode(): Int = 55
        }
        val listProperty = repository.listProperty(criteria)
        listProperty.get().size.mustBe(0)

        val entity1A = repository.saveAndGet(EntityForTesting("A"))
        listProperty.get().size.mustBe(0)

        repository.save(entity1A, EntityForTesting("B"))
        listProperty.get().size.mustBe(1)

        repository.save(entity1A.copy(name = "B3"))
        listProperty.get().size.mustBe(1)

        repository.save(entity1A)
        listProperty.get().size.mustBe(0)
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

    @Test
    fun idListProperty_shouldPopulateWhenDataBecomesVisible() {
        val repository1 = InMemoryRepository<EntityForTesting>()
        val repository2 = InMemoryRepository<EntityForTesting>()
        repository2.save(EntityForTesting("George"))
        val switchableRepository = SwitchableRepository(repository1)
        val idListProperty = switchableRepository.cached.idListProperty()
        idListProperty.get().size.mustBe(0)

        switchableRepository.delegate = repository2
        idListProperty.get().size.mustBe(1)

        repository2.save(EntityForTesting("Howard"))
        idListProperty.get().size.mustBe(2)
    }

    @Test
    fun listProperty_shouldPreserveValueEvenIfDuplicatedValueIsRemoved() {
        val repository1 = InMemoryRepository<EntityForTesting>()
        val cachingRepository = repository1.cached

        val id1 = repository1.save(EntityForTesting("George"))
        val id2 = repository1.save(EntityForTesting("George"))
        val names = cachingRepository.listProperty(RepositoryQuery(EntityName, allItems()))
        names.get().mustBe(listOf("George"))

        repository1.remove(id1)
        names.get().mustBe(listOf("George"))

        repository1.remove(id2)
        names.get().mustBe(emptyList())
    }
}

object EntityName : FieldSelector<EntityForTesting,String> {
    override fun invoke(entity: EntityForTesting): String? = entity.name
    override fun equals(other: Any?): Boolean = this == EntityName
    override fun hashCode(): Int = 444
}

data class EntityForTestingByName(val name: String) : RepositoryCriteria<EntityForTesting> {
    override fun invoke(entity: EntityForTesting): Boolean = entity.name == name
}
