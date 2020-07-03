@file:JsQualifier("PouchDB")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package pouchdb

import pouchdb.core.IdMeta
import pouchdb.core.NewDocument
import kotlin.js.*
import org.w3c.files.*

external interface Static : EventEmitter {
    fun <Content> replicate(source: String, target: String, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
    fun <Content> replicate(source: String, target: Database<Content>, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
    fun <Content> replicate(source: Database<Content>, target: String, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
    fun <Content> replicate(source: Database<Content>, target: Database<Content>, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
    fun <Content> sync(source: String, target: String, options: pouchdb.replication.SyncOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.SyncResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Sync<Content>
    fun <Content> sync(source: String, target: Database<Content>, options: pouchdb.replication.SyncOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.SyncResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Sync<Content>
    fun <Content> sync(source: Database<Content>, target: String, options: pouchdb.replication.SyncOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.SyncResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Sync<Content>
    fun <Content> sync(source: Database<Content>, target: Database<Content>, options: pouchdb.replication.SyncOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.SyncResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Sync<Content>
    fun plugin(plugin: String /* 'This should be passed to PouchDB.plugin()' */): Static
    var version: String
    var fetch: pouchdb.Fetch
    fun on(event: String /* 'created' */, listener: (dbName: String) -> Any): Static /* this */
    fun on(event: String /* 'destroyed' */, listener: (dbName: String) -> Any): Static /* this */
    var debug: debug.IDebug
    fun defaults(options: pouchdb.configuration.LocalDatabaseConfiguration): Any
    fun defaults(options: pouchdb.configuration.RemoteDatabaseConfiguration): Any
}

external interface `T$15` {
    var rev: pouchdb.core.RevisionId?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Database<Content > : EventEmitter {
    var name: String
    fun <Model> allDocs(options: pouchdb.core.AllDocsWithKeyOptions = definedExternally /* null */): Promise<pouchdb.core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: pouchdb.core.AllDocsWithKeysOptions = definedExternally /* null */): Promise<pouchdb.core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: pouchdb.core.AllDocsWithinRangeOptions = definedExternally /* null */): Promise<pouchdb.core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: pouchdb.core.AllDocsOptions = definedExternally /* null */): Promise<pouchdb.core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> bulkDocs(docs: Array<PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */>, options: pouchdb.core.BulkDocsOptions, callback: pouchdb.core.Callback<Array<dynamic /* PouchDB.Core.Response | PouchDB.Core.Error */>>)
    fun <Model> bulkDocs(docs: Array<PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */>, options: pouchdb.core.BulkDocsOptions = definedExternally /* null */): Promise<Array<dynamic /* PouchDB.Core.Response | PouchDB.Core.Error */>>
    fun compact(options: pouchdb.core.CompactOptions = definedExternally /* null */): Promise<pouchdb.core.Response>
    fun compact(options: pouchdb.core.CompactOptions, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun destroy(options: pouchdb.core.Options, callback: pouchdb.core.Callback<Any>)
    fun destroy(options: pouchdb.core.Options = definedExternally /* null */): Promise<Unit>
    fun <Model> get(docId: pouchdb.core.DocumentId, options: pouchdb.core.GetOptions, callback: pouchdb.core.Callback<Content /* Content & IdMeta */>)
    fun <Model> get(docId: pouchdb.core.DocumentId, options: pouchdb.core.GetOpenRevisions, callback: pouchdb.core.Callback<Array<pouchdb.core.Revision<Content /* Content & Model */>>>)
    fun <Model> get(docId: pouchdb.core.DocumentId, options: pouchdb.core.GetOptions = definedExternally /* null */): Promise<Content /* Content & IdMeta */>
    fun <Model> get(docId: pouchdb.core.DocumentId, options: pouchdb.core.GetOpenRevisions): Promise<Array<pouchdb.core.Revision<Content /* Content & Model */>>>
    fun <Model> post(doc: NewDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & `T$12` */, options: pouchdb.core.Options, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun <Model> post(doc: NewDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & `T$12` */, options: pouchdb.core.Options = definedExternally /* null */): Promise<pouchdb.core.Response>
    fun <Model> put(doc: PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */, options: pouchdb.core.PutOptions, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun <Model> put(doc: PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */, options: pouchdb.core.PutOptions = definedExternally /* null */): Promise<pouchdb.core.Response>
    fun remove(doc: IdMeta /* IdMeta & RevisionIdMeta */, options: pouchdb.core.Options, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun remove(docId: pouchdb.core.DocumentId, revision: pouchdb.core.RevisionId, options: pouchdb.core.Options, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun remove(doc: IdMeta /* IdMeta & RevisionIdMeta */, options: pouchdb.core.Options = definedExternally /* null */): Promise<pouchdb.core.Response>
    fun remove(docId: pouchdb.core.DocumentId, revision: pouchdb.core.RevisionId, options: pouchdb.core.Options = definedExternally /* null */): Promise<pouchdb.core.Response>
    fun info(callback: pouchdb.core.Callback<pouchdb.core.DatabaseInfo>)
    fun info(): Promise<pouchdb.core.DatabaseInfo>
    fun <Model> changes(options: pouchdb.core.ChangesOptions, callback: pouchdb.core.Callback<pouchdb.core.Changes<Content /* Content & Model */>>)
    fun <Model> changes(options: pouchdb.core.ChangesOptions = definedExternally /* null */): pouchdb.core.Changes<Content /* Content & Model */>
    fun close(callback: pouchdb.core.Callback<Any>)
    fun close(): Promise<Unit>
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId, attachment: String, type: String, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId, attachment: Blob, type: String, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId, attachment: Buffer, type: String, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId, attachment: String, type: String): Promise<pouchdb.core.Response>
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId, attachment: Blob, type: String): Promise<pouchdb.core.Response>
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId, attachment: Buffer, type: String): Promise<pouchdb.core.Response>
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, attachment: String, type: String, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, attachment: Blob, type: String, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, attachment: Buffer, type: String, callback: pouchdb.core.Callback<pouchdb.core.Response>)
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, attachment: String, type: String): Promise<pouchdb.core.Response>
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, attachment: Blob, type: String): Promise<pouchdb.core.Response>
    fun putAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, attachment: Buffer, type: String): Promise<pouchdb.core.Response>
    fun getAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, options: `T$15`, callback: pouchdb.core.Callback<dynamic /* Blob | Buffer */>)
    fun getAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, options: `T$15`? = definedExternally /* null */): Promise<dynamic /* Blob | Buffer */>
    fun getAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, callback: pouchdb.core.Callback<dynamic /* Blob | Buffer */>)
    fun removeAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId, callback: pouchdb.core.Callback<pouchdb.core.RemoveAttachmentResponse>)
    fun removeAttachment(docId: pouchdb.core.DocumentId, attachmentId: pouchdb.core.AttachmentId, rev: pouchdb.core.RevisionId): Promise<pouchdb.core.RemoveAttachmentResponse>
    fun <Model> bulkGet(options: pouchdb.core.BulkGetOptions, callback: pouchdb.core.Callback<pouchdb.core.BulkGetResponse<Content /* Content & Model */>>)
    fun <Model> bulkGet(options: pouchdb.core.BulkGetOptions): Promise<pouchdb.core.BulkGetResponse<Content /* Content & Model */>>
    fun revsDiff(diff: pouchdb.core.RevisionDiffOptions, callback: pouchdb.core.Callback<pouchdb.core.RevisionDiffResponse>)
    fun revsDiff(diff: pouchdb.core.RevisionDiffOptions): Promise<pouchdb.core.RevisionDiffResponse>
    fun <Model> allDocs(): Promise<pouchdb.core.AllDocsResponse<Content /* Content & Model */>>
}