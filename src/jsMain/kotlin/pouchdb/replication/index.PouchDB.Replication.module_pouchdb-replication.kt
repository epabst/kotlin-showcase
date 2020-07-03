@file:JsQualifier("PouchDB.Replication")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package pouchdb.replication

import pouchdb.EventEmitter
import pouchdb.ExistingDocument
import kotlin.js.*
import kotlin.js.Json

external interface ReplicateOptions {
    var live: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var retry: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var filter: dynamic /* String | (doc: Any, params: Any) -> Any */
        get() = definedExternally
        set(value) = definedExternally
    var doc_ids: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var query_params: Json?
        get() = definedExternally
        set(value) = definedExternally
    var view: String?
        get() = definedExternally
        set(value) = definedExternally
    var selector: pouchdb.find.Selector?
        get() = definedExternally
        set(value) = definedExternally
    var since: Any?
        get() = definedExternally
        set(value) = definedExternally
    var heartbeat: Any?
        get() = definedExternally
        set(value) = definedExternally
    var timeout: dynamic /* Number | false */
        get() = definedExternally
        set(value) = definedExternally
    var batch_size: Number?
        get() = definedExternally
        set(value) = definedExternally
    var batches_limit: Number?
        get() = definedExternally
        set(value) = definedExternally
    val back_off_function: ((delay: Number) -> Number)?
        get() = definedExternally
    var checkpoint: dynamic /* Boolean | 'target' | 'source' */
        get() = definedExternally
        set(value) = definedExternally
}

external interface ReplicationEventEmitter<Content, C, F> : EventEmitter {
    fun on(event: String /* 'change' */, listener: (info: C) -> Any): ReplicationEventEmitter<Content, C, F> /* this */
    fun on(event: String /* 'paused' */, listener: (err: Any) -> Any): ReplicationEventEmitter<Content, C, F> /* this */
    fun on(event: String /* 'denied' */, listener: (err: Any) -> Any): ReplicationEventEmitter<Content, C, F> /* this */
    fun on(event: String /* 'error' */, listener: (err: Any) -> Any): ReplicationEventEmitter<Content, C, F> /* this */
    fun on(event: String /* 'active' */, listener: () -> Any): ReplicationEventEmitter<Content, C, F> /* this */
    fun on(event: String /* 'complete' */, listener: (info: F) -> Any): ReplicationEventEmitter<Content, C, F> /* this */
    fun cancel()
}

external interface Replication<Content > : ReplicationEventEmitter<Content, ReplicationResult<Content>, ReplicationResultComplete<Content>>, Promise<ReplicationResultComplete<Content>>

external interface ReplicationResult<Content > {
    var doc_write_failures: Number
    var docs_read: Number
    var docs_written: Number
    var last_seq: Number
    var start_time: Date
    var ok: Boolean
    var errors: Array<Any>
    var docs: Array<ExistingDocument<Content>>
}

external interface ReplicationResultComplete<Content> : ReplicationResult<Content> {
    var end_time: Date
    var status: String
}

external interface SyncOptions : ReplicateOptions {
    var push: ReplicateOptions?
        get() = definedExternally
        set(value) = definedExternally
    var pull: ReplicateOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Sync<Content > : ReplicationEventEmitter<Content, SyncResult<Content>, SyncResultComplete<Content>>, Promise<SyncResultComplete<Content>>

external interface SyncResult<Content > {
    var direction: dynamic /* 'push' | 'pull' */
    var change: ReplicationResult<Content>
}

external interface SyncResultComplete<Content > {
    var push: ReplicationResultComplete<Content>?
        get() = definedExternally
        set(value) = definedExternally
    var pull: ReplicationResultComplete<Content>?
        get() = definedExternally
        set(value) = definedExternally
}