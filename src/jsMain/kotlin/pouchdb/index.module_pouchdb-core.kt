@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

package pouchdb

import org.khronos.webgl.Uint8Array
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import org.w3c.files.Blob
import kotlin.js.Promise

inline fun Blob.slice(start: Number? = null, end: Number? = null, contentType: String? = null): Blob = this.asDynamic().slice(start, end, contentType)

external interface `T$0` {
    var type: String /* 'Buffer' */
    var data: Array<Any>
}

external interface Buffer : Uint8Array {
    fun write(string: String, offset: Number? = definedExternally /* null */, length: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Number
    fun toString(encoding: String? = definedExternally /* null */, start: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): String
    fun toJSON(): `T$0`
    fun equals(otherBuffer: Buffer): Boolean
    fun compare(otherBuffer: Buffer, targetStart: Number? = definedExternally /* null */, targetEnd: Number? = definedExternally /* null */, sourceStart: Number? = definedExternally /* null */, sourceEnd: Number? = definedExternally /* null */): Number
    fun copy(targetBuffer: Buffer, targetStart: Number? = definedExternally /* null */, sourceStart: Number? = definedExternally /* null */, sourceEnd: Number? = definedExternally /* null */): Number
    fun slice(start: Number?, end: Number?): Buffer
    fun writeUIntLE(value: Number, offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeUIntBE(value: Number, offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeIntLE(value: Number, offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeIntBE(value: Number, offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readUIntLE(offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readUIntBE(offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readIntLE(offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readIntBE(offset: Number, byteLength: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readUInt8(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readUInt16LE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readUInt16BE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readUInt32LE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readUInt32BE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readInt8(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readInt16LE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readInt16BE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readInt32LE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readInt32BE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readFloatLE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readFloatBE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readDoubleLE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun readDoubleBE(offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun swap16(): Buffer
    fun swap32(): Buffer
    fun swap64(): Buffer
    fun writeUInt8(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeUInt16LE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeUInt16BE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeUInt32LE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeUInt32BE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeInt8(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeInt16LE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeInt16BE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeInt32LE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeInt32BE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeFloatLE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeFloatBE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeDoubleLE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun writeDoubleBE(value: Number, offset: Number, noAssert: Boolean? = definedExternally /* null */): Number
    fun fill(value: Any, offset: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): Buffer /* this */
    fun indexOf(value: String, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Number
    fun indexOf(value: Number, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Number
    fun indexOf(value: Buffer, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Number
    fun lastIndexOf(value: String, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Number
    fun lastIndexOf(value: Number, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Number
    fun lastIndexOf(value: Buffer, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Number
    fun entries(): IterableIterator<dynamic /* JsTuple<Number, Number> */>
    fun includes(value: String, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Boolean
    fun includes(value: Number, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Boolean
    fun includes(value: Buffer, byteOffset: Number? = definedExternally /* null */, encoding: String? = definedExternally /* null */): Boolean
    fun keys(): IterableIterator<Number>
    fun values(): IterableIterator<Number>
}

external interface EventEmitter {
    fun addListener(event: String, listener: Function<*>): EventEmitter /* this */
    fun addListener(event: Any, listener: Function<*>): EventEmitter /* this */
    fun on(event: String, listener: Function<*>): EventEmitter /* this */
    fun on(event: Any, listener: Function<*>): EventEmitter /* this */
    fun once(event: String, listener: Function<*>): EventEmitter /* this */
    fun once(event: Any, listener: Function<*>): EventEmitter /* this */
    fun removeListener(event: String, listener: Function<*>): EventEmitter /* this */
    fun removeListener(event: Any, listener: Function<*>): EventEmitter /* this */
    fun removeAllListeners(event: String? = definedExternally /* null */): EventEmitter /* this */
    fun removeAllListeners(event: Any? = definedExternally /* null */): EventEmitter /* this */
    fun setMaxListeners(n: Number): EventEmitter /* this */
    fun getMaxListeners(): Number
    fun listeners(event: String): Array<Function<*>>
    fun listeners(event: Any): Array<Function<*>>
    fun emit(event: String, vararg args: Any): Boolean
    fun emit(event: Any, vararg args: Any): Boolean
    fun listenerCount(type: String): Number
    fun listenerCount(type: Any): Number
    fun prependListener(event: String, listener: Function<*>): EventEmitter /* this */
    fun prependListener(event: Any, listener: Function<*>): EventEmitter /* this */
    fun prependOnceListener(event: String, listener: Function<*>): EventEmitter /* this */
    fun prependOnceListener(event: Any, listener: Function<*>): EventEmitter /* this */
    fun eventNames(): Array<dynamic /* String | Any */>
    fun removeAllListeners(): EventEmitter /* this */
}

typealias Fetch = (url: dynamic /* String | Request */, opts: RequestInit? /* = null */) -> Promise<Response>
