@file:Suppress("unused")

package component.firebase

import component.repository.*
import firebase.FieldPath
import firebase.firestore.*
import kotlinx.coroutines.await
import platform.handlingErrors
import kotlin.js.Date
import kotlin.js.Promise

fun Firestore.generateConsecutiveId(): String = collection("ids").generateConsecutiveId()

fun <T : WithID<T>> CollectionReference<T>.doc(id: ID<T>?): DocumentReference<T> {
    return doc(id?.toString() ?: generateConsecutiveId())
}

fun <T> DocumentReference<T>.update(vararg fieldValues: Pair<FieldPath, Any>): Promise<Unit> {
    return when (fieldValues.size) {
        0 -> error("must provide at least one field to update")
        1 -> update(
            fieldValues[0].first.fieldName, fieldValues[0].second
        )
        2 -> update(
            fieldValues[0].first.fieldName, fieldValues[0].second,
            fieldValues[1].first.fieldName, fieldValues[1].second
        )
        3 -> update(
            fieldValues[0].first.fieldName, fieldValues[0].second,
            fieldValues[1].first.fieldName, fieldValues[1].second,
            fieldValues[2].first.fieldName, fieldValues[2].second
        )
        else -> TODO("Support ${fieldValues.size}")
    }
    // This code should work for all cases but it doesn't compile:
    // TranslationRuntimeException: Unexpected error occurred compiling the following fragment:
    // 'update(fieldValues[0].first, fieldValues[0].second, *remainingFieldPathsAndValues)'
//
//    val remainingFieldPathsAndValues: Array<Any> = fieldValues
//        .drop(1)
//        .flatMap { listOf(it.first.unsafeCast<Any>(), it.second) }
//        .toTypedArray()
//    return update(fieldValues[0].first, fieldValues[0].second, *remainingFieldPathsAndValues)
}

fun CollectionReference<*>.generateConsecutiveId(): String =
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
        handlingErrors("onSnapshot for listener") {
            console.log("got a Firestore snapshot size=${snapshot.size}")
            block.invoke(snapshot.docs.map { it.data() })
        }
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
