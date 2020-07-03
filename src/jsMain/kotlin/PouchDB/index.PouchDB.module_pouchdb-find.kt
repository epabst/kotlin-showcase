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

external interface DatabaseWithFind<Content> : DatabaseWithMapReduce<Content> {
    fun find(request: PouchDB.Find.FindRequest<Content>, callback: PouchDB.Core.Callback<PouchDB.Find.FindResponse<Content>>)
    fun find(request: PouchDB.Find.FindRequest<Content> = definedExternally /* null */): Promise<PouchDB.Find.FindResponse<Content>>
    fun createIndex(index: PouchDB.Find.CreateIndexOptions, callback: PouchDB.Core.Callback<PouchDB.Find.CreateIndexResponse<Content>>)
    fun createIndex(index: PouchDB.Find.CreateIndexOptions = definedExternally /* null */): Promise<PouchDB.Find.CreateIndexResponse<Content>>
    fun getIndexes(callback: PouchDB.Core.Callback<PouchDB.Find.GetIndexesResponse<Content>>)
    fun getIndexes(): Promise<PouchDB.Find.GetIndexesResponse<Content>>
    fun deleteIndex(index: PouchDB.Find.DeleteIndexOptions, callback: PouchDB.Core.Callback<PouchDB.Find.DeleteIndexResponse<Content>>)
    fun deleteIndex(index: PouchDB.Find.DeleteIndexOptions = definedExternally /* null */): Promise<PouchDB.Find.DeleteIndexResponse<Content>>
}