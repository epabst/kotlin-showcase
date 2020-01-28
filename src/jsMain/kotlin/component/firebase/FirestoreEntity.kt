@file:Suppress("unused")

package component.firebase

import component.repository.*
import firebase.Unsubscribe
import firebase.firestore.*
import firebase.generateConsecutiveId
import kotlinx.coroutines.await
import platform.handleError
import platform.handlingErrors
import platform.inContext
import kotlin.js.Promise

fun <T : WithID<T>> CollectionReference<T>.doc(id: ID<T>?): DocumentReference<T> {
    return doc(id?.toString() ?: generateConsecutiveId())
}

suspend fun <E : WithID<E>> DocumentReference<E>.set(entity: E) {
    set(JSON.parse(JSON.stringify(entity.withID(ID(id))))).await()
}

suspend fun <E : WithID<E>> CollectionReference<E>.save(entity: E) {
    doc(entity.getID()).set(entity).await()
}

fun <E : WithID<E>> QuerySnapshot<E>.toList(): List<E> {
    return docs.map { snapshot -> snapshot.data() }
}

fun <E : WithID<E>> Query<E>.onSnapshot(
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

fun <E : WithID<E>> DocumentReference<E>.onSnapshot(
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

interface FirestoreEntityConverter<E : WithID<E>> : FirestoreDataConverter<E> {

    fun toNormal(json: DocumentData): E

    override fun toFirestore(modelObject: E): DocumentData {
        return JSON.parse(JSON.stringify(modelObject))
    }

    override fun fromFirestore(snapshot: QueryDocumentSnapshot<DocumentData>, options: SnapshotOptions): E {
        return toNormal(snapshot.data()).withID(ID(snapshot.id))
    }

}
