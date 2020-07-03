@file:JsQualifier("PouchDB.Query")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package pouchdb.query

import pouchdb.Content
import pouchdb.ExistingDocument
import kotlin.js.*

external interface Options<Content, Reduction> {
    var reduce: dynamic /* Reducer<Content, Reduction> | '_sum' | '_count' | '_stats' | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var include_docs: Boolean?
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
    var startkey: Any?
        get() = definedExternally
        set(value) = definedExternally
    var endkey: Any?
        get() = definedExternally
        set(value) = definedExternally
    var inclusive_end: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var limit: Number?
        get() = definedExternally
        set(value) = definedExternally
    var skip: Number?
        get() = definedExternally
        set(value) = definedExternally
    var descending: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var key: Any?
        get() = definedExternally
        set(value) = definedExternally
    var keys: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var group: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var group_level: Number?
        get() = definedExternally
        set(value) = definedExternally
    var stale: dynamic /* 'ok' | 'update_after' */
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$0` {
    var id: Any
    var key: Any
    var value: Any
    var doc: ExistingDocument<Content /* Content & PouchDB.Core.AllDocsMeta */>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Response<Content> {
    var total_rows: Number
    var offset: Number
    var rows: Array<`T$0`>
}