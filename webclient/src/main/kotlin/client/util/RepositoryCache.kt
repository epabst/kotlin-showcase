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

private val repositoryCaches = mutableMapOf<Repository<*>, RepositoryCache<*>>()

fun <T : WithID<T>> repositoryCache(repository: Repository<T>): RepositoryCache<T> {
    @Suppress("UNCHECKED_CAST")
    return repositoryCaches.getOrPut(repository) { RepositoryCache(repository) } as RepositoryCache<T>
}

fun <T : WithID<T>> Repository<T>.findProperty(id: ID<T>): ReadOnlyProperty<T?> {
    return repositoryCache(this).find(id)
}

fun <T : WithID<T>> Repository<T>.idListProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<ID<T>>> {
    return listProperty(IdFieldSelector(), criteria)
}

fun <T : WithID<T>,F> Repository<T>.list(query: RepositoryQuery<T, F>): List<F> {
    return list(query.selector, query.criteria)
}

fun <T : WithID<T>,F> Repository<T>.list(selector: FieldSelector<T, F>, criteria: RepositoryCriteria<T> = allItems()): List<F> {
    return listProperty(selector, criteria).get()
}

fun <T : WithID<T>> Repository<T>.listProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<T>> {
    return repositoryCache(this).listProperty(RepositoryQuery(SelfSelector(), criteria))
}

fun <T : WithID<T>,F> Repository<T>.listProperty(selector: FieldSelector<T, F>, criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<F>> {
    return listProperty(RepositoryQuery(selector, criteria))
}

fun <T : WithID<T>,F> Repository<T>.listProperty(repositoryQuery: RepositoryQuery<T, F>): ReadOnlyProperty<List<F>> {
    return repositoryCache(this).listProperty(repositoryQuery)
}

fun <T : WithID<T>> Repository<T>.findFirstOrNullProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<T?> {
    return idListProperty(criteria).flatMapOrNull { it.firstOrNull()?.let { findProperty(it) } }
}
