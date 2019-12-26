package component.firebase

import platform.launchHandlingErrors
import component.UndoComponent
import component.repository.*
import firebase.app.App
import firebase.auth.Auth
import firebase.firestore.DocumentReference
import firebase.firestore.DocumentSnapshot
import firebase.firestore.QueryDocumentSnapshot
import firebase.requireFirestore
import kotlinx.coroutines.await

/**
 * A [Repository] that synchronizes with a [firebase.firestore.FirebaseFirestore].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirestoreRepositorySync<T : WithID<T>, JS>(val pathsSpecifier: PathsSpecifier<T>, val toData: (JS) -> T, val firebaseApp: App) : NormalizingRepository<T>() {
    private val inMemoryRepository = InMemoryRepository<T>(UndoComponent)
    private val database = firebaseApp.firestore()
    private val firstCollection = pathsSpecifier.databasePaths.firstOrNull()?.let { database.collection(it) }
    private val unsubscribeByPath = mutableMapOf<String, ()->Unit>()
    private val callbackToSave: (QueryDocumentSnapshot) -> Unit = { snapshot ->
        launchHandlingErrors("added or modified") {
            UndoComponent.notUndoable {
                inMemoryRepository.save(snapshot.valueWithId())
            }
        }
    }
    private val callbackToRemove: (QueryDocumentSnapshot) -> Unit = { snapshot ->
        launchHandlingErrors("removed") {
            UndoComponent.notUndoable {
                inMemoryRepository.remove(snapshot.valueWithId())
            }
        }
    }


    init {
        requireFirestore?.let {}
        pathsSpecifier.databasePaths.forEach { addSubscribedPath(it) }
    }

    override fun generateID(): ID<T> {
        return ID((firstCollection ?: throw Error("At least one collection must be present")).doc().id)
    }

    private fun DocumentSnapshot.valueWithId(): T {
        return value().withID(ID(id))
    }

    private fun DocumentSnapshot.value(): T {
        return toData(data()!!.unsafeCast<JS>())
    }

    override fun list(): List<T> {
        return inMemoryRepository.list()
    }

    override suspend fun doSaveAndNotify(originalID: ID<T>?, originalWithID: T?, replacementWithID: T) {
        val originalReference = originalWithID?.let { referenceFor(it) }
        val replacementReference = referenceFor(replacementWithID)
        val setPromise = replacementReference?.set(JSON.parse(JSON.stringify(replacementWithID)))
        if (originalReference != null && (replacementReference == null || originalReference.path != replacementReference.path)) {
            originalReference.delete().await()
        }
        setPromise?.await()
        inMemoryRepository.save(originalWithID, replacementWithID)
    }

    override suspend fun remove(item: T): Boolean {
        val removed = inMemoryRepository.remove(item)
        if (removed) {
            referenceFor(item)?.delete()?.await()
        }
        return removed
    }

    internal fun referenceFor(entityWithID: T): DocumentReference? {
        return pathsSpecifier.chooseDatabasePath(entityWithID)?.let { database.collection(it).doc(entityWithID.getID()!!.toString()) }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        inMemoryRepository.addListener(listener)
    }

    override fun removeListener(listener: RepositoryListener<T>) {
        inMemoryRepository.removeListener(listener)
    }

    fun addSubscribedPath(path: String) {
        if (!unsubscribeByPath.contains(path)) {
            println("addSubscribedPath($path)")
            val collectionReference = database.collection(path)
            val unsubscribe = collectionReference.onSnapshot(onNext = { snapshot ->
                snapshot.docChanges().forEach { change ->
                    when (change.type) {
                        "added" -> callbackToSave(change.doc)
                        "modified" -> callbackToSave(change.doc)
                        "removed" -> callbackToRemove(change.doc)
                    }
                }
            })
            unsubscribeByPath.put(path, unsubscribe)
        }
    }

    fun removeSubscribedPath(path: String) {
        val unsubscribe = unsubscribeByPath.remove(path)
        if (unsubscribe != null) {
            println("removeSubscribedPath($path)")
            unsubscribe()
        }
    }

    override val localStorageKeys: Set<String>
        get() = inMemoryRepository.localStorageKeys
}

interface PathsSpecifier<T: WithID<T>> {
    val relativePath: String

    val deviceLocalStoragePath: String?

    val databasePaths: Set<String>

    fun onChangeDatabasePaths(onChangeDatabasePaths: (oldPaths: Set<String>, newPaths: Set<String>) -> Unit)

    fun chooseDatabasePath(entity: T): String?
}

typealias DatabasePathsListener = (oldPaths: Set<String>, newPaths: Set<String>) -> Unit

class GlobalPathsSpecifier<T: WithID<T>>(override val relativePath: String, appNameForFilesystem: String) : PathsSpecifier<T> {
    override val deviceLocalStoragePath: String? = "$appNameForFilesystem/$relativePath"
    override val databasePaths: Set<String> = setOf(relativePath)
    override fun onChangeDatabasePaths(onChangeDatabasePaths: (oldPaths: Set<String>, newPaths: Set<String>) -> Unit) {}
    override suspend fun chooseDatabasePath(entity: T): String? = relativePath
}

class PrivatePathsSpecifier<T: WithID<T>>(override val relativePath: String, firebaseAuth: Auth?) : PathsSpecifier<T> {
    private var _userId: String? = null
    private var _databasePaths: Set<String> = emptySet()
    private val databasePathListeners = mutableListOf<DatabasePathsListener>()

    override val deviceLocalStoragePath: String? = "private/$relativePath"

    override var databasePaths: Set<String>
        get() = _databasePaths
        set(value) {
            val oldPaths = _databasePaths
            _databasePaths = value
            databasePathListeners.forEach { it.invoke(oldPaths, value) }
        }


    init {
        firebaseAuth?.onUserChanged { _, newUser ->
            userId = newUser?.uid
        }
    }

    var userId: String?
        get() = _userId
        set(value) {
            _userId = value
            databasePaths = if (value == null) emptySet() else setOf("private/$value/$relativePath")
        }

    override fun onChangeDatabasePaths(onChangeDatabasePaths: DatabasePathsListener) {
        databasePathListeners.add(onChangeDatabasePaths)
    }

    override fun chooseDatabasePath(entity: T): String? = userId?.let { "private/$it/$relativePath" }
}
