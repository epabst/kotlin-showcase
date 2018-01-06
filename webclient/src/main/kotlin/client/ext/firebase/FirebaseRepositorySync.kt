package client.ext.firebase

import client.component.UndoComponent
import client.util.handlingErrors
import common.util.*
import firebase.app.App
import firebase.database.DataSnapshot

/**
 * A [Repository] that synchronizes with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirebaseRepositorySync<T : WithID<T>,JS>(private val delegate: Repository<T>, val path: String, private val toData: (JS) -> T, firebaseApp: App) : Repository<T> {
    private val collectionRef = firebaseApp.database().ref(path)

    init {
        collectionRef.on("child_added") { snapshot, _ ->
            handlingErrors("child_added") {
                val entity = snapshot!!.valueWithId()
                console.info("child_added: $entity")
                UndoComponent.notUndoable {
                    delegate.save(entity)
                }
            }
        }
        collectionRef.on("child_changed") { snapshot, _ ->
            handlingErrors("child_changed") {
                val entity = snapshot!!.valueWithId()
                console.info("child_changed: $entity")
                UndoComponent.notUndoable {
                    delegate.save(entity)
                }
            }
        }
        collectionRef.on("child_removed") { snapshot, _ ->
            handlingErrors("child_removed") {
                val id = snapshot?.id
                val entity = snapshot!!.valueWithId()
                console.info("child_removed: $entity")
                if (id != null) {
                    UndoComponent.notUndoable {
                        delegate.remove(id)
                    }
                }
            }
        }
    }

    private fun firebase.database.Reference.on(eventType: String, callback: (a: firebase.database.DataSnapshot?, b: String? /*= null*/) -> Any): (a: firebase.database.DataSnapshot?, b: String? /*= null*/) -> Any {
        return this.on(eventType, callback, null, null)
    }

    override fun generateID(): ID<T> {
        return delegate.generateID()
    }

    private fun DataSnapshot.valueWithId(): T {
        return value().withID(id!!)
    }

    private fun DataSnapshot.value(): T {
        @Suppress("UNCHECKED_CAST")
        return toData(this.`val`() as JS)
    }

    private val DataSnapshot.id: ID<T>? get() = key?.toLong()?.let { ID<T>(it) }

    override fun list(): List<T> {
        return delegate.list()
    }

    override fun save(original: T?, replacement: T): ID<T> {
        // Save it immediately so that it can be immediately found.
        // Later, when Firebase notifies of child_added or child_changed, it will not notify listeners again.
        val newID = delegate.save(original, replacement)
        handlingErrors("firebase set") {
            collectionRef.child(newID.toString()).set(JSON.parse(JSON.stringify(replacement.withID(newID))))
        }
        return newID
    }

    override fun remove(item: T) {
        val id = item.getID()
        if (id != null) {
            delegate.remove(item)
            // Remove it immediately so that it won't be found anymore.
            // Later, when Firebase notifies of child_removed, it will not notify listeners again.
            handlingErrors("firebase remove") {
                collectionRef.child(id.toString()).remove()
            }
        }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        delegate.addListener(listener)
    }
}