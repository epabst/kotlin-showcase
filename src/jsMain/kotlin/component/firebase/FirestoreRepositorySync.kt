package component.firebase

import component.repository.*
import firebase.firestore.*
import kotlinx.coroutines.await
import kotlin.js.Date

fun Firestore.generateConsecutiveId(): String = collection("ids").generateConsecutiveId()

fun <T : WithID<T>> CollectionReference<T>.doc(id: ID<T>?): DocumentReference<T> {
    return doc(id?.toString() ?: generateConsecutiveId())
}

private fun CollectionReference<*>.generateConsecutiveId() =
        Date.now().toLong().toString(36) + doc().id.takeLast(3)

suspend fun <E : WithID<E>> DocumentReference<E>.set(entity: E) {
    set(JSON.parse(JSON.stringify(entity.withID(ID(id))))).await()
}

suspend fun <E : WithID<E>> CollectionReference<E>.save(entity: E) {
    doc(entity.getID()).set(entity).await()
}

fun <E : WithID<E>> QuerySnapshot<E>.toList(): List<E> {
    return docs.map { snapshot -> snapshot.data() }
}

fun <E : WithID<E>> Query<E>.addListener(block: (List<E>) -> Unit): Closeable {
    val release = onSnapshot(onNext = { snapshot ->
        console.log("got a Firestore snapshot size=${snapshot.size}")
        block.invoke(snapshot.docs.map { it.data() })
    })
    return object : Closeable {
        override fun close() = release.invoke()
    }
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
