@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "NOTHING_TO_INLINE", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE", "DEPRECATION", "unused", "PropertyName")
@file:[JsModule("firebase/firestore") JsNonModule]
package firebase.firestore

import kotlin.js.*
import org.khronos.webgl.*

external interface DocumentData {
    @nativeGetter
    operator fun get(field: String): Any?
    @nativeSetter
    operator fun set(field: String, value: Any)
}
external interface UpdateData {
    @nativeGetter
    operator fun get(fieldPath: String): Any?
    @nativeSetter
    operator fun set(fieldPath: String, value: Any)
}
external interface Settings {
    var host: String? get() = definedExternally; set(value) = definedExternally
    var ssl: Boolean? get() = definedExternally; set(value) = definedExternally
    var timestampsInSnapshots: Boolean? get() = definedExternally; set(value) = definedExternally
}
external fun setLogLevel(logLevel: String /* "debug" | "error" | "silent" */): Unit = definedExternally
open external class FirebaseFirestore {
    open fun settings(settings: Settings): Unit = definedExternally
    open fun enablePersistence(): Promise<Unit> = definedExternally
    open fun collection(collectionPath: String): CollectionReference = definedExternally
    open fun doc(documentPath: String): DocumentReference = definedExternally
    open fun <T> runTransaction(updateFunction: (transaction: Transaction) -> Promise<T>): Promise<T> = definedExternally
    open fun batch(): WriteBatch = definedExternally
    open var app: Any = definedExternally
    open fun enableNetwork(): Promise<Unit> = definedExternally
    open fun disableNetwork(): Promise<Unit> = definedExternally
}
open external class GeoPoint(latitude: Number, longitude: Number) {
    open var latitude: Number = definedExternally
    open var longitude: Number = definedExternally
    open fun isEqual(other: GeoPoint): Boolean = definedExternally
}
open external class Timestamp(seconds: Number, nanoseconds: Number) {
    open var seconds: Number = definedExternally
    open var nanoseconds: Number = definedExternally
    open fun toDate(): Date = definedExternally
    open fun toMillis(): Number = definedExternally
    open fun isEqual(other: Timestamp): Boolean = definedExternally
    companion object {
        fun now(): Timestamp = definedExternally
        fun fromDate(date: Date): Timestamp = definedExternally
        fun fromMillis(milliseconds: Number): Timestamp = definedExternally
    }
}
open external class Blob {
    open fun toBase64(): String = definedExternally
    open fun toUint8Array(): Uint8Array = definedExternally
    open fun isEqual(other: Blob): Boolean = definedExternally
    companion object {
        fun fromBase64String(base64: String): Blob = definedExternally
        fun fromUint8Array(array: Uint8Array): Blob = definedExternally
    }
}
open external class Transaction {
    open fun get(documentRef: DocumentReference): Promise<DocumentSnapshot> = definedExternally
    open fun set(documentRef: DocumentReference, data: DocumentData, options: SetOptions? = definedExternally /* null */): Transaction = definedExternally
    open fun update(documentRef: DocumentReference, data: UpdateData): Transaction = definedExternally
    open fun update(documentRef: DocumentReference, field: String, value: Any, vararg moreFieldsAndValues: Any): Transaction = definedExternally
    open fun update(documentRef: DocumentReference, field: FieldPath, value: Any, vararg moreFieldsAndValues: Any): Transaction = definedExternally
    open fun delete(documentRef: DocumentReference): Transaction = definedExternally
}
open external class WriteBatch {
    open fun set(documentRef: DocumentReference, data: DocumentData, options: SetOptions? = definedExternally /* null */): WriteBatch = definedExternally
    open fun update(documentRef: DocumentReference, data: UpdateData): WriteBatch = definedExternally
    open fun update(documentRef: DocumentReference, field: String, value: Any, vararg moreFieldsAndValues: Any): WriteBatch = definedExternally
    open fun update(documentRef: DocumentReference, field: FieldPath, value: Any, vararg moreFieldsAndValues: Any): WriteBatch = definedExternally
    open fun delete(documentRef: DocumentReference): WriteBatch = definedExternally
    open fun commit(): Promise<Unit> = definedExternally
}
external interface SnapshotListenOptions {
    var includeMetadataChanges: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface SetOptions {
    var merge: Boolean? get() = definedExternally; set(value) = definedExternally
    var mergeFields: Array<dynamic /* String | FieldPath */>? get() = definedExternally; set(value) = definedExternally
}
external interface GetOptions {
    var source: String? /* "default" | "server" | "cache" */ get() = definedExternally; set(value) = definedExternally
}
external interface OnSnapshotObserverWithFirestoreError {
    var next: ((snapshot: DocumentSnapshot) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var error: ((error: FirestoreError) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var complete: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
}
external interface OnSnapshotObserverWithError {
    var next: ((snapshot: DocumentSnapshot) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var error: ((error: Error) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var complete: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
}
open external class DocumentReference {
    open var id: String = definedExternally
    open var firestore: FirebaseFirestore = definedExternally
    open var parent: CollectionReference = definedExternally
    open var path: String = definedExternally
    open fun collection(collectionPath: String): CollectionReference = definedExternally
    open fun isEqual(other: DocumentReference): Boolean = definedExternally
    open fun set(data: DocumentData, options: SetOptions? = definedExternally /* null */): Promise<Unit> = definedExternally
    open fun update(data: UpdateData): Promise<Unit> = definedExternally
    open fun update(field: String, value: Any, vararg moreFieldsAndValues: Any): Promise<Unit> = definedExternally
    open fun update(field: FieldPath, value: Any, vararg moreFieldsAndValues: Any): Promise<Unit> = definedExternally
    open fun delete(): Promise<Unit> = definedExternally
    open fun get(options: GetOptions? = definedExternally /* null */): Promise<DocumentSnapshot> = definedExternally
    open fun onSnapshot(observer: OnSnapshotObserverWithFirestoreError): () -> Unit = definedExternally
    open fun onSnapshot(options: SnapshotListenOptions, observer: OnSnapshotObserverWithError): () -> Unit = definedExternally
    open fun onSnapshot(onNext: (snapshot: DocumentSnapshot) -> Unit, onError: ((error: Error) -> Unit)? = definedExternally /* null */, onCompletion: (() -> Unit)? = definedExternally /* null */): () -> Unit = definedExternally
    open fun onSnapshot(options: SnapshotListenOptions, onNext: (snapshot: DocumentSnapshot) -> Unit, onError: ((error: Error) -> Unit)? = definedExternally /* null */, onCompletion: (() -> Unit)? = definedExternally /* null */): () -> Unit = definedExternally
}
external interface SnapshotOptions {
    var serverTimestamps: String? /* "estimate" | "previous" | "none" */ get() = definedExternally; set(value) = definedExternally
}
external interface SnapshotMetadata {
    var hasPendingWrites: Boolean
    var fromCache: Boolean
    fun isEqual(other: SnapshotMetadata): Boolean
}
open external class DocumentSnapshot {
    open var exists: Boolean = definedExternally
    open var ref: DocumentReference = definedExternally
    open var id: String = definedExternally
    open var metadata: SnapshotMetadata = definedExternally
    open fun data(options: SnapshotOptions? = definedExternally /* null */): DocumentData? = definedExternally
    open fun get(fieldPath: String, options: SnapshotOptions? = definedExternally /* null */): Any = definedExternally
    open fun get(fieldPath: FieldPath, options: SnapshotOptions? = definedExternally /* null */): Any = definedExternally
    open fun isEqual(other: DocumentSnapshot): Boolean = definedExternally
}
open external class QueryDocumentSnapshot : DocumentSnapshot {
    override fun data(options: SnapshotOptions?): DocumentData = definedExternally
}
external interface QuerySnapshotObserver {
    var next: ((snapshot: QuerySnapshot) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var error: ((error: Error) -> Unit)? get() = definedExternally; set(value) = definedExternally
    var complete: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
}
open external class Query {
    open var firestore: FirebaseFirestore = definedExternally
    open fun where(fieldPath: String, opStr: String /* "<" | "<=" | "==" | ">=" | ">" */, value: Any): Query = definedExternally
    open fun where(fieldPath: FieldPath, opStr: String /* "<" | "<=" | "==" | ">=" | ">" */, value: Any): Query = definedExternally
    open fun orderBy(fieldPath: String, directionStr: String? /* "desc" | "asc" */ = definedExternally /* null */): Query = definedExternally
    open fun orderBy(fieldPath: FieldPath, directionStr: String? /* "desc" | "asc" */ = definedExternally /* null */): Query = definedExternally
    open fun limit(limit: Number): Query = definedExternally
    open fun startAt(snapshot: DocumentSnapshot): Query = definedExternally
    open fun startAt(vararg fieldValues: Any): Query = definedExternally
    open fun startAfter(snapshot: DocumentSnapshot): Query = definedExternally
    open fun startAfter(vararg fieldValues: Any): Query = definedExternally
    open fun endBefore(snapshot: DocumentSnapshot): Query = definedExternally
    open fun endBefore(vararg fieldValues: Any): Query = definedExternally
    open fun endAt(snapshot: DocumentSnapshot): Query = definedExternally
    open fun endAt(vararg fieldValues: Any): Query = definedExternally
    open fun isEqual(other: Query): Boolean = definedExternally
    open fun get(options: GetOptions? = definedExternally /* null */): Promise<QuerySnapshot> = definedExternally
    open fun onSnapshot(observer: QuerySnapshotObserver): () -> Unit = definedExternally
    open fun onSnapshot(options: SnapshotListenOptions, observer: QuerySnapshotObserver): () -> Unit = definedExternally
    open fun onSnapshot(onNext: (snapshot: QuerySnapshot) -> Unit, onError: ((error: Error) -> Unit)? = definedExternally /* null */, onCompletion: (() -> Unit)? = definedExternally /* null */): () -> Unit = definedExternally
    open fun onSnapshot(options: SnapshotListenOptions, onNext: (snapshot: QuerySnapshot) -> Unit, onError: ((error: Error) -> Unit)? = definedExternally /* null */, onCompletion: (() -> Unit)? = definedExternally /* null */): () -> Unit = definedExternally
}
open external class QuerySnapshot {
    open var query: Query = definedExternally
    open var metadata: SnapshotMetadata = definedExternally
    open var docs: Array<QueryDocumentSnapshot> = definedExternally
    open var size: Number = definedExternally
    open var empty: Boolean = definedExternally
    open fun docChanges(options: SnapshotListenOptions? = definedExternally /* null */): Array<DocumentChange> = definedExternally
    open fun forEach(callback: (result: QueryDocumentSnapshot) -> Unit, thisArg: Any? = definedExternally /* null */): Unit = definedExternally
    open fun isEqual(other: QuerySnapshot): Boolean = definedExternally
}
external interface DocumentChange {
    var type: String /* "added" | "removed" | "modified" */
    var doc: QueryDocumentSnapshot
    var oldIndex: Number
    var newIndex: Number
}
open external class CollectionReference : Query {
    open var id: String = definedExternally
    open var parent: DocumentReference? = definedExternally
    open var path: String = definedExternally
    open fun doc(documentPath: String? = definedExternally /* null */): DocumentReference = definedExternally
    open fun add(data: Any): Promise<DocumentReference> = definedExternally
    open fun add(data: DocumentData): Promise<DocumentReference> = definedExternally
    open fun isEqual(other: CollectionReference): Boolean = definedExternally
}
open external class FieldValue {
    open fun isEqual(other: FieldValue): Boolean = definedExternally
    companion object {
        fun serverTimestamp(): FieldValue = definedExternally
        fun delete(): FieldValue = definedExternally
    }
}
open external class FieldPath(vararg fieldNames: String) {
    open fun isEqual(other: FieldPath): Boolean = definedExternally
    companion object {
        fun documentId(): FieldPath = definedExternally
    }
}
external interface FirestoreError {
    var code: String /* "cancelled" | "unknown" | "invalid-argument" | "deadline-exceeded" | "not-found" | "already-exists" | "permission-denied" | "resource-exhausted" | "failed-precondition" | "aborted" | "out-of-range" | "unimplemented" | "internal" | "unavailable" | "data-loss" | "unauthenticated" */
    var message: String
    var name: String
    var stack: String? get() = definedExternally; set(value) = definedExternally
}
// This is only to force requiring "firebase/firestore" since there are no top-level functions or objects.
external val requireFirestore: Nothing? = definedExternally
