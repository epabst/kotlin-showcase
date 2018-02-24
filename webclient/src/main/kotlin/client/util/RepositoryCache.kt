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
    private val listCache = mutableMapOf<RepositoryQuery<T,*>,Property<List<*>>>()

    init {
        repository.addListener(object : RepositoryListener<T> {
            override fun onSaved(original: T?, replacementWithID: T) {
                val id = replacementWithID.getID()!!
                cache[id]?.set(replacementWithID)
                listCache.entries.forEach { (query, listProperty) ->
                    listProperty.modifyList { list ->
                        val index = if (original != null && query.criteria.invoke(original)) {
                            list.indexOf(query.selector.invoke(original))
                        } else {
                            -1
                        }
                        if (query.criteria.invoke(replacementWithID)) {
                            val newFieldValue = query.selector.invoke(replacementWithID)
                            if (index >= 0) {
                                list.set(index, newFieldValue)
                            } else {
                                list.add(newFieldValue)
                            }
                        } else if (index >= 0) {
                            list.removeAt(index)
                        }
                    }
                }
            }

            override fun onRemoved(item: T) {
                item.getID()?.let { cache[it]?.set(null) }
                listCache.entries.forEach { (query, listProperty) ->
                    if (query.criteria.invoke(item)) {
                        listProperty.modifyList {
                            it.remove(query.selector.invoke(item))
                        }
                    }
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
        return listCache.getOrPut(query) {
            Property(repository.list(query.criteria).map { query.selector.invoke(it) }.distinct())
        } as ReadOnlyProperty<List<F>>
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
    return listProperty<T,ID<T>>(IdFieldSelector(), criteria)
}

internal data class RepositoryQuery<T : WithID<T>,F>(val selector: FieldSelector<T, F>, val criteria: RepositoryCriteria<T>)

fun <T : WithID<T>,F> Repository<T>.list(selector: FieldSelector<T, F>, criteria: RepositoryCriteria<T> = allItems()): List<F> {
    return listProperty(selector, criteria).get()
}

fun <T : WithID<T>> Repository<T>.listProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<T>> {
    return repositoryCache(this).listProperty(RepositoryQuery(SelfSelector(), criteria))
}

fun <T : WithID<T>,F> Repository<T>.listProperty(selector: FieldSelector<T, F>, criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<List<F>> {
    return repositoryCache(this).listProperty(RepositoryQuery(selector, criteria))
}

fun <T : WithID<T>> Repository<T>.findFirstOrNullProperty(criteria: RepositoryCriteria<T> = allItems()): ReadOnlyProperty<T?> {
    return idListProperty(criteria).flatMapOrNull { it.firstOrNull()?.let { findProperty(it) } }
}
