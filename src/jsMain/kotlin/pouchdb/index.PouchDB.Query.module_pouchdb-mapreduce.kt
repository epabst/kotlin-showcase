@file:JsQualifier("PouchDB.Query")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package PouchDB.Query

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

external interface Options<Content : Any, Reduction> {
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
    var doc: Core.ExistingDocument<Content /* Content & Core.AllDocsMeta */>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Response<Content : Any> {
    var total_rows: Number
    var offset: Number
    var rows: Array<`T$0`>
}