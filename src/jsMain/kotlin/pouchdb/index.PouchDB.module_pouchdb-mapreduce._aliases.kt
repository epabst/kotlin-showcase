@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION",
    "unused",
    "UNUSED_TYPEALIAS_PARAMETER"
)
package pouchdb

typealias Map<Content, Result> = (doc: Content, emit: ((key: Any, value: dynamic /* Content | Result */) -> Unit)? /* = null */) -> Unit

typealias Reducer<Content, Reduction> = (keys: Any?, values: dynamic /* Array<Content> | Array<Reduction> */, rereduce: Boolean) -> dynamic