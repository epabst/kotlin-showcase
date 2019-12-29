@file:[JsModule("firebase/storage") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused", "DEPRECATION")
package firebase.storage

import firebase.Unsubscribe
import firebase.app.App
import firebase.app.Observer
import firebase.firestore.Blob
import kotlin.js.*
import org.khronos.webgl.*

external interface FullMetadata : UploadMetadata {
    var bucket: String
    var downloadURLs: Array<String>
    var fullPath: String
    var generation: String
    var metageneration: String
    var name: String
    var size: Number
    var timeCreated: String
    var updated: String
}
external interface Reference {
    var bucket: String
    fun child(path: String): Reference
    fun delete(): Promise<Any>
    var fullPath: String
    fun getDownloadURL(): Promise<Any>
    fun getMetadata(): Promise<Any>
    var name: String
    var parent: Reference?
    fun put(data: Blob, metadata: UploadMetadata? = definedExternally /* null */): UploadTask
    fun put(data: Uint8Array, metadata: UploadMetadata? = definedExternally /* null */): UploadTask
    fun put(data: ArrayBuffer, metadata: UploadMetadata? = definedExternally /* null */): UploadTask
    fun putString(data: String, format: StringFormat? = definedExternally /* null */, metadata: UploadMetadata? = definedExternally /* null */): UploadTask
    var root: Reference
    var storage: Storage
    override fun toString(): String
    fun updateMetadata(metadata: SettableMetadata): Promise<Any>
    fun listAll(): Promise<ListResult>
    fun list(options: ListOptions? = definedExternally /* null */): Promise<ListResult>
}
external interface ListResult {
    var prefixes: Array<Reference>
    var items: Array<Reference>
    var nextPageToken: String?
}
external interface ListOptions {
    var maxResults: Number? get() = definedExternally; set(value) = definedExternally
    var pageToken: String? get() = definedExternally; set(value) = definedExternally
}
external interface `T$26` {
    @nativeGetter
    operator fun get(key: String): String?
    @nativeSetter
    operator fun set(key: String, value: String)
}
external interface SettableMetadata {
    var cacheControl: String? get() = definedExternally; set(value) = definedExternally
    var contentDisposition: String? get() = definedExternally; set(value) = definedExternally
    var contentEncoding: String? get() = definedExternally; set(value) = definedExternally
    var contentLanguage: String? get() = definedExternally; set(value) = definedExternally
    var contentType: String? get() = definedExternally; set(value) = definedExternally
    var customMetadata: dynamic /* `T$26` | Nothing? */
}
external interface Storage {
    var app: App
    var maxOperationRetryTime: Number
    var maxUploadRetryTime: Number
    fun ref(path: String? = definedExternally /* null */): Reference
    fun refFromURL(url: String): Reference
    fun setMaxOperationRetryTime(time: Number): Any
    fun setMaxUploadRetryTime(time: Number): Any
}
external object StringFormat {
    var BASE64: StringFormat
    var BASE64URL: StringFormat
    var DATA_URL: StringFormat
    var RAW: StringFormat
}
external object TaskEvent {
    var STATE_CHANGED: TaskEvent
}
external object TaskState {
    var CANCELED: TaskState
    var ERROR: TaskState
    var PAUSED: TaskState
    var RUNNING: TaskState
    var SUCCESS: TaskState
}
external interface UploadMetadata : SettableMetadata {
    var md5Hash: String? get() = definedExternally; set(value) = definedExternally
}
external interface UploadTask {
    fun cancel(): Boolean
    fun catch(onRejected: (a: Error) -> Any): Promise<Any>
    fun on(event: TaskEvent, nextOrObserver: Observer<UploadTaskSnapshot,Error>? = definedExternally /* null */, error: ((a: Error) -> Any)? = definedExternally /* null */, complete: Unsubscribe? = definedExternally /* null */): Function<*>
    fun on(event: TaskEvent, nextOrObserver: Nothing? = definedExternally /* null */, error: ((a: Error) -> Any)? = definedExternally /* null */, complete: Unsubscribe? = definedExternally /* null */): Function<*>
    fun on(event: TaskEvent, nextOrObserver: ((a: UploadTaskSnapshot) -> Any)? = definedExternally /* null */, error: ((a: Error) -> Any)? = definedExternally /* null */, complete: Unsubscribe? = definedExternally /* null */): Function<*>
    fun pause(): Boolean
    fun resume(): Boolean
    var snapshot: UploadTaskSnapshot
    fun then(onFulfilled: ((a: UploadTaskSnapshot) -> Any)? = definedExternally /* null */, onRejected: ((a: Error) -> Any)? = definedExternally /* null */): Promise<Any>
    fun on(event: TaskEvent): Function<*>
}
external interface UploadTaskSnapshot {
    var bytesTransferred: Number
    var downloadURL: String?
    var metadata: FullMetadata
    var ref: Reference
    var state: TaskState
    var task: UploadTask
    var totalBytes: Number
}