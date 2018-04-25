package client.ext.firebase

import client.component.UndoComponent
import client.util.LocalStorageRepository
import client.util.handlingErrors
import common.util.*
import common.util.ProtectionLevel.*
import firebase.app.App
import firebase.database.DataSnapshot
import firebase.database.Reference
import net.yested.core.properties.Property
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.onChange
import kotlin.browser.window

/**
 * A [Repository] that synchronizes with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirebaseRepositorySync<T : WithID<T>, in JS>(private val delegate: Repository<T>,
                                                        initialPaths: List<String>,
                                                        private val pathChooser: (T) -> String,
                                                        private val toData: (JS) -> T,
                                                        val firebaseApp: App) : NormalizingRepository<T>() {
    private val rawDatabase = firebaseApp.database()
    val databaseWithLocalStorage = FirebaseDatabaseWithLocalStorage(rawDatabase)
    private val subscribedPaths = mutableSetOf<String>()
    private val callbackToSave: (DataSnapshot?, String?) -> Int = { snapshot, _ ->
        window.requestAnimationFrame {
            handlingErrors("child_added or child_changed") {
                UndoComponent.notUndoable {
                    delegate.save(snapshot!!.valueWithId())
                }
            }
        }
    }
    private val callbackToRemove: (DataSnapshot?, String?) -> Int = { snapshot, _ ->
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

    init {
        initialPaths.forEach { addSubscribedPath(it) }
    }

    override fun generateID(): ID<T> {
        return ID(rawDatabase.ref().push().key!!)
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
        databaseWithLocalStorage.set(referenceFor(replacementWithID), JSON.parse(JSON.stringify(replacementWithID)))
        delegate.save(originalWithID, replacementWithID)
    }

    override fun remove(item: T): Boolean {
        val removed = delegate.remove(item)
        if (removed) {
            databaseWithLocalStorage.remove(referenceFor(item))
        }
        return removed
    }

    private fun referenceFor(entityWithID: T): Reference {
        return rawDatabase.ref(pathChooser.invoke(entityWithID)).child(entityWithID.getID()!!.toString())
    }

    override fun addListener(listener: RepositoryListener<T>) {
        delegate.addListener(listener)
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        delegate.removeListener(listener)
    }

    fun addSubscribedPath(path: String) {
        if (subscribedPaths.add(path)) {
            val reference = rawDatabase.ref(path)
            reference.on("child_added", callbackToSave)
            reference.on("child_changed", callbackToSave)
            reference.on("child_removed", callbackToRemove)
        }
    }

    fun removeSubscribedPath(path: String) {
        if (subscribedPaths.remove(path)) {
            val reference = rawDatabase.ref(path)
            reference.off("child_added", callbackToSave)
            reference.off("child_changed", callbackToSave)
            reference.off("child_removed", callbackToRemove)
        }
    }

    override val localStorageKeys: Set<String>
        get() = delegate.localStorageKeys
}

fun <T : WithID<T>,JS> FirebaseAndLocalRepository(path: String, localPath: String, toData: (JS) -> T, firebaseApp: App) : FirebaseRepositorySync<T,JS> {
    return FirebaseRepositorySync(LocalStorageRepository(localPath, toData), listOf(path), { path }, toData, firebaseApp)
}

fun <T : ProtectedWithID<T>,JS> PublicWithChangeLogAndPrivateFirebaseRepository(relativePath: String,
                                                                                userId: Property<String?>,
                                                                                privateViaLinkIds: ReadOnlyProperty<List<ID<PrivateViaLinkSpace>>>,
                                                                                toData: (JS) -> T,
                                                                                firebaseApp: App) : Repository<T> {
    val publicRepository = FirebaseAndLocalRepository("public/$relativePath", relativePath, toData, firebaseApp)
    val publicRepositoryWithChangeLog = RepositoryWithFirebaseChangeLog("publicChanges/$relativePath", publicRepository, userId)
    val privateViaLinkRepository = PrivateViaLinkFirebaseRepository(privateViaLinkIds, relativePath, toData, firebaseApp)
    val privateRepository = PrivateFirebaseRepository(userId, relativePath, toData, firebaseApp)
    val deviceRepository = LocalStorageRepository("device/$relativePath", toData)
    return CompositeRepository(mapOf(
            PUBLIC to publicRepositoryWithChangeLog,
            PRIVATE_VIA_LINK to privateViaLinkRepository,
            PRIVATE to privateRepository,
            DEVICE to deviceRepository), UndoComponent, { it.protectedAccess.protectionLevel })
}

fun <T : ProtectedWithID<T>,JS> PrivateViaLinkFirebaseRepository(privateLinkIds: ReadOnlyProperty<List<ID<PrivateViaLinkSpace>>>, relativePath: String, toData: (JS) -> T, firebaseApp: App): FirebaseRepositorySync<T, JS> {
    val localPrivateSharedViaLinkRepository = LocalStorageRepository("privateSharedViaLink/$relativePath", toData)
    val initialPaths = privateLinkIds.get().map { it.getPath(relativePath) }
    val pathChooser: (T) -> String = { it.protectedAccess.privateViaLinkSpaceId!!.getPath(relativePath) }
    val firebaseRepositorySync = FirebaseRepositorySync(localPrivateSharedViaLinkRepository, initialPaths, pathChooser, toData, firebaseApp)
    privateLinkIds.onChange { oldIds, newIds ->
        newIds.minus(oldIds).forEach { firebaseRepositorySync.addSubscribedPath("privateSharedViaLink/${it._id}/$relativePath") }
        oldIds.minus(newIds).forEach { firebaseRepositorySync.removeSubscribedPath("privateSharedViaLink/${it._id}/$relativePath") }
    }
    return firebaseRepositorySync
}

private fun ID<PrivateViaLinkSpace>.getPath(relativePath: String) = "privateSharedViaLink/${_id}/$relativePath"

fun <T : WithID<T>,JS> PrivateFirebaseRepository(userId: ReadOnlyProperty<String?>, relativePath: String, toData: (JS) -> T, firebaseApp: App): Repository<T> {
    val unauthenticatedRepository = LocalStorageRepository("unauthenticatedPrivate/$relativePath", toData)
    val currentUserId = userId.get()
    val initialDelegate = PrivateFirebaseRepository(currentUserId, relativePath, toData, firebaseApp, unauthenticatedRepository)
    val userIdRepository = SwitchableRepository(initialDelegate, UndoComponent)
    userId.onChange { oldUserId, newUserId ->
        val newRepository = PrivateFirebaseRepository(newUserId, relativePath, toData, firebaseApp, unauthenticatedRepository)
        if (oldUserId == null) {
            unauthenticatedRepository.list().forEach {
                newRepository.save(it)
                unauthenticatedRepository.remove(it)
            }
        }
        userIdRepository.delegate = newRepository
    }
    return CompositeRepository(mapOf(true to userIdRepository, false to unauthenticatedRepository), UndoComponent, { userId.get() != null })
}

private fun <JS, T : WithID<T>> PrivateFirebaseRepository(userId: String?, relativePath: String, toData: (JS) -> T, firebaseApp: App, unauthenticatedRepository: LocalStorageRepository<T, JS>): NormalizingRepository<T> {
    return if (userId != null) {
        PrivateFirebaseRepository(userId, relativePath, toData, firebaseApp)
    } else {
        unauthenticatedRepository
    }
}

private fun <JS, T : WithID<T>> PrivateFirebaseRepository(userId: String, relativePath: String, toData: (JS) -> T, firebaseApp: App): FirebaseRepositorySync<T, JS> {
    return FirebaseAndLocalRepository("userPrivate/$userId/$relativePath", "userPrivate/$userId/$relativePath", toData, firebaseApp)
}
