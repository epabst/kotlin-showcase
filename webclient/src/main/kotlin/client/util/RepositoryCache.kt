package client.util

import common.util.ID
import common.util.Repository
import common.util.RepositoryListener
import common.util.WithID
import net.yested.core.properties.Property
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.toProperty

/**
 * A cache for a [Repository] that provides a ReadOnlyProperty for each accessed ID.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/8/17
 * Time: 7:08 AM
 */
class RepositoryCache<T : WithID<T>>(val repository: Repository<T>) {
    private val cache = mutableMapOf<ID<T>, Property<T>?>()

    init {
        repository.addListener(object : RepositoryListener<T> {
            override fun onSaved(original: T?, replacementWithID: T) {
                cache.get(replacementWithID.getID()!!)?.set(replacementWithID)
            }

            override fun onRemoved(item: T) {
                item.getID()?.let { cache.remove(it) }
            }
        })
    }

    fun find(id: ID<T>): ReadOnlyProperty<T>? {
        return cache.getOrPut(id) {
            repository.find(id)?.toProperty()
        }
    }
}

private val repositoryCaches = mutableMapOf<Repository<*>, RepositoryCache<*>>()

fun <T : WithID<T>> repositoryCache(repository: Repository<T>): RepositoryCache<T> {
    @Suppress("UNCHECKED_CAST")
    return repositoryCaches.getOrPut(repository) { RepositoryCache(repository) } as RepositoryCache<T>
}

fun <T : WithID<T>> Repository<T>.findProperty(id: ID<T>): ReadOnlyProperty<T>? {
    return repositoryCache(this).find(id)
}
