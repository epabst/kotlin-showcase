@file:JsQualifier("PouchDB")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package pouchdb

import kotlin.js.*

external interface DatabaseWithFind<Content> : DatabaseWithMapReduce<Content> {
    fun find(request: pouchdb.find.FindRequest<Content>, callback: pouchdb.core.Callback<pouchdb.find.FindResponse<Content>>)
    fun find(request: pouchdb.find.FindRequest<Content> = definedExternally /* null */): Promise<pouchdb.find.FindResponse<Content>>
    fun createIndex(index: pouchdb.find.CreateIndexOptions, callback: pouchdb.core.Callback<pouchdb.find.CreateIndexResponse<Content>>)
    fun createIndex(index: pouchdb.find.CreateIndexOptions = definedExternally /* null */): Promise<pouchdb.find.CreateIndexResponse<Content>>
    fun getIndexes(callback: pouchdb.core.Callback<pouchdb.find.GetIndexesResponse<Content>>)
    fun getIndexes(): Promise<pouchdb.find.GetIndexesResponse<Content>>
    fun deleteIndex(index: pouchdb.find.DeleteIndexOptions, callback: pouchdb.core.Callback<pouchdb.find.DeleteIndexResponse<Content>>)
    fun deleteIndex(index: pouchdb.find.DeleteIndexOptions = definedExternally /* null */): Promise<pouchdb.find.DeleteIndexResponse<Content>>
}