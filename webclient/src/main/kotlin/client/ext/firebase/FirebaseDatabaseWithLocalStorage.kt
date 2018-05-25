package client.ext.firebase

import client.appNameForFilesystem
import client.util.MapInLocalStorage
import client.util.handleError
import client.util.handlingErrors
import firebase.database.Database
import firebase.database.Reference
import kotlin.browser.window
import kotlin.js.Json

/**
 * A [firebase.database.Database] backed by browser localStorage.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 4/19/18
 * Time: 5:05 AM
 */
open class FirebaseDatabaseWithLocalStorage(private val firebaseDatabase: Database) {
    init {
        syncRemainingToFirebaseAsynchronously()
    }

    fun push(parentReference: Reference, value: Any, onComplete: ((Error?) -> Any) = { if (it != null) handleError(it) }): Reference {
        val reference = parentReference.push()
        set(reference, value, onComplete)
        return reference
    }

    fun set(reference: Reference, value: Any, onComplete: ((Error?) -> Any) = { if (it != null) handleError(it) }) {
        markAsNotSynced(reference, value)
        setWithoutMarkingAsNotSynced(reference, value, onComplete)
    }

    fun remove(reference: Reference, onComplete: ((Error?) -> Any) = { if (it != null) handleError(it) }) {
        markAsNotSynced(reference, null)
        removeWithoutMarkingAsNotSynced(reference, onComplete)
    }

    private fun markAsNotSynced(ref: Reference, value: Any?) {
        valuesToSync.put(ref.path, value)
    }

    private fun markAsSynced(reference: Reference) {
        valuesToSync.remove(reference.path)
    }

    private fun setWithoutMarkingAsNotSynced(reference: Reference, entity: Any, onComplete: (Error?) -> Any) {
        val jsonValue = JSON.parse<Any>(JSON.stringify(entity))
        reference.set(jsonValue, onComplete = { error ->
            handlingErrors("firebase set") {
                if (error == null) {
                    markAsSynced(reference)
                }
                onComplete.invoke(error)
            }
        })
    }

    private fun removeWithoutMarkingAsNotSynced(reference: Reference, onComplete: (a: Error?) -> Any) {
        reference.remove(onComplete = { error ->
            handlingErrors("firebase remove onComplete") {
                if (error == null) {
                    markAsSynced(reference)
                }
                onComplete.invoke(error)
            }
        })
    }

    private fun syncRemainingToFirebaseAsynchronously() {
        window.requestAnimationFrame {
            handlingErrors("syncRemainingToFirebaseAsynchronously") {
                val firstOrNull = valuesToSync.entries.firstOrNull()
                if (firstOrNull != null) {
                    val value = firstOrNull.value
                    val reference = firebaseDatabase.ref(firstOrNull.key)
                    if (value != null) {
                        setWithoutMarkingAsNotSynced(reference, value) {
                            if (it != null) handleError(it)
                            syncRemainingToFirebaseAsynchronously()
                        }
                    } else {
                        removeWithoutMarkingAsNotSynced(reference) {
                            if (it != null) handleError(it)
                            syncRemainingToFirebaseAsynchronously()
                        }
                    }
                }
            }
        }
    }
}

private val valuesToSync by lazy { MapInLocalStorage<Json, Any?>("unsynced/firebase/$appNameForFilesystem", { it }) }

val Reference.path: String get() = toString().substring(root.toString().length)
