@file:JsModule("pouchdb")

import pouchdb.configuration.CommonDatabaseConfiguration

@JsName("default")
external val PouchDB: pouchdb.Static

@JsName("default")
external fun <T> PouchDB(name: String, options: CommonDatabaseConfiguration = definedExternally): pouchdb.Database<T>
