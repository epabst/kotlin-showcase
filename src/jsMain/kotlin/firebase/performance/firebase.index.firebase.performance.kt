@file:[JsModule("firebase/performance") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused", "DEPRECATION")
package firebase.performance

import kotlin.js.*

external interface Performance {
    fun trace(traceName: String): Trace
    var instrumentationEnabled: Boolean
    var dataCollectionEnabled: Boolean
}
external interface Metrics {
    @nativeGetter
    operator fun get(key: String): Number?
    @nativeSetter
    operator fun set(key: String, value: Number)
}
external interface TraceAttributes {
    @nativeGetter
    operator fun get(key: String): String?
    @nativeSetter
    operator fun set(key: String, value: String)
}
external interface TraceRecordOptions {
    var metrics: Metrics? get() = definedExternally; set(value) = definedExternally
    var attributes: TraceAttributes? get() = definedExternally; set(value) = definedExternally
}
external interface Trace {
    fun start()
    fun stop()
    fun record(startTime: Number, duration: Number, options: TraceRecordOptions? = definedExternally /* null */)
    fun incrementMetric(metricName: String, num: Number? = definedExternally /* null */)
    fun putMetric(metricName: String, num: Number)
    fun getMetric(metricName: String): Number
    fun putAttribute(attr: String, value: String)
    fun getAttribute(attr: String): String?
    fun removeAttribute(attr: String)
    fun getAttributes(): TraceAttributes
}