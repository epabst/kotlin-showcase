package component.firebase

import component.repository.*
import firebase.firestore.*
import kotlinx.coroutines.await

fun <T : WithID<T>> CollectionReference<DocumentData>.doc(id: ID<T>?): DocumentReference<DocumentData> {
    val documentPath = id?.toString()
    return if (documentPath == null) doc() else doc(documentPath)
}

suspend fun <E : WithID<E>> DocumentReference<DocumentData>.set(entity: E) {
    set(JSON.parse(JSON.stringify(entity.withID(ID(id))))).await()
}

interface JsonParser<E : WithID<E>> {
    fun fromJson(json: DocumentData): E
}

fun <E : WithID<E>> DocumentSnapshot<DocumentData>.parse(parser: JsonParser<E>): E {
    return parser.fromJson(data()!!).withID(ID(id))
}

fun <E : WithID<E>> QuerySnapshot<DocumentData>.parse(parser: JsonParser<E>): List<E> {
    return this.docs.map { snapshot -> parser.fromJson(snapshot.data()).withID(ID(snapshot.id)) }
}

fun <E : WithID<E>> Query<DocumentData>.addListener(parser: JsonParser<E>, block: (List<E>) -> Unit): Closeable {
    val release = onSnapshot(onNext = { snapshot ->
        console.log("got a Firestore snapshot size=${snapshot.size}")
        block.invoke(snapshot.docs.map { parser.fromJson(it.data()) })
    })
    return object : Closeable {
        override fun close() = release.invoke()
    }
}
