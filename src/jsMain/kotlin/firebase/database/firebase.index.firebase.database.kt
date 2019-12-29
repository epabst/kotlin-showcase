@file:[JsModule("firebase/database") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused")
package firebase.database

import firebase.app.App
import kotlin.js.*

external interface DataSnapshot {
    fun child(path: String): DataSnapshot
    fun exists(): Boolean
    fun exportVal(): Any
    fun forEach(action: (a: DataSnapshot) -> dynamic): Boolean
    fun getPriority(): dynamic /* String | Number | Nothing? */
    fun hasChild(path: String): Boolean
    fun hasChildren(): Boolean
    var key: String?
    fun numChildren(): Number
    fun `val`(): Any
    var ref: Reference
    fun toJSON(): Any?
}
external interface Database {
    var app: App
    fun goOffline(): Any
    fun goOnline(): Any
    fun ref(path: String? = definedExternally /* null */): Reference
    fun refFromURL(url: String): Reference
}
external interface OnDisconnect {
    fun cancel(onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun remove(onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun set(value: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun setWithPriority(value: Any, priority: Number, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun setWithPriority(value: Any, priority: String, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun setWithPriority(value: Any, priority: Nothing?, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun update(values: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
}
external interface Query {
    fun endAt(value: Number, key: String? = definedExternally /* null */): Query
    fun endAt(value: String, key: String? = definedExternally /* null */): Query
    fun endAt(value: Boolean, key: String? = definedExternally /* null */): Query
    fun endAt(value: Nothing?, key: String? = definedExternally /* null */): Query
    fun equalTo(value: Number, key: String? = definedExternally /* null */): Query
    fun equalTo(value: String, key: String? = definedExternally /* null */): Query
    fun equalTo(value: Boolean, key: String? = definedExternally /* null */): Query
    fun equalTo(value: Nothing?, key: String? = definedExternally /* null */): Query
    fun isEqual(other: Query?): Boolean
    fun limitToFirst(limit: Number): Query
    fun limitToLast(limit: Number): Query
    fun off(eventType: String /* 'value' */ = definedExternally /* null */, callback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, context: Any? = definedExternally /* null */)
    fun off(eventType: String /* 'child_added' */ = definedExternally /* null */, callback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, context: Any? = definedExternally /* null */)
    fun off(eventType: String /* 'child_changed' */ = definedExternally /* null */, callback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, context: Any? = definedExternally /* null */)
    fun off(eventType: String /* 'child_moved' */ = definedExternally /* null */, callback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, context: Any? = definedExternally /* null */)
    fun off(eventType: String /* 'child_removed' */ = definedExternally /* null */, callback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, context: Any? = definedExternally /* null */)
    fun on(eventType: String /* 'value' */, callback: (a: DataSnapshot, b: String?) -> Any, cancelCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): (a: DataSnapshot?, b: String?) -> Any
    fun on(eventType: String /* 'child_added' */, callback: (a: DataSnapshot, b: String?) -> Any, cancelCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): (a: DataSnapshot?, b: String?) -> Any
    fun on(eventType: String /* 'child_changed' */, callback: (a: DataSnapshot, b: String?) -> Any, cancelCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): (a: DataSnapshot?, b: String?) -> Any
    fun on(eventType: String /* 'child_moved' */, callback: (a: DataSnapshot, b: String?) -> Any, cancelCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): (a: DataSnapshot?, b: String?) -> Any
    fun on(eventType: String /* 'child_removed' */, callback: (a: DataSnapshot, b: String?) -> Any, cancelCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): (a: DataSnapshot?, b: String?) -> Any
    fun once(eventType: String /* 'value' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: ((a: Error) -> Unit)? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'value' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'value' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Nothing? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_added' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: ((a: Error) -> Unit)? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_added' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_added' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Nothing? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_changed' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: ((a: Error) -> Unit)? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_changed' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_changed' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Nothing? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_moved' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: ((a: Error) -> Unit)? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_moved' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_moved' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Nothing? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_removed' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: ((a: Error) -> Unit)? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_removed' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_removed' */, successCallback: ((a: DataSnapshot, b: String?) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Nothing? = definedExternally /* null */, context: Any? = definedExternally /* null */): Promise<DataSnapshot>
    fun orderByChild(path: String): Query
    fun orderByKey(): Query
    fun orderByPriority(): Query
    fun orderByValue(): Query
    var ref: Reference
    fun startAt(value: Number, key: String? = definedExternally /* null */): Query
    fun startAt(value: String, key: String? = definedExternally /* null */): Query
    fun startAt(value: Boolean, key: String? = definedExternally /* null */): Query
    fun startAt(value: Nothing?, key: String? = definedExternally /* null */): Query
    fun toJSON(): Any
    override fun toString(): String
    fun off()
    fun once(eventType: String /* 'value' */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_added' */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_changed' */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_moved' */): Promise<DataSnapshot>
    fun once(eventType: String /* 'child_removed' */): Promise<DataSnapshot>
}
external interface Reference : Query {
    fun child(path: String): Reference
    var key: String?
    fun onDisconnect(): OnDisconnect
    var parent: Reference?
    fun push(value: Any? = definedExternally /* null */, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): ThenableReference
    fun remove(onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    var root: Reference
    fun set(value: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun setPriority(priority: String, onComplete: (a: Error?) -> Any): Promise<Any>
    fun setPriority(priority: Number, onComplete: (a: Error?) -> Any): Promise<Any>
    fun setPriority(priority: Nothing?, onComplete: (a: Error?) -> Any): Promise<Any>
    fun setWithPriority(newVal: Any, newPriority: String, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun setWithPriority(newVal: Any, newPriority: Number, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun setWithPriority(newVal: Any, newPriority: Nothing?, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
    fun transaction(transactionUpdate: (a: Any) -> Any, onComplete: ((a: Error?, b: Boolean, c: DataSnapshot?) -> Any)? = definedExternally /* null */, applyLocally: Boolean? = definedExternally /* null */): Promise<Any>
    fun update(values: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): Promise<Any>
}
external interface ThenableReference : Reference, Promise<Reference>
external fun enableLogging(logger: Boolean? = definedExternally /* null */, persistent: Boolean? = definedExternally /* null */): Any
external fun enableLogging(logger: ((a: String) -> Any)? = definedExternally /* null */, persistent: Boolean? = definedExternally /* null */): Any
external fun enableLogging(): Any