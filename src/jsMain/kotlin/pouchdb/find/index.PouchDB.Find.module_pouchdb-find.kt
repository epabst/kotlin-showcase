@file:JsQualifier("PouchDB.Find")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION",
    "DEPRECATION"
)
package pouchdb.find

import pouchdb.ExistingDocument
import kotlin.js.*

external interface ConditionOperators {
    var `$lt`: Any?
        get() = definedExternally
        set(value) = definedExternally
    var `$gt`: Any?
        get() = definedExternally
        set(value) = definedExternally
    var `$lte`: Any?
        get() = definedExternally
        set(value) = definedExternally
    var `$gte`: Any?
        get() = definedExternally
        set(value) = definedExternally
    var `$eq`: Any?
        get() = definedExternally
        set(value) = definedExternally
    var `$ne`: Any?
        get() = definedExternally
        set(value) = definedExternally
    var `$exists`: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var `$type`: dynamic /* "null" | "boolean" | "number" | "string" | "array" | "object" */
        get() = definedExternally
        set(value) = definedExternally
    var `$in`: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var `$nin`: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var `$size`: Number?
        get() = definedExternally
        set(value) = definedExternally
    var `$mod`: dynamic /* JsTuple<Number, Number> */
        get() = definedExternally
        set(value) = definedExternally
    var `$regex`: String?
        get() = definedExternally
        set(value) = definedExternally
    var `$all`: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var `$elemMatch`: ConditionOperators?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CombinationOperators {
    var `$and`: Array<Selector>?
        get() = definedExternally
        set(value) = definedExternally
    var `$or`: Array<Selector>?
        get() = definedExternally
        set(value) = definedExternally
    var `$not`: Selector?
        get() = definedExternally
        set(value) = definedExternally
    var `$nor`: Array<Selector>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Selector : CombinationOperators {
    @nativeGetter
    operator fun get(field: String): dynamic /* Selector | Array<Selector> | ConditionOperators | Any */
    @nativeSetter
    operator fun set(field: String, value: Selector)
    @nativeSetter
    operator fun set(field: String, value: Array<Selector>)
    @nativeSetter
    operator fun set(field: String, value: ConditionOperators)
    @nativeSetter
    operator fun set(field: String, value: Any)
    var _id: ConditionOperators?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$0` {
    @nativeGetter
    operator fun get(propName: String): dynamic /* 'asc' | 'desc' */
    @nativeSetter
    operator fun set(propName: String, value: String /* 'asc' */)
    @nativeSetter
    operator fun set(propName: String, value: String /* 'desc' */)
}

external interface FindRequest<Content > {
    var selector: Selector
    var fields: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var sort: Array<dynamic /* String | `T$0` */>?
        get() = definedExternally
        set(value) = definedExternally
    var limit: Number?
        get() = definedExternally
        set(value) = definedExternally
    var skip: Number?
        get() = definedExternally
        set(value) = definedExternally
    var use_index: dynamic /* String | dynamic */
        get() = definedExternally
        set(value) = definedExternally
}

external interface FindResponse<Content > {
    var docs: Array<ExistingDocument<Content>>
    var warning: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$1` {
    var fields: Array<String>
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var ddoc: String?
        get() = definedExternally
        set(value) = definedExternally
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CreateIndexOptions {
    var index: `T$1`
}

external interface CreateIndexResponse<Content > {
    var result: String
}

external interface `T$2` {
    @nativeGetter
    operator fun get(fieldName: String): String?
    @nativeSetter
    operator fun set(fieldName: String, value: String)
}

external interface `T$3` {
    var fields: Array<`T$2`>
}

external interface Index {
    var name: String
    var ddoc: String?
    var type: String
    var def: `T$3`
}

external interface GetIndexesResponse<Content > {
    var indexes: Array<Index>
}

external interface DeleteIndexOptions {
    var name: String
    var ddoc: String
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeleteIndexResponse<Content > {
    @nativeGetter
    operator fun get(propertyName: String): Any?
    @nativeSetter
    operator fun set(propertyName: String, value: Any)
}