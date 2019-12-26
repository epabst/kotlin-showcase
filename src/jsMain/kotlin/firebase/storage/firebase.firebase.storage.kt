@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "DEPRECATION", "unused", "PropertyName")
@file:[JsModule("firebase/storage") JsNonModule]
package firebase.storage

import kotlin.js.*

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
    fun put(blob: Any, metadata: UploadMetadata? = definedExternally /* null */): UploadTask
    var root: Reference
    var storage: Storage
    override fun toString(): String
    fun updateMetadata(metadata: SettableMetadata): Promise<Any>
}
external interface ICustomMetadata {
    @nativeGetter
    operator fun get(key: String): String?
    @nativeSetter
    operator fun set(key: String, value: String)
}
external interface SettableMetadata {
    var cacheControl: String?
    var contentDisposition: String?
    var contentEncoding: String?
    var contentLanguage: String?
    var contentType: String?
    var customMetadata: ICustomMetadata?
}
external interface Storage {
    var app: firebase.app.App
    var maxOperationRetryTime: Number
    var maxUploadRetryTime: Number
    fun ref(path: String? = definedExternally /* null */): Reference
    fun refFromURL(url: String): Reference
    fun setMaxOperationRetryTime(time: Number): Any
    fun setMaxUploadRetryTime(time: Number): Any
}
external interface ITaskEvent {
    var STATE_CHANGED: String
}
external var TaskEvent: ITaskEvent = definedExternally
external interface ITaskState {
    var CANCELED: String
    var ERROR: String
    var PAUSED: String
    var RUNNING: String
    var SUCCESS: String
}
external var TaskState: ITaskState = definedExternally
external interface UploadMetadata : SettableMetadata {
    var md5Hash: String?
}
external interface UploadTask {
    fun cancel(): Boolean
    fun catch(onRejected: (a: Error) -> Any): Promise<Any>
    fun on(event: ITaskEvent, nextOrObserver: Any? = definedExternally /* null */, error: (a: Error) -> Any? = definedExternally /* null */, complete: () -> Any? = definedExternally /* null */): Function<*>
    fun pause(): Boolean
    fun resume(): Boolean
    var snapshot: UploadTaskSnapshot
    fun then(onFulfilled: (a: UploadTaskSnapshot) -> Any? = definedExternally /* null */, onRejected: (a: Error) -> Any? = definedExternally /* null */): Promise<Any>
}
external interface UploadTaskSnapshot {
    var bytesTransferred: Number
    var downloadURL: String?
    var metadata: FullMetadata
    var ref: Reference
    var state: ITaskState
    var task: UploadTask
    var totalBytes: Number
}
