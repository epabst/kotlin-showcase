package client.ext.firebase

import client.util.handlingErrors
import common.util.*
import net.yested.ext.moment.Moment

/**
 * A [Repository] that stores change-log items with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/8/18
 * Time: 11:05 PM
 */
open class RepositoryWithFirebaseChangeLog<T : WithID<T>,JS>(path: String, private val delegate: FirebaseRepositorySync<T,JS>) : Repository<T> {
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
            val changeLogEntry = ChangeLogEntry(replacement, "me")
            changeLogRef.child(newID.toString()).push(changeLogEntry)
        }
        return newID
    }

    override fun remove(item: T) {
        val id = item.getID()
        if (id != null) {
            delegate.remove(item)
            handlingErrors("changelog: firebase push removal") {
                val changeLogEntry = ChangeLogEntry(null, "me")
                changeLogRef.child(id.toString()).push(changeLogEntry)
            }
        }
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
                                         val changeDateMillis: Int = Moment.now().millisecondsSinceUnixEpoch.toInt(),
                                         val id: ID<ChangeLogEntry<T>>? = null) : WithID<ChangeLogEntry<T>> {
    override fun getID(): ID<ChangeLogEntry<T>>? = id

    override fun withID(id: ID<ChangeLogEntry<T>>): ChangeLogEntry<T> = copy(id = id)
}
