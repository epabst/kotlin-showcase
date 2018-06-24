package client.util

import common.util.*
import net.yested.core.properties.*

/**
 * A cache for a [Repository] that provides a ReadOnlyProperty for each accessed ID.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/8/17
 * Time: 7:08 AM
 */
class RepositoryCache<T : WithID<T>>(val repository: Repository<T>) {
    private val cache = mutableMapOf<ID<T>, Property<T?>>()
    private val listCache = mutableMapOf<RepositoryCriteria<T>,CriteriaCache<T>>()

    init {
        repository.addListener(object : RepositoryListener<T> {
            override fun onSaved(original: T?, replacementWithID: T) {
                val id = replacementWithID.getID()!!
                cache[id]?.set(replacementWithID)
                listCache.values.forEach { queryResult ->
                    queryResult.onSaved(original, replacementWithID)
                }
            }

            override fun onRemoved(item: T) {
                item.getID()?.let { cache[it]?.set(null) }
                listCache.values.forEach { queryResult ->
                    queryResult.onRemoved(item)
                }
            }

            override fun onVisibilityChanged(item: T, visible: Boolean) {
                if (visible) {
                    onSaved(null, item)
                } else {
                    onRemoved(item)
                }
            }
        })
    }

    fun find(id: ID<T>): ReadOnlyProperty<T?> {
        return cache.getOrPut(id) {
            repository.find(id).toProperty()
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <F : Any> listProperty(query: RepositoryQuery<T,F>): ReadOnlyProperty<List<F>> {
        return listCache.getOrPut(query.criteria) {
            CriteriaCache(query.criteria, repository.list(query.criteria))
        }.listProperty(query.selector)
    }
}

private class CriteriaCache<T : WithID<T>>(private val criteria: RepositoryCriteria<T>, initialList: List<T>) {
    private val listProperty: Property<List<T>> = Property(initialList)
    private val listPropertyBySelector = mutableMapOf<FieldSelector<T,*>,ReadOnlyProperty<List<*>>>()

    fun <F : Any> listProperty(selector: FieldSelector<T,F>): ReadOnlyProperty<List<F>> {
        @Suppress("UNCHECKED_CAST")
        return listPropertyBySelector.getOrPut(selector) {
            listProperty.map { it.mapNotNull { selector.invoke(it) }.distinct() }
        } as ReadOnlyProperty<List<F>>
    }

    fun onSaved(original: T?, replacementWithID: T) {
        if (criteria.invoke(replacementWithID)) {
            listProperty.modify { list ->
                if (original != null && criteria.invoke(original)) {
                    list.map { if (it == original) replacementWithID else it }
                } else {
                    list + replacementWithID
                }
            }
        } else if (original != null && criteria.invoke(original)) {
            listProperty.modify { it.filter { it != original } }
        }
    }

    fun onRemoved(item: T) {
        if (criteria.invoke(item)) {
            listProperty.modify { it.filter { it != item } }
        }
    }
}

class CachingRepository<T : WithID<T>>(private val repository: Repository<T>) : Repository<T> {
    private val cache = RepositoryCache(repository)

    override fun list(): List<T> {
        return listProperty().get()
    }

    override fun find(id: ID<T>): T? {
        return findProperty(id).get()
    }

    fun findProperty(id: ID<T>): ReadOnlyProperty<T?> {
        return cache.find(id)
    }

    override fun <F : Any> list(query: RepositoryQuery<T, F>): List<F> {
        return listProperty(query).get()
    }

    fun listProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<T>> {
        return listProperty(RepositoryQuery(SelfSelector(), criteria))
    }

    fun <F : Any> listProperty(selector: FieldSelector<T, F>, criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<F>> {
        return listProperty(RepositoryQuery(selector, criteria))
    }

    fun <F : Any> listProperty(repositoryQuery: RepositoryQuery<T, F>): ReadOnlyProperty<List<F>> {
        return cache.listProperty(repositoryQuery)
    }

    fun idListProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<ID<T>>> {
        return listProperty(IdFieldSelector(), criteria)
    }

    fun findFirstOrNullProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<T?> {
        return idListProperty(criteria).flatMapOrNull { it.firstOrNull()?.let { findProperty(it) } }
    }

    override fun save(original: T?, replacement: T): ID<T> = repository.save(original, replacement)

    override fun remove(id: ID<T>): Boolean = repository.remove(id)

    override fun remove(item: T): Boolean = repository.remove(item)

    override fun generateID(): ID<T> = repository.generateID()

    override fun addListener(listener: RepositoryListener<T>) {
        repository.addListener(listener)
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        repository.removeListener(listener)
    }

    override val localStorageKeys: Set<String> get() = repository.localStorageKeys
}

val <T : WithID<T>> Repository<T>.cached: CachingRepository<T> get() = CachingRepository(this)
