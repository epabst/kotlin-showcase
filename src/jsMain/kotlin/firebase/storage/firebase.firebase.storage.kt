@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:[JsModule("firebase/storage") JsNonModule]
package firebase.storage

import kotlin.js.*

external interface FullMetadata : firebase.storage.UploadMetadata {
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
    fun child(path: String): firebase.storage.Reference
    fun delete(): Promise<Any>
    var fullPath: String
    fun getDownloadURL(): Promise<Any>
    fun getMetadata(): Promise<Any>
    var name: String
    var parent: firebase.storage.Reference?
    fun put(blob: Any, metadata: firebase.storage.UploadMetadata? = definedExternally /* null */): firebase.storage.UploadTask
    var root: firebase.storage.Reference
    var storage: firebase.storage.Storage
    override fun toString(): String
    fun updateMetadata(metadata: firebase.storage.SettableMetadata): Promise<Any>
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
    fun ref(path: String? = definedExternally /* null */): firebase.storage.Reference
    fun refFromURL(url: String): firebase.storage.Reference
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
external interface UploadMetadata : firebase.storage.SettableMetadata {
    var md5Hash: String?
}
external interface UploadTask {
    fun cancel(): Boolean
    fun catch(onRejected: (a: Error) -> Any): Promise<Any>
    fun on(event: firebase.storage.ITaskEvent, nextOrObserver: Any? = definedExternally /* null */, error: (a: Error) -> Any? = definedExternally /* null */, complete: () -> Any? = definedExternally /* null */): Function<*>
    fun pause(): Boolean
    fun resume(): Boolean
    var snapshot: firebase.storage.UploadTaskSnapshot
    fun then(onFulfilled: (a: firebase.storage.UploadTaskSnapshot) -> Any? = definedExternally /* null */, onRejected: (a: Error) -> Any? = definedExternally /* null */): Promise<Any>
}
external interface UploadTaskSnapshot {
    var bytesTransferred: Number
    var downloadURL: String?
    var metadata: firebase.storage.FullMetadata
    var ref: firebase.storage.Reference
    var state: firebase.storage.ITaskState
    var task: firebase.storage.UploadTask
    var totalBytes: Number
}
