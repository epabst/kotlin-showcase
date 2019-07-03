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
open class FirebaseRepositorySync<T : WithID<T>, JS>(val pathsSpecifier: PathsSpecifier<T>, val toData: (JS) -> T, val firebaseApp: App) : NormalizingRepository<T>() {
    val localStorageRepository = object : LocalStorageRepository<T,JS>(
            pathsSpecifier.relativePath,
            pathsSpecifier.databasePaths.map { paths ->
                pathsSpecifier.deviceLocalStoragePath?.let { paths + it } ?: paths
            },
            toData, { pathsSpecifier.chooseDatabasePath(it) ?: pathsSpecifier.deviceLocalStoragePath ?: error("no localStorageKey provided")
    }) {
        override fun doRemove(item: T): Boolean {
            val result1 = super.doRemove(item)
            val result2 = pathsSpecifier.deviceLocalStoragePath?.let { removeFromLocalStorage(it, item) } ?: false
            return result1 || result2
        }
    }

    private val rawDatabase = firebaseApp.database()
    val databaseWithLocalStorage = firebaseDatabaseWithLocalStorageByApp.getOrPut(firebaseApp) { FirebaseDatabaseWithLocalStorage(rawDatabase) }
    private val subscribedPaths = mutableSetOf<String>()
    private val callbackToSave: (DataSnapshot?, String?) -> Int = { snapshot, _ ->
        window.requestAnimationFrame {
            handlingErrors("child_added or child_changed") {
                UndoComponent.notUndoable {
                    localStorageRepository.save(snapshot!!.valueWithId())
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
                        localStorageRepository.remove(snapshot.valueWithId())
                    }
                }
            }
        }
    }


    init {
        pathsSpecifier.databasePaths.get().forEach { addSubscribedPath(it) }
        pathsSpecifier.databasePaths.onChange { oldPaths, newPaths ->
            newPaths.minus(oldPaths).forEach { addSubscribedPath(it) }
            oldPaths.minus(newPaths).forEach { removeSubscribedPath(it) }
        }
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
        return localStorageRepository.list()
    }

    override fun doSaveAndNotify(originalID: ID<T>?, originalWithID: T?, replacementWithID: T) {
        val originalReference = originalWithID?.let { referenceFor(it) }
        val replacementReference = referenceFor(replacementWithID)
        if (originalReference != null && (replacementReference == null || originalReference.path != replacementReference.path)) {
            databaseWithLocalStorage.remove(originalReference)
        }
        replacementReference?.let { databaseWithLocalStorage.set(it, JSON.parse(JSON.stringify(replacementWithID))) }
        localStorageRepository.save(originalWithID, replacementWithID)
    }

    override fun remove(item: T): Boolean {
        val removed = localStorageRepository.remove(item)
        if (removed) {
            referenceFor(item)?.let { databaseWithLocalStorage.remove(it) }
        }
        return removed
    }

    private fun referenceFor(entityWithID: T): Reference? {
        return pathsSpecifier.chooseDatabasePath(entityWithID)?.let { rawDatabase.ref(it).child(entityWithID.getID()!!.toString()) }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        localStorageRepository.addListener(listener)
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        localStorageRepository.removeListener(listener)
    }

    fun addSubscribedPath(path: String) {
        if (subscribedPaths.add(path)) {
            println("addSubscribedPath($path)")
            val reference = rawDatabase.ref(path)
            reference.on("child_added", callbackToSave)
            reference.on("child_changed", callbackToSave)
            reference.on("child_removed", callbackToRemove)
        }
    }

    fun removeSubscribedPath(path: String) {
        if (subscribedPaths.remove(path)) {
            println("removeSubscribedPath($path)")
            val reference = rawDatabase.ref(path)
            reference.off("child_added", callbackToSave)
            reference.off("child_changed", callbackToSave)
            reference.off("child_removed", callbackToRemove)
        }
    }

    override val localStorageKeys: Set<String>
        get() = localStorageRepository.localStorageKeys

    companion object {
        val firebaseDatabaseWithLocalStorageByApp = mutableMapOf<App,FirebaseDatabaseWithLocalStorage>()
    }
}

