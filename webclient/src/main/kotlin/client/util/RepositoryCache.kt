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
    private val listCache = mutableMapOf<RepositoryQuery<T,*>,QueryResult<T,*>>()

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
                listCache.values.forEach { listProperty ->
                    listProperty.onRemoved(item)
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
    internal fun <F> listProperty(query: RepositoryQuery<T,F>): ReadOnlyProperty<List<F>> {
        return (listCache.getOrPut(query) {
            QueryResult(query, repository.list(query.criteria).map { query.selector.invoke(it) }.distinct())
        } as QueryResult<T,F>).listProperty
    }
}

private class QueryResult<T : WithID<T>, F>(private val query: RepositoryQuery<T,F>, initialList: List<F>) {
    private val _listProperty: Property<List<F>> = Property(initialList)

    val listProperty: ReadOnlyProperty<List<F>> get() = _listProperty

    fun onSaved(original: T?, replacementWithID: T) {
        _listProperty.modify { list ->
            val index = if (original != null && query.criteria.invoke(original)) {
                list.indexOf(query.selector.invoke(original))
            } else {
                -1
            }
            when {
                query.criteria.invoke(replacementWithID) -> {
                    val newFieldValue: F? = query.selector.invoke(replacementWithID)
                    when {
                        newFieldValue == null -> list.filterIndexed { i, _ -> i != index }
                        index >= 0 -> list.mapIndexed { i, item -> if (i == index) newFieldValue else item }
                        else -> list.plus(newFieldValue)
                    }
                }
                index >= 0 -> list.filterIndexed { i, _ -> i != index }
                else -> list
            }
        }
    }

    fun onRemoved(item: T) {
        if (query.criteria.invoke(item)) {
            _listProperty.modifyList {
                it.remove(query.selector.invoke(item))
            }
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
        return listProperty<ID<T>>(IdFieldSelector(), criteria)
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
