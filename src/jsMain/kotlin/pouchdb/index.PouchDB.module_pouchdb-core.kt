@file:JsQualifier("PouchDB")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package PouchDB

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

external interface Static : EventEmitter {
    fun plugin(plugin: String /* 'This should be passed to PouchDB.plugin()' */): Static
    var version: String
    var fetch: Fetch
    fun on(event: String /* 'created' */, listener: (dbName: String) -> Any): Static /* this */
    fun on(event: String /* 'destroyed' */, listener: (dbName: String) -> Any): Static /* this */
    var debug: debug.IDebug
    fun defaults(options: LocalDatabaseConfiguration): Any
    fun defaults(options: RemoteDatabaseConfiguration): Any
}

external interface `T$15` {
    var rev: Core.RevisionId?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Database<Content : Any> : EventEmitter {
    var name: String
    fun <Model> allDocs(options: PouchDB.Core.AllDocsWithKeyOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: PouchDB.Core.AllDocsWithKeysOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: PouchDB.Core.AllDocsWithinRangeOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> allDocs(options: PouchDB.Core.AllDocsOptions = definedExternally /* null */): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
    fun <Model> bulkDocs(docs: Array<PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */>, options: PouchDB.Core.BulkDocsOptions, callback: Core.Callback<Array<dynamic /* Core.Response | Core.Error */>>)
    fun <Model> bulkDocs(docs: Array<PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */>, options: PouchDB.Core.BulkDocsOptions = definedExternally /* null */): Promise<Array<dynamic /* Core.Response | Core.Error */>>
    fun compact(options: PouchDB.Core.CompactOptions = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun compact(options: PouchDB.Core.CompactOptions, callback: Core.Callback<PouchDB.Core.Response>)
    fun destroy(options: PouchDB.Core.Options, callback: Core.Callback<Any>)
    fun destroy(options: PouchDB.Core.Options = definedExternally /* null */): Promise<Unit>
    fun <Model> get(docId: Core.DocumentId, options: PouchDB.Core.GetOptions, callback: Core.Callback<Content /* Content & IdMeta */>)
    fun <Model> get(docId: Core.DocumentId, options: PouchDB.Core.GetOpenRevisions, callback: Core.Callback<Array<PouchDB.Core.Revision<Content /* Content & Model */>>>)
    fun <Model> get(docId: Core.DocumentId, options: PouchDB.Core.GetOptions = definedExternally /* null */): Promise<Content /* Content & IdMeta */>
    fun <Model> get(docId: Core.DocumentId, options: PouchDB.Core.GetOpenRevisions): Promise<Array<PouchDB.Core.Revision<Content /* Content & Model */>>>
    fun <Model> post(doc: NewDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & `T$12` */, options: PouchDB.Core.Options, callback: Core.Callback<PouchDB.Core.Response>)
    fun <Model> post(doc: NewDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & `T$12` */, options: PouchDB.Core.Options = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun <Model> put(doc: PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */, options: PouchDB.Core.PutOptions, callback: Core.Callback<PouchDB.Core.Response>)
    fun <Model> put(doc: PostDocument<Content /* Content & Model */> /* NewDocument<Content /* Content & Model */> & ChangesMeta & `T$13` */, options: PouchDB.Core.PutOptions = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun remove(doc: IdMeta /* IdMeta & RevisionIdMeta */, options: PouchDB.Core.Options, callback: Core.Callback<PouchDB.Core.Response>)
    fun remove(docId: Core.DocumentId, revision: Core.RevisionId, options: PouchDB.Core.Options, callback: Core.Callback<PouchDB.Core.Response>)
    fun remove(doc: IdMeta /* IdMeta & RevisionIdMeta */, options: PouchDB.Core.Options = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun remove(docId: Core.DocumentId, revision: Core.RevisionId, options: PouchDB.Core.Options = definedExternally /* null */): Promise<PouchDB.Core.Response>
    fun info(callback: Core.Callback<PouchDB.Core.DatabaseInfo>)
    fun info(): Promise<PouchDB.Core.DatabaseInfo>
    fun <Model> changes(options: PouchDB.Core.ChangesOptions, callback: Core.Callback<PouchDB.Core.Changes<Content /* Content & Model */>>)
    fun <Model> changes(options: PouchDB.Core.ChangesOptions = definedExternally /* null */): PouchDB.Core.Changes<Content /* Content & Model */>
    fun close(callback: Core.Callback<Any>)
    fun close(): Promise<Unit>
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId, attachment: String, type: String, callback: Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId, attachment: Blob, type: String, callback: Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId, attachment: Buffer, type: String, callback: Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId, attachment: String, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId, attachment: Blob, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId, attachment: Buffer, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, attachment: String, type: String, callback: Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, attachment: Blob, type: String, callback: Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, attachment: Buffer, type: String, callback: Core.Callback<PouchDB.Core.Response>)
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, attachment: String, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, attachment: Blob, type: String): Promise<PouchDB.Core.Response>
    fun putAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, attachment: Buffer, type: String): Promise<PouchDB.Core.Response>
    fun getAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, options: `T$15`, callback: Core.Callback<dynamic /* Blob | Buffer */>)
    fun getAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, options: `T$15`? = definedExternally /* null */): Promise<dynamic /* Blob | Buffer */>
    fun getAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, callback: Core.Callback<dynamic /* Blob | Buffer */>)
    fun removeAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId, callback: Core.Callback<PouchDB.Core.RemoveAttachmentResponse>)
    fun removeAttachment(docId: Core.DocumentId, attachmentId: Core.AttachmentId, rev: Core.RevisionId): Promise<PouchDB.Core.RemoveAttachmentResponse>
    fun <Model> bulkGet(options: PouchDB.Core.BulkGetOptions, callback: Core.Callback<PouchDB.Core.BulkGetResponse<Content /* Content & Model */>>)
    fun <Model> bulkGet(options: PouchDB.Core.BulkGetOptions): Promise<PouchDB.Core.BulkGetResponse<Content /* Content & Model */>>
    fun revsDiff(diff: PouchDB.Core.RevisionDiffOptions, callback: Core.Callback<PouchDB.Core.RevisionDiffResponse>)
    fun revsDiff(diff: PouchDB.Core.RevisionDiffOptions): Promise<PouchDB.Core.RevisionDiffResponse>
    fun <Model> allDocs(): Promise<PouchDB.Core.AllDocsResponse<Content /* Content & Model */>>
}