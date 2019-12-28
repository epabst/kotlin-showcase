package component.firebase

import component.repository.*
import firebase.firestore.*
import kotlinx.coroutines.await

fun <T : WithID<T>> CollectionReference.doc(id: ID<T>?): DocumentReference {
    val documentPath = id?.toString()
    return if (documentPath == null) doc() else doc(documentPath)
}

suspend fun <E : WithID<E>> DocumentReference.set(entity: E) {
    set(JSON.parse(JSON.stringify(entity.withID(ID(id))))).await()
}

interface JsonParser<E : WithID<E>> {
    fun fromJson(json: DocumentData): E
}

fun <E : WithID<E>> DocumentSnapshot.parse(parser: JsonParser<E>): E {
    return parser.fromJson(data()!!).withID(ID(id))
}

fun <E : WithID<E>> Query.addListener(parser: JsonParser<E>, block: (List<E>) -> Unit): Closeable {
    val release = onSnapshot(onNext = { snapshot ->
        console.log("got a Firestore snapshot size=${snapshot.size}")
        block.invoke(snapshot.docs.map { parser.fromJson(it.data()) })
    })
    return object : Closeable {
        override fun close() = release.invoke()
    }
}
