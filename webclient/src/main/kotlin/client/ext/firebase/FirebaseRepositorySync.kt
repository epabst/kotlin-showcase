package client.ext.firebase

import client.component.UndoComponent
import client.ext.firebase.ProtectionLevel.PRIVATE
import client.ext.firebase.ProtectionLevel.PUBLIC
import client.util.LocalStorageRepository
import client.util.handlingErrors
import common.util.*
import firebase.app.App
import firebase.database.DataSnapshot
import net.yested.core.properties.Property

/**
 * A [Repository] that synchronizes with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirebaseRepositorySync<T : WithID<T>, in JS>(private val delegate: Repository<T>, val path: String, private val toData: (JS) -> T, val firebaseApp: App) : Repository<T> {
    private val collectionRef = firebaseApp.database().ref(path)

    init {
        collectionRef.on("child_added") { snapshot, _ ->
            handlingErrors("child_added") {
                UndoComponent.notUndoable {
                    delegate.save(snapshot!!.valueWithId())
                }
            }
        }
        collectionRef.on("child_changed") { snapshot, _ ->
            handlingErrors("child_changed") {
                UndoComponent.notUndoable {
                    delegate.save(snapshot!!.valueWithId())
                }
            }
        }
        collectionRef.on("child_removed") { snapshot, _ ->
            handlingErrors("child_removed") {
                val id = snapshot?.id

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

    private val DataSnapshot.id: ID<T>? get() = key?.toLong()?.let { ID(it) }

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

    override fun remove(id: ID<T>) {
        delegate.remove(id)
        // Remove it immediately so that it won't be found anymore.
        // Later, when Firebase notifies of child_removed, it will not notify listeners again.
        handlingErrors("firebase remove") {
            collectionRef.child(id.toString()).remove()
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

fun <T : WithID<T>,JS> FirebaseAndLocalRepository(path: String, localPath: String, toData: (JS) -> T, firebaseApp: App) : FirebaseRepositorySync<T,JS> {
    return FirebaseRepositorySync(LocalStorageRepository(localPath, toData), path, toData, firebaseApp)
}

enum class ProtectionLevel {
    PRIVATE, PUBLIC, OWNED
}

fun <T : WithID<T>,JS> PublicWithChangeLogAndPrivateFirebaseRepository(relativePath: String,
                                                                       userId: Property<String?>,
                                                                       toData: (JS) -> T,
                                                                       firebaseApp: App,
                                                                       categorizer: (T) -> ProtectionLevel) : Repository<T> {
    val privateRepository = PrivateFirebaseRepository(userId, relativePath, toData, firebaseApp)
    val publicRepository = FirebaseAndLocalRepository("public/$relativePath", relativePath, toData, firebaseApp)
    val publicRepositoryWithChangeLog = RepositoryWithFirebaseChangeLog("publicChanges/$relativePath", publicRepository)
    return CompositeRepository(mapOf(PRIVATE to privateRepository, PUBLIC to publicRepositoryWithChangeLog), UndoComponent, categorizer)
}

fun <T : WithID<T>,JS> PrivateFirebaseRepository(userId: Property<String?>, relativePath: String, toData: (JS) -> T, firebaseApp: App): SwitchableRepository<T> {
    val emptyRepository = EmptyRepository<T>()
    val privateRepository = SwitchableRepository(emptyRepository, UndoComponent)
    userId.onNext {
        privateRepository.delegate = it?.let { FirebaseAndLocalRepository("userPrivate/$it/$relativePath", "userPrivate/$it/$relativePath", toData, firebaseApp) } ?: emptyRepository
    }
    return privateRepository
}