fun <T : ProtectedWithID<T>,JS> protectionLevelWithChangesRepository(
        globalPathsSpecifier: PathsSpecifier<T>,
        protectedPathsSpecifier: PathsSpecifier<T>,
        privatePathsSpecifier: PathsSpecifier<T>,
        userId: ReadOnlyProperty<String?>,
        relativePath: String,
        toData: (JS) -> T,
        firebaseApp: App?): Repository<T> {
    return if (firebaseApp != null) {
        val globalRepository = FirebaseRepositorySync(globalPathsSpecifier, toData, firebaseApp)
        protectionLevelRepository(
                globalRepository = RepositoryWithFirebaseChangeLog("globalChanges/$relativePath", globalRepository, userId),
                protectedRepository = FirebaseRepositorySync(protectedPathsSpecifier, toData, firebaseApp),
                privateRepository = FirebaseRepositorySync(privatePathsSpecifier, toData, firebaseApp),
                deviceRepository = LocalStorageRepository(relativePath, setOf("device/$relativePath").toProperty(), toData, { "device/$relativePath" }))
    } else {
        LocalStorageRepository("device/$relativePath", toData)
    }
}

fun <T : ProtectedWithID<T>> protectionLevelRepository(
        globalRepository: Repository<T>,
        protectedRepository: Repository<T>,
        privateRepository: Repository<T>,
        deviceRepository: Repository<T>): CompositeRepository<T, ProtectionLevel> {
    return CompositeRepository(mapOf(
            GLOBAL to globalRepository,
            PROTECTED to protectedRepository,
            PRIVATE to privateRepository,
            DEVICE to deviceRepository), UndoComponent, { it.protectedAccess.protectionLevel })
}

interface PathsSpecifier<T: WithID<T>> {
    val relativePath: String

    val deviceLocalStoragePath: String?

    val databasePaths: ReadOnlyProperty<Set<String>>

    fun chooseDatabasePath(entity: T): String?
}

class GlobalPathsSpecifier<T: WithID<T>>(override val relativePath: String) : PathsSpecifier<T> {
    override val deviceLocalStoragePath: String? = relativePath

    override val databasePaths: ReadOnlyProperty<Set<String>> = setOf("global/$relativePath").toProperty()

    override fun chooseDatabasePath(entity: T): String? = "global/$relativePath"
}

class ProtectedPathsSpecifier<T: ProtectedWithID<T>>(
        override val relativePath: String,
        accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>) : PathsSpecifier<T> {

    override val deviceLocalStoragePath: String? = null

    override val databasePaths: ReadOnlyProperty<Set<String>> = accessSpaceIds.map { it.map { spaceId -> "protected/${spaceId._id}/$relativePath" }.toSet() }

    override fun chooseDatabasePath(entity: T): String? {
        return "protected/${entity.protectedAccess.accessSpaceId!!._id}/$relativePath"
    }
}

class ChildPathsSpecifier<T: ChildWithID<T,P>,P: WithID<P>>(
        val delegate: PathsSpecifier<T>,
        parentIds: ReadOnlyProperty<List<ID<P>>>) : PathsSpecifier<T> {
    override val relativePath: String get() = delegate.relativePath
    override val deviceLocalStoragePath: String? = delegate.deviceLocalStoragePath
    override val databasePaths: ReadOnlyProperty<Set<String>> = delegate.databasePaths.zip(parentIds).map { (paths, parentIds) ->
        paths.flatMap { path -> parentIds.map { "$path/$it" } }.toSet()
    }

    override fun chooseDatabasePath(entity: T): String? = delegate.chooseDatabasePath(entity) + "/" + entity.parentId
}

fun <T: ChildWithID<T,P>,P: WithID<P>> PathsSpecifier<T>.withParentIds(parentIds: ReadOnlyProperty<List<ID<P>>>): ChildPathsSpecifier<T,P> {
    return ChildPathsSpecifier(this, parentIds)
}

class PrivatePathsSpecifier<T: WithID<T>>(override val relativePath: String, val userId: ReadOnlyProperty<String?>): PathsSpecifier<T> {
    override val deviceLocalStoragePath: String? = "private/$relativePath"

    override val databasePaths = userId.map { if (it == null) emptySet() else setOf("private/$it/$relativePath") }

    override fun chooseDatabasePath(entity: T): String? = userId.get()?.let { "private/$it/$relativePath" }
}
