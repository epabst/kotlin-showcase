@file:JsQualifier("PouchDB")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package pouchdb

import kotlin.js.*

external interface ReplicationConfig {
    fun <Content> to(target: String, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
    fun <Content> to(target: Database<Content>, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
    fun <Content> from(source: String, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
    fun <Content> from(source: Database<Content>, options: pouchdb.replication.ReplicateOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Replication<Content>
}

external interface DatabaseWithReplication<Content > {
    var replicate: ReplicationConfig
    fun <Content> sync(remote: String, options: pouchdb.replication.SyncOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.SyncResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Sync<Content>
    fun <Content> sync(remote: Database<Content>, options: pouchdb.replication.SyncOptions = definedExternally /* null */, callback: pouchdb.core.Callback<pouchdb.replication.SyncResultComplete<Content>>? = definedExternally /* null */): pouchdb.replication.Sync<Content>
}