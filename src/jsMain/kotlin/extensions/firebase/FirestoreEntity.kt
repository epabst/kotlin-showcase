@file:Suppress("unused")

package extensions.firebase

import firebase.Unsubscribe
import firebase.firestore.*
import firebase.generateConsecutiveId
import kotlinx.coroutines.await
import util.handleError
import util.handlingErrors
import util.inContext
import util.Entity
import util.ID
import kotlin.js.Promise

fun <T : Entity<T>> CollectionReference<T>.doc(id: ID<T>?): DocumentReference<T> {
    return doc(id?.toString() ?: generateConsecutiveId())
}

suspend fun <E : Entity<E>> DocumentReference<E>.set(entity: E) {
    set(JSON.parse(JSON.stringify(entity.withID(ID(id))))).await()
}

suspend fun <E : Entity<E>> CollectionReference<E>.save(entity: E) {
    doc(entity.id).set(entity).await()
}

fun <E : Entity<E>> QuerySnapshot<E>.toList(): List<E> {
    return docs.map { snapshot -> snapshot.data() }
}

fun <E : Entity<E>> Query<E>.onSnapshot(
    onError: (FirestoreError) -> Unit = { handleFirestoreError(it) },
    block: (List<E>) -> Unit
): Unsubscribe {
    return onSnapshot(onNext = { snapshot ->
        handlingErrors("onSnapshot for list listener") {
            console.log("got a Firestore snapshot size=${snapshot.size}")
            block.invoke(snapshot.docs.map { it.data() })
        }
    }, onError = { error ->
        inContext("onSnapshot error for list listener") {
            onError(error.unsafeCast<FirestoreError>())
        }
    })
}

fun <E : Entity<E>> DocumentReference<E>.onSnapshot(
    onError: (FirestoreError) -> Unit = { handleFirestoreError(it) },
    block: (E?) -> Unit
): Unsubscribe {
    return onSnapshot(onNext = { snapshot ->
        handlingErrors("onSnapshot for $path") {
            block.invoke(snapshot.data())
        }
    }, onError = { error ->
        inContext("onSnapshot error for $path") {
            onError(error.unsafeCast<FirestoreError>())
        }
    })
}

fun <T> Promise<QuerySnapshot<T>>.then(
    onError: (FirestoreError) -> Unit = { handleFirestoreError(it) },
    onFulfilled: (QuerySnapshot<T>) -> Unit
) {
    then(
        onFulfilled = onFulfilled,
        onRejected = { inContext("QuerySnapshot.then") { onError(it.unsafeCast<FirestoreError>()) } }
    )
}

fun handleFirestoreError(error: FirestoreError) {
    handleError(error.message, error)
}

interface FirestoreEntityConverter<E : Entity<E>> : FirestoreDataConverter<E> {

    fun toNormal(json: DocumentData): E

    override fun toFirestore(modelObject: E): DocumentData {
        return JSON.parse(JSON.stringify(modelObject))
    }

    override fun fromFirestore(snapshot: QueryDocumentSnapshot<DocumentData>, options: SnapshotOptions): E {
        return toNormal(snapshot.data()).withID(ID(snapshot.id))
    }

}
