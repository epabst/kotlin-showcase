@file:[JsModule("firebase/remoteConfig") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "DEPRECATION", "unused")
package firebase.remoteConfig

import kotlin.js.*

external interface `T$4` {
    @nativeGetter
    operator fun get(key: String): dynamic /* String | Number | Boolean */
    @nativeSetter
    operator fun set(key: String, value: String)
    @nativeSetter
    operator fun set(key: String, value: Number)
    @nativeSetter
    operator fun set(key: String, value: Boolean)
}
external interface `T$5` {
    @nativeGetter
    operator fun get(key: String): Value?
    @nativeSetter
    operator fun set(key: String, value: Value)
}
external interface RemoteConfig {
    var settings: Settings
    var defaultConfig: `T$4`
    var fetchTimeMillis: Number
    var lastFetchStatus: dynamic /* 'no-fetch-yet' | 'success' | 'failure' | 'throttle' */
    fun activate(): Promise<Boolean>
    fun ensureInitialized(): Promise<Unit>
    fun fetch(): Promise<Unit>
    fun fetchAndActivate(): Promise<Boolean>
    fun getAll(): `T$5`
    fun getBoolean(key: String): Boolean
    fun getNumber(key: String): Number
    fun getString(key: String): String
    fun getValue(key: String): Value
    fun setLogLevel(logLevel: String /* 'debug' */)
    fun setLogLevel(logLevel: String /* 'error' */)
    fun setLogLevel(logLevel: String /* 'silent' */)
}
external interface Value {
    fun asBoolean(): Boolean
    fun asNumber(): Number
    fun asString(): String
    fun getSource(): dynamic /* 'static' | 'default' | 'remote' */
}
external interface Settings {
    var minimumFetchIntervalMillis: Number
    var fetchTimeoutMillis: Number
}