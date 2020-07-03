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

external interface ReplicationConfig {
    fun <Content> to(target: String, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
    fun <Content> to(target: Database<Content>, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
    fun <Content> from(source: String, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
    fun <Content> from(source: Database<Content>, options: PouchDB.Replication.ReplicateOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.ReplicationResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Replication<Content>
}

external interface DatabaseWithReplication<Content > {
    var replicate: ReplicationConfig
    fun <Content> sync(remote: String, options: PouchDB.Replication.SyncOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.SyncResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Sync<Content>
    fun <Content> sync(remote: Database<Content>, options: PouchDB.Replication.SyncOptions = definedExternally /* null */, callback: PouchDB.Core.Callback<PouchDB.Replication.SyncResultComplete<Content>>? = definedExternally /* null */): PouchDB.Replication.Sync<Content>
}