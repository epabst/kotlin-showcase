@file:JsQualifier("PouchDB")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package pouchdb

import kotlin.js.*

external interface Filter<Content, Reduction> {
    var map: Map<Content, Reduction>
    var reduce: dynamic /* Reducer<Content, Reduction> | '_sum' | '_count' | '_stats' | String */
        get() = definedExternally
        set(value) = definedExternally
}

external interface DatabaseWithMapReduce<Content> : DatabaseWithReplication<Content> {
    fun viewCleanup(callback: pouchdb.core.Callback<pouchdb.core.BasicResponse>)
    fun viewCleanup(): Promise<pouchdb.core.BasicResponse>
    fun <Result, Model> query(`fun`: String, opts: pouchdb.query.Options<Model, Result>, callback: pouchdb.core.Callback<pouchdb.query.Response<Result>>)
    fun <Result, Model> query(`fun`: Map<Model, Result>, opts: pouchdb.query.Options<Model, Result>, callback: pouchdb.core.Callback<pouchdb.query.Response<Result>>)
    fun <Result, Model> query(`fun`: Filter<Model, Result>, opts: pouchdb.query.Options<Model, Result>, callback: pouchdb.core.Callback<pouchdb.query.Response<Result>>)
    fun <Result, Model> query(`fun`: String, callback: pouchdb.core.Callback<pouchdb.query.Response<Result>>)
    fun <Result, Model> query(`fun`: Map<Model, Result>, callback: pouchdb.core.Callback<pouchdb.query.Response<Result>>)
    fun <Result, Model> query(`fun`: Filter<Model, Result>, callback: pouchdb.core.Callback<pouchdb.query.Response<Result>>)
    fun <Result, Model> query(`fun`: String, opts: pouchdb.query.Options<Model, Result> = definedExternally /* null */): Promise<pouchdb.query.Response<Result>>
    fun <Result, Model> query(`fun`: Map<Model, Result>, opts: pouchdb.query.Options<Model, Result> = definedExternally /* null */): Promise<pouchdb.query.Response<Result>>
    fun <Result, Model> query(`fun`: Filter<Model, Result>, opts: pouchdb.query.Options<Model, Result> = definedExternally /* null */): Promise<pouchdb.query.Response<Result>>
}