@file:JsQualifier("PouchDB")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package PouchDB

import PouchDB.Core.IdMeta
import PouchDB.Core.NewDocument
import kotlin.js.*
import org.w3c.files.*

external interface Static : EventEmitter {
    fun <Content> replicate(source: String, target: String, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
    fun <Content> replicate(source: String, target: Database<Content>, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
    fun <Content> replicate(source: Database<Content>, target: String, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
    fun <Content> replicate(source: Database<Content>, target: Database<Content>, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
    fun <Content> sync(source: String, target: String, options: PouchDB.Replication.SyncOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.SyncResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Sync<Content>
    fun <Content> sync(source: String, target: Database<Content>, options: PouchDB.Replication.SyncOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.SyncResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Sync<Content>
    fun <Content> sync(source: Database<Content>, target: String, options: PouchDB.Replication.SyncOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.SyncResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Sync<Content>
    fun <Content> sync(source: Database<Content>, target: Database<Content>, options: PouchDB.Replication.SyncOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.SyncResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Sync<Content>
    fun plugin(plugin: String /* 'This should be passed to PouchDB.plugin()' */): Static
    var version: String
    var fetch: PouchDB.Fetch
    fun on(event: String /* 'created' */, listener: (dbName: String) -> Any): Static /* this */
    fun on(event: String /* 'destroyed' */, listener: (dbName: String) -> Any): Static /* this */
    var debug: debug.IDebug
    fun defaults(options: PouchDB.Configuration.LocalDatabaseConfiguration): Any
    fun defaults(options: PouchDB.Configuration.RemoteDatabaseConfiguration): Any
}

external interface `T$15` {
    var rev: PouchDB.Core.RevisionId?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Database<Content > : EventEmitter {
    var name: String
    fun <Model> allDocs(options: PouchDB.Core.AllDocsWithKeyOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: PouchDB.Core.AllDocsWithKeysOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: PouchDB.Core.AllDocsWithinRangeOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: PouchDB.Core.AllDocsOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> bulkDocs(docs: Array<PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */>, options: PouchDB.Core.BulkDocsOptions, callback: PouchDB.Core.Callback<Array<dynamic /* PouchDB.Core.Response | PouchDB.Core.Error */>>)
    fun <Model> bulkDocs(docs: Array<PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */>, options: PouchDB.Core.BulkDocsOptions = definedExternally /* null */): Promise<Array<dynamic /* PouchDB.Core.Response | PouchDB.Core.Error */>>
    fun compact(options: PouchDB.Core.CompactOptions = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun compact(options: PouchDB.Core.CompactOptions, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun destroy(options: PouchDB.Core.Options, callback: PouchDB.Core.Callback<Any>)
    fun destroy(options: PouchDB.Core.Options = definedExternally /* null */): Promise<Unit>
    fun <Model> get(docId: PouchDB.Core.DocumentId, options: PouchDB.Core.GetOptions, callback: PouchDB.Core.Callback<Content /* Content & IdMeta */>)
    fun <Model> get(docId: PouchDB.Core.DocumentId, options: PouchDB.Core.GetOpenRevisions, callback: PouchDB.Core.Callback<Array<PouchDB.Core.Revision<Content /* Content & Model */>>>)
    fun <Model> get(docId: PouchDB.Core.DocumentId, options: PouchDB.Core.GetOptions = definedExternally /* null */): Promise<Content /* Content & IdMeta */>
    fun <Model> get(docId: PouchDB.Core.DocumentId, options: PouchDB.Core.GetOpenRevisions): Promise<Array<PouchDB.Core.Revision<Content /* Content & Model */>>>
    fun <Model> post(doc: NewDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & `T$12` */, options: PouchDB.Core.Options, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun <Model> post(doc: NewDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & `T$12` */, options: PouchDB.Core.Options = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun <Model> put(doc: PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */, options: PouchDB.Core.PutOptions, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun <Model> put(doc: PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */, options: PouchDB.Core.PutOptions = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun remove(doc: IdMeta /* IdMeta & RevisionIdMeta */, options: PouchDB.Core.Options, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun remove(docId: PouchDB.Core.DocumentId, revision: PouchDB.Core.RevisionId, options: PouchDB.Core.Options, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun remove(doc: IdMeta /* IdMeta & RevisionIdMeta */, options: PouchDB.Core.Options = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun remove(docId: PouchDB.Core.DocumentId, revision: PouchDB.Core.RevisionId, options: PouchDB.Core.Options = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun info(callback: PouchDB.Core.Callback<PouchDB.Core.DatabaseInfo>)
    fun info(): Promise<PouchDB.Core.DatabaseInfo>
    fun <Model> changes(options: PouchDB.Core.ChangesOptions, callback: PouchDB.Core.Callback<PouchDB.Core.Changes<Content /* Content & Model */>>)
    fun <Model> changes(options: PouchDB.Core.ChangesOptions = definedExternally /* null */): PouchDB.Core.Changes<Content /* Content & Model */>
    fun close(callback: PouchDB.Core.Callback<Any>)
    fun close(): Promise<Unit>
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId, attachment: String, type: String, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId, attachment: Blob, type: String, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId, attachment: Buffer, type: String, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId, attachment: String, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId, attachment: Blob, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId, attachment: Buffer, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, attachment: String, type: String, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, attachment: Blob, type: String, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, attachment: Buffer, type: String, callback: PouchDB.Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, attachment: String, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, attachment: Blob, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, attachment: Buffer, type: String): Promise<PouchDB.Core.Response>
    fun getAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, options: `T$15`, callback: PouchDB.Core.Callback<dynamic /* Blob | Buffer */>)
    fun getAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, options: `T$15`? = definedExternally /* null */): Promise<dynamic /* Blob | Buffer */>
    fun getAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, callback: PouchDB.Core.Callback<dynamic /* Blob | Buffer */>)
    fun removeAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId, callback: PouchDB.Core.Callback<PouchDB.Core.RemoveAttachmentResponse>)
    fun removeAttachment(docId: PouchDB.Core.DocumentId, attachmentId: PouchDB.Core.AttachmentId, rev: PouchDB.Core.RevisionId): Promise<PouchDB.Core.RemoveAttachmentResponse>
    fun <Model> bulkGet(options: PouchDB.Core.BulkGetOptions, callback: PouchDB.Core.Callback<PouchDB.Core.BulkGetResponse<Content /* Content & Model */>>)
    fun <Model> bulkGet(options: PouchDB.Core.BulkGetOptions): Promise<PouchDB.Core.BulkGetResponse<Content /* Content & Model */>>
    fun revsDiff(diff: PouchDB.Core.RevisionDiffOptions, callback: PouchDB.Core.Callback<PouchDB.Core.RevisionDiffResponse>)
    fun revsDiff(diff: PouchDB.Core.RevisionDiffOptions): Promise<PouchDB.Core.RevisionDiffResponse>
    fun <Model> allDocs(): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
}