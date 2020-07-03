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

external interface Filter<Content, Reduction> {
    var map: Map<Content, Reduction>
    var reduce: dynamic /* Reducer<Content, Reduction> | '_sum' | '_count' | '_stats' | String */
        get() = definedExternally
        set(value) = definedExternally
}

external interface DatabaseWithMapReduce<Content> : DatabaseWithReplication<Content> {
    fun viewCleanup(callback: PouchDB.Core.Callback<PouchDB.Core.BasicResponse>)
    fun viewCleanup(): Promise<PouchDB.Core.BasicResponse>
    fun <Result, Model> query(`fun`: String, opts: PouchDB.Query.Options<Model, Result>, callback: PouchDB.Core.Callback<PouchDB.Query.Response<Result>>)
    fun <Result, Model> query(`fun`: Map<Model, Result>, opts: PouchDB.Query.Options<Model, Result>, callback: PouchDB.Core.Callback<PouchDB.Query.Response<Result>>)
    fun <Result, Model> query(`fun`: Filter<Model, Result>, opts: PouchDB.Query.Options<Model, Result>, callback: PouchDB.Core.Callback<PouchDB.Query.Response<Result>>)
    fun <Result, Model> query(`fun`: String, callback: PouchDB.Core.Callback<PouchDB.Query.Response<Result>>)
    fun <Result, Model> query(`fun`: Map<Model, Result>, callback: PouchDB.Core.Callback<PouchDB.Query.Response<Result>>)
    fun <Result, Model> query(`fun`: Filter<Model, Result>, callback: PouchDB.Core.Callback<PouchDB.Query.Response<Result>>)
    fun <Result, Model> query(`fun`: String, opts: PouchDB.Query.Options<Model, Result> = definedExternally /* null */): Promise<PouchDB.Query.Response<Result>>
    fun <Result, Model> query(`fun`: Map<Model, Result>, opts: PouchDB.Query.Options<Model, Result> = definedExternally /* null */): Promise<PouchDB.Query.Response<Result>>
    fun <Result, Model> query(`fun`: Filter<Model, Result>, opts: PouchDB.Query.Options<Model, Result> = definedExternally /* null */): Promise<PouchDB.Query.Response<Result>>
}