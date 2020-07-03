@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package PouchDB.Core

typealias Callback<R> = (error: Error?, result: R?) -> Unit

typealias DocumentId = String

typealias DocumentKey = String

typealias AttachmentId = String

typealias RevisionId = String

typealias NewDocument<Content> = PouchDB.Content