@file:JsQualifier("PouchDB.Core")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION",
    "DEPRECATION"
)
package pouchdb.core

import pouchdb.Content
import pouchdb.Document
import pouchdb.EventEmitter
import kotlin.js.*
import kotlin.js.Json

external interface Error {
    var status: Number?
        get() = definedExternally
        set(value) = definedExternally
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var message: String?
        get() = definedExternally
        set(value) = definedExternally
    var reason: String?
        get() = definedExternally
        set(value) = definedExternally
    var error: dynamic /* String | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var id: String?
        get() = definedExternally
        set(value) = definedExternally
    var rev: RevisionId?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Options {
    var fetch: pouchdb.Fetch?
        get() = definedExternally
        set(value) = definedExternally
}

external interface BasicResponse {
    var ok: Boolean
}

external interface Response : BasicResponse {
    var id: DocumentId
    var rev: RevisionId
}

external interface DatabaseInfo {
    var db_name: String
    var doc_count: Number
    var update_seq: dynamic /* Number | String */
}

external interface Revision<Content > {
    var ok: Content /* Content & IdMeta */
}

external interface RevisionInfo {
    var rev: RevisionId
    var status: dynamic /* 'available' | 'compacted' | 'not compacted' | 'missing' */
}

external interface RevisionDiffOptions {
    @nativeGetter
    operator fun get(DocumentId: String): Array<String>?
    @nativeSetter
    operator fun set(DocumentId: String, value: Array<String>)
}

