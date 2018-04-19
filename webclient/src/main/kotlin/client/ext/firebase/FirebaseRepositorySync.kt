package client.ext.firebase

import client.component.UndoComponent
import client.util.LocalStorageRepository
import client.util.handlingErrors
import common.util.*
import common.util.ProtectionLevel.*
import firebase.app.App
import firebase.database.DataSnapshot
import net.yested.core.properties.Property
import kotlin.browser.window

/**
 * A [Repository] that synchronizes with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirebaseRepositorySync<T : WithID<T>, in JS>(private val delegate: Repository<T>, val path: String, private val toData: (JS) -> T, val firebaseApp: App) : NormalizingRepository<T>() {
    private val rawDatabase = firebaseApp.database()
    val databaseWithLocalStorage = FirebaseDatabaseWithLocalStorage(rawDatabase)
    private val collectionRef = rawDatabase.ref(path)

    init {
        collectionRef.on("child_added") { snapshot, _ ->
            window.requestAnimationFrame {
                handlingErrors("child_added") {
                    UndoComponent.notUndoable {
                        delegate.save(snapshot!!.valueWithId())
                    }
                }
            }
        }
        collectionRef.on("child_changed") { snapshot, _ ->
            window.requestAnimationFrame {
                handlingErrors("child_changed") {
                    UndoComponent.notUndoable {
                        delegate.save(snapshot!!.valueWithId())
                    }
                }
            }
        }
        collectionRef.on("child_removed") { snapshot, _ ->
            window.requestAnimationFrame {
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
    }

    private fun firebase.database.Reference.on(eventType: String, callback: (a: firebase.database.DataSnapshot?, b: String? /*= null*/) -> Any): (a: firebase.database.DataSnapshot?, b: String? /*= null*/) -> Any {
        return this.on(eventType, callback, null, null)
    }

    override fun generateID(): ID<T> {
        return ID(collectionRef.push().key!!)
    }

    private fun DataSnapshot.valueWithId(): T {
        return value().withID(id!!)
    }

    private fun DataSnapshot.value(): T {
        @Suppress("UNCHECKED_CAST")
        return toData(this.`val`() as JS)
    }

    private val DataSnapshot.id: ID<T>? get() = key?.let { ID(it) }

    override fun list(): List<T> {
        return delegate.list()
    }

    override fun doSaveAndNotify(originalID: ID<T>?, originalWithID: T?, replacementWithID: T) {
        val reference = collectionRef.child(replacementWithID.getID()!!.toString())
        databaseWithLocalStorage.set(reference, JSON.parse(JSON.stringify(replacementWithID)))
        delegate.save(originalWithID, replacementWithID)
    }

    override fun remove(id: ID<T>): Boolean {
        val removed = delegate.remove(id)
        if (removed) {
            databaseWithLocalStorage.remove(collectionRef.child(id.toString()))
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

fun <T : WithID<T>,JS> FirebaseAndLocalRepository(path: String, localPath: String, toData: (JS) -> T, firebaseApp: App) : FirebaseRepositorySync<T,JS> {
    return FirebaseRepositorySync(LocalStorageRepository(localPath, toData), path, toData, firebaseApp)
}

fun <T : WithID<T>,JS> PublicWithChangeLogAndPrivateFirebaseRepository(relativePath: String,
                                                                       userId: Property<String?>,
                                                                       toData: (JS) -> T,
                                                                       firebaseApp: App,
                                                                       categorizer: (T) -> ProtectionLevel) : Repository<T> {
    val publicRepository = FirebaseAndLocalRepository("public/$relativePath", relativePath, toData, firebaseApp)
    val publicRepositoryWithChangeLog = RepositoryWithFirebaseChangeLog("publicChanges/$relativePath", publicRepository, userId)
    val privateRepository = PrivateFirebaseRepository(userId, relativePath, toData, firebaseApp)
    val deviceRepository = LocalStorageRepository("device/$relativePath", toData)
    return CompositeRepository(mapOf(PUBLIC to publicRepositoryWithChangeLog, PRIVATE to privateRepository, DEVICE to deviceRepository), UndoComponent, categorizer)
}

fun <T : ProtectedWithID<T>,JS> PublicWithChangeLogAndPrivateFirebaseRepository(relativePath: String,
                                                                                userId: Property<String?>,
                                                                                toData: (JS) -> T,
                                                                                firebaseApp: App) : Repository<T> {
    return PublicWithChangeLogAndPrivateFirebaseRepository(relativePath, userId, toData, firebaseApp, { it.protectionLevel })
}

fun <T : WithID<T>,JS> PrivateFirebaseRepository(userId: Property<String?>, relativePath: String, toData: (JS) -> T, firebaseApp: App): SwitchableRepository<T> {
    val emptyRepository = EmptyRepository<T>()
    val privateRepository = SwitchableRepository(emptyRepository, UndoComponent)
    userId.onNext {
        privateRepository.delegate = it?.let { FirebaseAndLocalRepository("userPrivate/$it/$relativePath", "userPrivate/$it/$relativePath", toData, firebaseApp) } ?: emptyRepository
    }
    return privateRepository
}
