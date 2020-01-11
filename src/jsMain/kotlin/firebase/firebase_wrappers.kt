/**
 * These are only here to force requiring "firebase/<something>" since there are no top-level functions or objects.
 */
package firebase

import firebase.firestore.CollectionReference
import firebase.firestore.DocumentReference
import firebase.firestore.Firestore
import kotlin.js.Date
import kotlin.js.Promise

@JsModule("firebase/app") @JsNonModule
external val requireApp: Nothing? = definedExternally

@JsModule("firebase/auth") @JsNonModule
external val requireAuth: Nothing? = definedExternally

@JsModule("firebase/database") @JsNonModule
external val requireDatabase: Nothing? = definedExternally

@JsModule("firebase/firestore") @JsNonModule
external val requireFirestore: Nothing? = definedExternally

@JsModule("firebase/storage") @JsNonModule
external val requireStorage: Nothing? = definedExternally

/** This is an alternative to [firebase.firestore.FieldPath] to work around the error "FieldPath is not a constructor" */
class FieldPath(val fieldName: String)

fun Firestore.generateConsecutiveId(): String = collection("ids").generateConsecutiveId()

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
