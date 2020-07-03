@file:JsQualifier("PouchDB.Configuration")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package pouchdb.configuration

import kotlin.js.*

external interface CommonDatabaseConfiguration {
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var adapter: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LocalDatabaseConfiguration : CommonDatabaseConfiguration {
    var auto_compaction: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var revs_limit: Number?
        get() = definedExternally
        set(value) = definedExternally
    var size: Number?
        get() = definedExternally
        set(value) = definedExternally
    var prefix: String?
        get() = definedExternally
        set(value) = definedExternally
    var deterministic_revs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$14` {
    var username: String?
        get() = definedExternally
        set(value) = definedExternally
    var password: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RemoteDatabaseConfiguration : CommonDatabaseConfiguration {
    var auth: `T$14`?
        get() = definedExternally
        set(value) = definedExternally
    var skip_setup: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}