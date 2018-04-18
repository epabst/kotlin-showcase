package client.ext.firebase

import client.component.UndoComponent
import client.util.LocalStorageRepository
import client.util.handleError
import client.util.handlingErrors
import common.util.*
import common.util.ProtectionLevel.*
import firebase.app.App
import firebase.database.DataSnapshot
import net.yested.core.properties.Property
import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.browser.localStorage
import kotlin.browser.window
import kotlin.js.Json
import kotlin.js.json

/**
 * A [Repository] that synchronizes with a [firebase.database.Database].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 1/4/18
 * Time: 11:05 PM
 */
open class FirebaseRepositorySync<T : WithID<T>, in JS>(private val delegate: Repository<T>, val path: String, private val toData: (JS) -> T, val firebaseApp: App) : Repository<T> {
    private val unsyncedLocalStorageKey = "unsynced/firebase/$path"
    private val originalValuesToSync: Map<String,T?>? by lazy {
        localStorage[unsyncedLocalStorageKey]?.let { mapString ->
            try {
//                console.info(unsyncedLocalStorageKey + ": " + mapString)
                val json = JSON.parse<Json>(mapString)
                json.keys.map { it to json[it]?.let {
                    @Suppress("UNCHECKED_CAST")
                    toData(it as JS)
                } }
            } catch (t: Throwable) {
                console.info(unsyncedLocalStorageKey + ": " + mapString)
                console.error(t)
                null
            }
        }?.let { mapOf(*it.toTypedArray()) }
    }

    private val valuesToSync: MutableMap<String,T?> by lazy {
        originalValuesToSync?.toMutableMap() ?: mutableMapOf<String,T?>(*(delegate.list().map { item ->
            item.getID()?._id?.let { it to item }
        }.filterNotNull().toTypedArray()))
    }
    private val collectionRef = firebaseApp.database().ref(path)

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
        syncRemainingToFirebaseAsynchronously()
    }

    private fun syncRemainingToFirebaseAsynchronously() {
        window.requestAnimationFrame {
            val firstOrNull = valuesToSync.entries.firstOrNull()
            if (firstOrNull != null) {
                syncToFirebase(firstOrNull) {
                    markAsSynced(it)
                    syncRemainingToFirebaseAsynchronously()
                }
            }
        }
    }

    private fun syncToFirebase(valueToSync: Map.Entry<String, T?>, onComplete: (ID<T>) -> Unit = {}) {
        val id = ID<T>(valueToSync.key)
        val value = valueToSync.value
        if (value != null) {
            setInFirebase(value.withID(id), onComplete)
        } else {
            removeInFirebase(id, onComplete)
        }
    }

    private fun markAsNotSynced(id: ID<T>, value: T?) {
        valuesToSync.put(id._id, value)
        storeValuesToSync()
    }

    private fun markAsSynced(id: ID<T>) {
        valuesToSync.remove(id._id)
        storeValuesToSync()
    }

    private fun storeValuesToSync() {
        localStorage[unsyncedLocalStorageKey] = JSON.stringify(json(*valuesToSync.map { it.key to it.value }.toTypedArray()))
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

    override fun save(original: T?, replacement: T): ID<T> {
        val originalID = original?.getID()
        val replacementPossiblyWithOriginalId = if (originalID != null) replacement.withID(originalID) else replacement
        val newID = setInFirebase(replacementPossiblyWithOriginalId) { it: ID<T> -> markAsSynced(it) }
        val replacementWithID = replacement.withID(newID)
        // Save it immediately so that it can be immediately found.
        delegate.save(original, replacementWithID)
        // Later, when Firebase notifies of child_added or child_changed, it will not notify listeners again.
        markAsNotSynced(newID, replacementWithID)
        return newID
    }

    private fun setInFirebase(entity: T, onComplete: (ID<T>) -> Unit = {}): ID<T> {
        val givenID = entity.getID()
        val newReference = givenID?.let { collectionRef.child(it.toString()) } ?: collectionRef.push()
        val id = givenID ?: ID(newReference.key!!)
        val jsonValue = JSON.parse<Any>(JSON.stringify(entity.withID(id)))
        newReference.set(jsonValue, onComplete = { error ->
            handlingErrors("firebase set") {
                if (error == null) onComplete(id) else handleError(error)
            }
        })
        return id
    }

    override fun remove(id: ID<T>): Boolean {
        val removed = delegate.remove(id)
        if (removed) {
            markAsNotSynced(id, null)
            // Remove it immediately so that it won't be found anymore.
            // Later, when Firebase notifies of child_removed, it will not notify listeners again.
            removeInFirebase(id) { markAsSynced(id) }
        }
        return removed
    }

    private fun removeInFirebase(id: ID<T>, onComplete: (ID<T>) -> Unit = {}) {
        handlingErrors("firebase remove") {
            collectionRef.child(id.toString()).remove(onComplete = { error ->
                handlingErrors("firebase remove onComplete") {
                    if (error == null) onComplete(id) else handleError(error)
                }
            })
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

val Json.keys: Array<String> get() = js("Object").keys(this)
