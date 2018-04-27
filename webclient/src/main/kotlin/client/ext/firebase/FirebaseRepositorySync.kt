package client.ext.firebase

import client.component.UndoComponent
import client.util.LocalStorageRepository
import client.util.handlingErrors
import common.util.*
import common.util.ProtectionLevel.*
import firebase.app.App
import firebase.database.DataSnapshot
import firebase.database.Reference
import net.yested.core.properties.*
import kotlin.browser.window

/**
 * A [Repository] that synchronizes with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirebaseRepositorySync<T : WithID<T>, in JS>(private val delegate: Repository<T>,
                                                        initialPaths: List<String>,
                                                        private val pathChooser: (T) -> String?,
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
        referenceFor(replacementWithID)?.let { databaseWithLocalStorage.set(it, JSON.parse(JSON.stringify(replacementWithID))) }
        delegate.save(originalWithID, replacementWithID)
    }

    override fun remove(item: T): Boolean {
        val removed = delegate.remove(item)
        if (removed) {
            referenceFor(item)?.let { databaseWithLocalStorage.remove(it) }
        }
        return removed
    }

    private fun referenceFor(entityWithID: T): Reference? {
        return pathChooser.invoke(entityWithID)?.let { rawDatabase.ref(it).child(entityWithID.getID()!!.toString()) }
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

fun <T : ProtectedWithID<T>,JS> protectionLevelWithGlobalChangeLogRepository(relativePath: String,
                                                                             userId: Property<String?>,
                                                                             accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>,
                                                                             toData: (JS) -> T,
                                                                             firebaseApp: App) : Repository<T> {
    val path = "global/$relativePath"
    val globalRepository = firebaseAndLocalRepository(listOf(path).toProperty(), { path }, toData, firebaseApp, relativePath)
    val globalRepositoryWithChangeLog = RepositoryWithFirebaseChangeLog("globalChanges/$relativePath", globalRepository, userId)
    return protectionLevelRepository(relativePath, userId, accessSpaceIds, globalRepositoryWithChangeLog, toData, firebaseApp)
}

fun <T : ProtectedWithID<T>,JS> protectionLevelRepository(relativePath: String,
                                                          userId: Property<String?>,
                                                          accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>,
                                                          toData: (JS) -> T,
                                                          firebaseApp: App) : Repository<T> {
    val path = "global/$relativePath"
    val globalRepository = firebaseAndLocalRepository(listOf(path).toProperty(), { path }, toData, firebaseApp, relativePath)
    return protectionLevelRepository(relativePath, userId, accessSpaceIds, globalRepository, toData, firebaseApp)
}

private fun <JS, T : ProtectedWithID<T>> protectionLevelRepository(relativePath: String,
                                                                   userId: Property<String?>,
                                                                   accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>,
                                                                   globalRepository: Repository<T>,
                                                                   toData: (JS) -> T,
                                                                   firebaseApp: App): CompositeRepository<T, ProtectionLevel> {
    val protectedRepository = protectedFirebaseRepository(relativePath, accessSpaceIds, toData, firebaseApp)
    val privateRepository = privateRepository(relativePath, userId, toData, firebaseApp)
    val deviceRepository = LocalStorageRepository("device/$relativePath", toData)
    return CompositeRepository(mapOf(
            GLOBAL to globalRepository,
            PROTECTED to protectedRepository,
            PRIVATE to privateRepository,
            DEVICE to deviceRepository), UndoComponent, { it.protectedAccess.protectionLevel })
}

private fun <JS,T: ProtectedChildWithID<T,P>,P: WithID<P>> protectionLevelChildRepository(
        relativePath: String,
        userId: Property<String?>,
        accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>,
        parentIds: ReadOnlyProperty<List<ID<P>>>,
        globalRepository: Repository<T>,
        toData: (JS) -> T,
        firebaseApp: App): CompositeRepository<T, ProtectionLevel> {
    val protectedRepository = protectedChildFirebaseRepository(relativePath, accessSpaceIds, parentIds, toData, firebaseApp)
    val privateRepository = privateRepository(relativePath, userId, toData, firebaseApp)
    val deviceRepository = LocalStorageRepository("device/$relativePath", toData)
    return CompositeRepository(mapOf(
            GLOBAL to globalRepository,
            PROTECTED to protectedRepository,
            PRIVATE to privateRepository,
            DEVICE to deviceRepository), UndoComponent, { it.protectedAccess.protectionLevel })
}

fun <T : ProtectedWithID<T>,JS> protectedFirebaseRepository(relativePath: String,
                                                            accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>,
                                                            toData: (JS) -> T,
                                                            firebaseApp: App): FirebaseRepositorySync<T, JS> {
    val paths = accessSpaceIds.map { it.map { spaceId -> "protected/${spaceId._id}/$relativePath" } }
    val pathChooser: (T) -> String = { "protected/${it.protectedAccess.accessSpaceId!!._id}/$relativePath" }
    return firebaseAndLocalRepository(paths, pathChooser, toData, firebaseApp, "protected/$relativePath")
}

fun <T: ProtectedChildWithID<T,P>,P: WithID<P>,JS> protectedChildFirebaseRepository(relativePath: String,
                                                                                    accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>,
                                                                                    parentIds: ReadOnlyProperty<List<ID<P>>>,
                                                                                    toData: (JS) -> T,
                                                                                    firebaseApp: App): FirebaseRepositorySync<T, JS> {
    val paths = accessSpaceIds.zip(parentIds).map { (accessSpaceIds, parentIds) ->
        accessSpaceIds.flatMap { spaceId -> parentIds.map { "protected/$spaceId/$relativePath/$it" } }
    }
    val pathChooser: (T) -> String = { "protected/${it.protectedAccess.accessSpaceId!!}/$relativePath/${it.parentId}" }
    return firebaseAndLocalRepository(paths, pathChooser, toData, firebaseApp, "protected/$relativePath")
}

private fun <T : WithID<T>,JS> firebaseAndLocalRepository(paths: ReadOnlyProperty<List<String>>, pathChooser: (T) -> String?, toData: (JS) -> T, firebaseApp: App, localStoragePath: String): FirebaseRepositorySync<T, JS> {
    val pathsSpecifier: PathsSpecifier<T> = object : PathsSpecifier<T> {
        override val localStoragePath: String = localStoragePath

        override val databasePaths: ReadOnlyProperty<List<String>> = paths

        override fun chooseDatabasePath(entity: T): String? = pathChooser.invoke(entity)
    }
    return firebaseAndLocalRepository(pathsSpecifier, toData, firebaseApp)
}

private fun <T : WithID<T>,JS> firebaseAndLocalRepository(pathsSpecifier: PathsSpecifier<T>, toData: (JS) -> T, firebaseApp: App): FirebaseRepositorySync<T, JS> {
    val localProtectedRepository = LocalStorageRepository(pathsSpecifier.localStoragePath, toData)
    val initialPaths = pathsSpecifier.databasePaths.get()
    val firebaseRepositorySync = FirebaseRepositorySync(localProtectedRepository, initialPaths, { pathsSpecifier.chooseDatabasePath(it) }, toData, firebaseApp)
    pathsSpecifier.databasePaths.onChange { oldPaths, newPaths ->
        newPaths.minus(oldPaths).forEach { firebaseRepositorySync.addSubscribedPath(it) }
        oldPaths.minus(newPaths).forEach { firebaseRepositorySync.removeSubscribedPath(it) }
    }
    return firebaseRepositorySync
}

fun <T : WithID<T>,JS> privateRepository(relativePath: String, userId: ReadOnlyProperty<String?>, toData: (JS) -> T, firebaseApp: App): Repository<T> {
    val paths = userId.map { if (it == null) emptyList() else listOf("private/$it/$relativePath") }
    return firebaseAndLocalRepository(paths, { userId.get()?.let { "private/$it/$relativePath" } }, toData, firebaseApp, "private/$relativePath")
}

interface PathsSpecifier<T: WithID<T>> {
    val localStoragePath: String

    val databasePaths: ReadOnlyProperty<List<String>>

    fun chooseDatabasePath(entity: T): String?
}

class PrivatePathsSpecifier<T: WithID<T>>(override val localStoragePath: String, val relativePath: String, val userId: ReadOnlyProperty<String?>): PathsSpecifier<T> {
    override val databasePaths = userId.map { if (it == null) emptyList() else listOf("private/$it/$relativePath") }

    override fun chooseDatabasePath(entity: T): String? = userId.get()?.let { "private/$it/$relativePath" }
}
