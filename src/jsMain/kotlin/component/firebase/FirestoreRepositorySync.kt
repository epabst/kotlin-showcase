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
import kotlinx.coroutines.withTimeout

/**
 * A [Repository] that synchronizes with a [firebase.firestore.FirebaseFirestore].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirestoreRepositorySync<T : WithID<T>>(val pathsSpecifier: PathsSpecifier<T>,
                                                  val parser: JsonParser<T>,
                                                  firebaseApp: App) : NormalizingRepository<T>() {

    private val inMemoryRepository = InMemoryRepository<T>(UndoComponent)
    private val database = firebaseApp.firestore()
    private val firstCollection = pathsSpecifier.databasePaths.firstOrNull()?.let { database.collection(it) }
    private val unsubscribeByPath = mutableMapOf<String, ()->Unit>()
    private val callbackToSave: (QueryDocumentSnapshot) -> Unit = { snapshot ->
        launchHandlingErrors("added or modified") {
            UndoComponent.notUndoable {
                inMemoryRepository.save(snapshot.parse(parser))
            }
        }
    }
    private val callbackToRemove: (QueryDocumentSnapshot) -> Unit = { snapshot ->
        launchHandlingErrors("removed") {
            UndoComponent.notUndoable {
                inMemoryRepository.remove(snapshot.parse(parser))
            }
        }
    }


    init {
        requireFirestore?.let {}
        pathsSpecifier.databasePaths.forEach { addSubscribedPath(it) }
    }

    override suspend fun generateID(): ID<T> {
        return ID((firstCollection ?: throw Error("At least one collection must be present")).doc().id)
    }

    override suspend fun generateID(entityWithoutId: T): ID<T> {
        val databasePath = pathsSpecifier.chooseDatabasePath(entityWithoutId)
        return if (databasePath != null) ID(database.collection(databasePath).doc().id) else generateID()
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

    internal suspend fun referenceFor(entityWithID: T): DocumentReference? {
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

fun <E : WithID<E>> DocumentSnapshot.parse(parser: JsonParser<E>): E {
    return parser.fromJson(data()!!).withID(ID(id))
}

interface PathsSpecifier<T: WithID<T>> {
    val relativePath: String

    val deviceLocalStoragePath: String?

    val databasePaths: Set<String>

    fun onChangeDatabasePaths(onChangeDatabasePaths: (oldPaths: Set<String>, newPaths: Set<String>) -> Unit)

    suspend fun chooseDatabasePath(entity: T): String?
}

typealias DatabasePathsListener = (oldPaths: Set<String>, newPaths: Set<String>) -> Unit

class GlobalPathsSpecifier<T: WithID<T>>(override val relativePath: String, appNameForFilesystem: String) : PathsSpecifier<T> {
    override val deviceLocalStoragePath: String? = "$appNameForFilesystem/$relativePath"
    override val databasePaths: Set<String> = setOf(relativePath)
    override fun onChangeDatabasePaths(onChangeDatabasePaths: (oldPaths: Set<String>, newPaths: Set<String>) -> Unit) {}
    override suspend fun chooseDatabasePath(entity: T): String? = relativePath
}

class PrivatePathsSpecifier<T: WithID<T>>(override val relativePath: String, private val firebaseAuth: Auth?) : PathsSpecifier<T> {
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

    override suspend fun chooseDatabasePath(entity: T): String? {
        if (firebaseAuth == null) {
            return null
        }
        val userId = withTimeout(7000) { firebaseAuth.waitForUser() }.uid
        return "private/$userId/$relativePath"
    }
}
