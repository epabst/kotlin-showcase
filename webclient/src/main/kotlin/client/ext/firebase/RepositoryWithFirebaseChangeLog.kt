package client.ext.firebase

import client.util.handlingErrors
import common.util.*
import net.yested.core.properties.Property
import net.yested.ext.moment.Moment

/**
 * A [Repository] that stores change-log items with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/8/18
 * Time: 11:05 PM
 */
open class RepositoryWithFirebaseChangeLog<T : WithID<T>,JS>(path: String, private val delegate: FirebaseRepositorySync<T, JS>, val userId: Property<String?>) : Repository<T> {
    private val firebaseDatabaseWithLocalStorage = delegate.databaseWithLocalStorage
    private val changeLogRef = delegate.firebaseApp.database().ref(path)

    override fun generateID(): ID<T> {
        return delegate.generateID()
    }

    override fun list(): List<T> {
        return delegate.list()
    }

    override fun save(original: T?, replacement: T): ID<T> {
        val newID = delegate.save(original, replacement)
        handlingErrors("changelog: firebase push save") {
            val changeLogEntry = ChangeLogEntry(replacement, userId.get() ?: throw Error("Not authenticated yet"))
            firebaseDatabaseWithLocalStorage.push(changeLogRef.child(newID.toString()), changeLogEntry)
        }
        return newID
    }

    override fun remove(item: T): Boolean {
        val removed = delegate.remove(item)
        if (removed) {
            val id = item.getID()
            if (id != null) {
                handlingErrors("changelog: firebase push removal") {
                    val changeLogEntry = ChangeLogEntry(null, userId.get() ?: throw Error("Not authenticated yet"))
                    firebaseDatabaseWithLocalStorage.push(changeLogRef.child(id.toString()), changeLogEntry)
                }
            }
        }
        return removed
    }

    override fun addListener(listener: RepositoryListener<T>) {
        delegate.addListener(listener)
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        delegate.removeListener(listener)
    }

    override val localStorageKeys: Set<String>
        get() = delegate.localStorageKeys
}

data class ChangeLogEntry<T : WithID<T>>(val data: T?,
                                         val userId: String,
                                         val changeDateMillis: Double = Moment.now().millisecondsSinceUnixEpoch.toDouble(),
                                         val id: ID<ChangeLogEntry<T>>? = null) : WithID<ChangeLogEntry<T>> {
    override fun getID(): ID<ChangeLogEntry<T>>? = id

    override fun withID(id: ID<ChangeLogEntry<T>>): ChangeLogEntry<T> = copy(id = id)
}