external interface RevisionDiff {
    var missing: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var possible_ancestors: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RevisionDiffResponse {
    @nativeGetter
    operator fun get(DocumentId: String): RevisionDiff?
    @nativeSetter
    operator fun set(DocumentId: String, value: RevisionDiff)
}

external interface IdMeta {
    var _id: DocumentId
}

external interface RevisionIdMeta {
    var _rev: RevisionId
}

external interface `T$1` {
    var ids: Array<RevisionId>
    var start: Number
}

external interface GetMeta {
    var _conflicts: Array<RevisionId>?
        get() = definedExternally
        set(value) = definedExternally
    var _rev: RevisionId
    var _revs_info: Array<RevisionInfo>?
        get() = definedExternally
        set(value) = definedExternally
    var _revisions: `T$1`?
        get() = definedExternally
        set(value) = definedExternally
    var _attachments: Attachments?
        get() = definedExternally
        set(value) = definedExternally
}

external interface StubAttachment {
    var content_type: String
    var digest: String
    var stub: String /* true */
    var length: Number
}

external interface FullAttachment {
    var content_type: String
    var digest: String?
        get() = definedExternally
        set(value) = definedExternally
    var data: dynamic /* String | Blob | Buffer */
}

external interface Attachments {
    @nativeGetter
    operator fun get(attachmentId: String): dynamic /* StubAttachment | FullAttachment */
    @nativeSetter
    operator fun set(attachmentId: String, value: StubAttachment)
    @nativeSetter
    operator fun set(attachmentId: String, value: FullAttachment)
}

external interface `T$9` {
    @nativeGetter
    operator fun get(filterName: String): String?
    @nativeSetter
    operator fun set(filterName: String, value: String)
}

external interface `T$10` {
    var map: String
    var reduce: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$11` {
    @nativeGetter
    operator fun get(viewName: String): `T$10`?
    @nativeSetter
    operator fun set(viewName: String, value: `T$10`)
}

external interface `T$12` {
    var filters: `T$9`?
        get() = definedExternally
        set(value) = definedExternally
    var views: `T$11`?
        get() = definedExternally
        set(value) = definedExternally
    var _rev: RevisionId?
        get() = definedExternally
        set(value) = definedExternally
    var _attachments: Attachments?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$13` {
    var _id: DocumentId?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AllDocsOptions : Options {
    var attachments: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var binary: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var conflicts: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var descending: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var include_docs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var limit: Number?
        get() = definedExternally
        set(value) = definedExternally
    var skip: Number?
        get() = definedExternally
        set(value) = definedExternally
    var update_seq: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AllDocsWithKeyOptions : AllDocsOptions {
    var key: DocumentKey
}

external interface AllDocsWithKeysOptions : AllDocsOptions {
    var keys: Array<DocumentId>
}

external interface AllDocsWithinRangeOptions : AllDocsOptions {
    var startkey: DocumentKey
    var endkey: DocumentKey
    var inclusive_end: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AllDocsMeta {
    var _conflicts: Array<RevisionId>?
        get() = definedExternally
        set(value) = definedExternally
    var _attachments: Attachments?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$2` {
    var rev: RevisionId
    var deleted: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$3` {
    var doc: Document<Content /* Content & AllDocsMeta */> /* Content & RevisionIdMeta */
    var id: DocumentId
    var key: DocumentKey
    var value: `T$2`
}

external interface AllDocsResponse<Content > {
    var offset: Number
    var total_rows: Number
    var update_seq: dynamic /* Number | String */
        get() = definedExternally
        set(value) = definedExternally
    var rows: Array<`T$3`>
}

external interface BulkDocsOptions : Options {
    var new_edits: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$4` {
    var id: String
    var rev: RevisionId?
        get() = definedExternally
        set(value) = definedExternally
}

external interface BulkGetOptions : Options {
    var docs: Array<`T$4`>
    var revs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var attachments: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var binary: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$5` {
    var ok: Content /* Content & GetMeta */
}

external interface `T$6` {
    var error: Error
}

external interface `T$7` {
    var id: String
    var docs: Array<dynamic /* `T$5` | `T$6` */>
}

external interface BulkGetResponse<Content > {
    var results: Array<`T$7`>
}

external interface ChangesMeta {
    var _conflicts: Array<RevisionId>?
        get() = definedExternally
        set(value) = definedExternally
    var _deleted: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var _attachments: Attachments?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ChangesOptions {
    var live: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var since: dynamic /* 'now' | Number | String */
        get() = definedExternally
        set(value) = definedExternally
    var timeout: dynamic /* Number | false */
        get() = definedExternally
        set(value) = definedExternally
    var include_docs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var limit: dynamic /* Number | false */
        get() = definedExternally
        set(value) = definedExternally
    var conflicts: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var attachments: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var binary: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var descending: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var heartbeat: dynamic /* Number | false */
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
    var return_docs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var batch_size: Number?
        get() = definedExternally
        set(value) = definedExternally
    var style: dynamic /* 'main_only' | 'all_docs' */
        get() = definedExternally
        set(value) = definedExternally
    var seq_interval: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$8` {
    var rev: String
}

external interface ChangesResponseChange<Content > {
    var id: String
    var seq: dynamic /* Number | String */
    var changes: Array<`T$8`>
    var deleted: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var doc: Document<Content /* Content & ChangesMeta */> /* Content & RevisionIdMeta */
}

external interface ChangesResponse<Content > {
    var status: String
    var last_seq: dynamic /* Number | String */
    var results: Array<ChangesResponseChange<Content>>
}

external interface Changes<Content > : EventEmitter, Promise<ChangesResponse<Content>> {
    fun on(event: String /* 'change' */, listener: (value: ChangesResponseChange<Content>) -> Any): Changes<Content> /* this */
    fun on(event: String /* 'complete' */, listener: (value: ChangesResponse<Content>) -> Any): Changes<Content> /* this */
    fun on(event: String /* 'error' */, listener: (value: Any) -> Any): Changes<Content> /* this */
    fun cancel()
}

external interface GetOptions : Options {
    var conflicts: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var rev: RevisionId?
        get() = definedExternally
        set(value) = definedExternally
    var revs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var revs_info: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var attachments: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var binary: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var latest: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GetOpenRevisions : Options {
    var open_revs: dynamic /* 'all' | Array<RevisionId> */
    var revs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CompactOptions : Options {
    var interval: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface PutOptions : Options {
    var force: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RemoveAttachmentResponse : BasicResponse {
    var id: DocumentId
    var rev: RevisionId
}