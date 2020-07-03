@file:JsQualifier("PouchDB.Replication")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package PouchDB.Replication

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

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
    var selector: Find.Selector?
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

external interface ReplicationEventEmitter<Content : Any, C, F> : EventEmitter {
    fun on(event: String /* 'change' */, listener: (info: C) -> Any): ReplicationEventEmitter<Content<Any>, C, F> /* this */
    fun on(event: String /* 'paused' */, listener: (err: Any) -> Any): ReplicationEventEmitter<Content<Any>, C, F> /* this */
    fun on(event: String /* 'denied' */, listener: (err: Any) -> Any): ReplicationEventEmitter<Content<Any>, C, F> /* this */
    fun on(event: String /* 'error' */, listener: (err: Any) -> Any): ReplicationEventEmitter<Content<Any>, C, F> /* this */
    fun on(event: String /* 'active' */, listener: () -> Any): ReplicationEventEmitter<Content<Any>, C, F> /* this */
    fun on(event: String /* 'complete' */, listener: (info: F) -> Any): ReplicationEventEmitter<Content<Any>, C, F> /* this */
    fun cancel()
}

external interface Replication<Content : Any> : ReplicationEventEmitter<Content, ReplicationResult<Content>, ReplicationResultComplete<Content>>, Promise<ReplicationResultComplete<Content>>

external interface ReplicationResult<Content : Any> {
    var doc_write_failures: Number
    var docs_read: Number
    var docs_written: Number
    var last_seq: Number
    var start_time: Date
    var ok: Boolean
    var errors: Array<Any>
    var docs: Array<Core.ExistingDocument<Content>>
}

external interface ReplicationResultComplete<Content : Any> : ReplicationResult<Content> {
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

external interface Sync<Content : Any> : ReplicationEventEmitter<Content, SyncResult<Content>, SyncResultComplete<Content>>, Promise<SyncResultComplete<Content>>

external interface SyncResult<Content : Any> {
    var direction: dynamic /* 'push' | 'pull' */
    var change: ReplicationResult<Content>
}

external interface SyncResultComplete<Content : Any> {
    var push: ReplicationResultComplete<Content>?
        get() = definedExternally
        set(value) = definedExternally
    var pull: ReplicationResultComplete<Content>?
        get() = definedExternally
        set(value) = definedExternally
}