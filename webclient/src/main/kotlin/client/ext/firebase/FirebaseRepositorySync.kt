package client.ext.firebase

import client.component.UndoComponent
import client.util.LocalStorageRepository
import client.util.handlingErrors
import client.util.keys
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
    constructor(pathsSpecifier: PathsSpecifier<T>, toData: (JS) -> T, firebaseApp: App) : this(
            LocalStorageRepository(pathsSpecifier.localStoragePath, toData),
            pathsSpecifier.databasePaths.get(),
            { pathsSpecifier.chooseDatabasePath(it) },
            toData,
            firebaseApp) {
        pathsSpecifier.databasePaths.onChange { oldPaths, newPaths ->
            newPaths.minus(oldPaths).forEach { addSubscribedPath(it) }
            oldPaths.minus(newPaths).forEach { removeSubscribedPath(it) }
        }
    }

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
        get() = delegate.localStorageKeys
}

fun <T : ProtectedWithID<T>,JS> protectionLevelWithChangesRepository(
        globalPathsSpecifier: PathsSpecifier<T>,
        protectedPathsSpecifier: PathsSpecifier<T>,
        privatePathsSpecifier: PathsSpecifier<T>,
        userId: Property<String?>,
        relativePath: String,
        toData: (JS) -> T,
        firebaseApp: App?): Repository<T> {
    if (firebaseApp != null) {
        val globalRepository = FirebaseRepositorySync(globalPathsSpecifier, toData, firebaseApp)
        return protectionLevelRepository(
                globalRepository = RepositoryWithFirebaseChangeLog("globalChanges/$relativePath", globalRepository, userId),
                protectedRepository = FirebaseRepositorySync(protectedPathsSpecifier, toData, firebaseApp),
                privateRepository = FirebaseRepositorySync(privatePathsSpecifier, toData, firebaseApp),
                deviceRepository = LocalStorageRepository("device/$relativePath", toData))
    } else {
        return LocalStorageRepository("device/$relativePath", toData)
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
    val localStoragePath: String

    val databasePaths: ReadOnlyProperty<List<String>>

    fun chooseDatabasePath(entity: T): String?
}

class GlobalPathsSpecifier<T: WithID<T>>(private val relativePath: String) : PathsSpecifier<T> {
    override val localStoragePath: String = relativePath

    override val databasePaths: ReadOnlyProperty<List<String>> = listOf("global/$relativePath").toProperty()

    override fun chooseDatabasePath(entity: T): String? = "global/$relativePath"
}

class ProtectedPathsSpecifier<T: ProtectedWithID<T>>(
        private val relativePath: String,
        accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>) : PathsSpecifier<T> {

    override val localStoragePath: String = "protected/$relativePath"

    override val databasePaths: ReadOnlyProperty<List<String>> = accessSpaceIds.map { it.map { spaceId -> "protected/${spaceId._id}/$relativePath" } }

    override fun chooseDatabasePath(entity: T): String? {
        return "protected/${entity.protectedAccess.accessSpaceId!!._id}/$relativePath"
    }
}

class ChildPathsSpecifier<T: ProtectedChildWithID<T,P>,P: WithID<P>>(
        val delegate: PathsSpecifier<T>,
        parentIds: ReadOnlyProperty<List<ID<P>>>) : PathsSpecifier<T> {

    override val localStoragePath: String = delegate.localStoragePath
    override val databasePaths: ReadOnlyProperty<List<String>> = delegate.databasePaths.zip(parentIds).map { (paths, parentIds) ->
        paths.flatMap { path -> parentIds.map { "$path/$it" } }
    }

    override fun chooseDatabasePath(entity: T): String? = delegate.chooseDatabasePath(entity) + "/" + entity.parentId
}

fun <T: ProtectedChildWithID<T,P>,P: WithID<P>> PathsSpecifier<T>.withParentIds(parentIds: ReadOnlyProperty<List<ID<P>>>): ChildPathsSpecifier<T,P> {
    return ChildPathsSpecifier(this, parentIds)
}

class PrivatePathsSpecifier<T: WithID<T>>(val relativePath: String, val userId: ReadOnlyProperty<String?>): PathsSpecifier<T> {
    override val localStoragePath: String = "private/$relativePath"

    override val databasePaths = userId.map { if (it == null) emptyList() else listOf("private/$it/$relativePath") }

    override fun chooseDatabasePath(entity: T): String? = userId.get()?.let { "private/$it/$relativePath" }
}
