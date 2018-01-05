@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("firebase.database")
package firebase.database

import kotlin.js.*

external interface DataSnapshot {
    fun child(path: String): firebase.database.DataSnapshot
    fun exists(): Boolean
    fun exportVal(): Any
    fun forEach(action: (a: firebase.database.DataSnapshot) -> Boolean): Boolean
    fun getPriority(): dynamic /* String | Number | Nothing? */
    fun hasChild(path: String): Boolean
    fun hasChildren(): Boolean
    var key: String?
    fun numChildren(): Number
    var ref: firebase.database.Reference
    fun `val`(): Any
}
external interface Database {
    var app: firebase.app.App
    fun goOffline(): Any
    fun goOnline(): Any
    fun ref(path: String? = definedExternally /* null */): firebase.database.Reference
    fun refFromURL(url: String): firebase.database.Reference
}
external interface OnDisconnect {
    fun cancel(onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun remove(onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun set(value: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun setWithPriority(value: Any, priority: Number, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun setWithPriority(value: Any, priority: String, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun setWithPriority(value: Any, priority: Nothing?, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun update(values: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
}
external interface Query {
    fun endAt(value: Number, key: String? = definedExternally /* null */): firebase.database.Query
    fun endAt(value: String, key: String? = definedExternally /* null */): firebase.database.Query
    fun endAt(value: Boolean, key: String? = definedExternally /* null */): firebase.database.Query
    fun endAt(value: Nothing?, key: String? = definedExternally /* null */): firebase.database.Query
    fun equalTo(value: Number, key: String? = definedExternally /* null */): firebase.database.Query
    fun equalTo(value: String, key: String? = definedExternally /* null */): firebase.database.Query
    fun equalTo(value: Boolean, key: String? = definedExternally /* null */): firebase.database.Query
    fun equalTo(value: Nothing?, key: String? = definedExternally /* null */): firebase.database.Query
    fun limitToFirst(limit: Number): firebase.database.Query
    fun limitToLast(limit: Number): firebase.database.Query
    fun off(eventType: String? = definedExternally /* null */, callback: ((a: firebase.database.DataSnapshot, b: String? /*= null*/) -> Any)? = definedExternally /* null */, context: Any? = definedExternally /* null */): Any
    fun on(eventType: String, callback: (a: firebase.database.DataSnapshot?, b: String? /*= null*/) -> Any, cancelCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): (a: firebase.database.DataSnapshot?, b: String? /*= null*/) -> Any
    fun once(eventType: String, successCallback: ((a: firebase.database.DataSnapshot, b: String? /*= null*/) -> Any)? = definedExternally /* null */, failureCallbackOrContext: Any? = definedExternally /* null */, context: Any? = definedExternally /* null */): firebase.Promise<Any>
    fun orderByChild(path: String): firebase.database.Query
    fun orderByKey(): firebase.database.Query
    fun orderByPriority(): firebase.database.Query
    fun orderByValue(): firebase.database.Query
    var ref: firebase.database.Reference
    fun startAt(value: Number, key: String? = definedExternally /* null */): firebase.database.Query
    fun startAt(value: String, key: String? = definedExternally /* null */): firebase.database.Query
    fun startAt(value: Boolean, key: String? = definedExternally /* null */): firebase.database.Query
    fun startAt(value: Nothing?, key: String? = definedExternally /* null */): firebase.database.Query
    override fun toString(): String
}
external interface Reference : firebase.database.Query {
    fun child(path: String): firebase.database.Reference
    var key: String?
    fun onDisconnect(): firebase.database.OnDisconnect
    var parent: firebase.database.Reference?
    fun push(value: Any? = definedExternally /* null */, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.database.ThenableReference
    fun remove(onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    var root: firebase.database.Reference
    fun set(value: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun setPriority(priority: String, onComplete: (a: Error?) -> Any): firebase.Promise<Any>
    fun setPriority(priority: Number, onComplete: (a: Error?) -> Any): firebase.Promise<Any>
    fun setPriority(priority: Nothing?, onComplete: (a: Error?) -> Any): firebase.Promise<Any>
    fun setWithPriority(newVal: Any, newPriority: String, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun setWithPriority(newVal: Any, newPriority: Number, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun setWithPriority(newVal: Any, newPriority: Nothing?, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
    fun transaction(transactionUpdate: (a: Any) -> Any, onComplete: ((a: Error?, b: Boolean, c: firebase.database.DataSnapshot?) -> Any)? = definedExternally /* null */, applyLocally: Boolean? = definedExternally /* null */): firebase.Promise<Any>
    fun update(values: Any, onComplete: ((a: Error?) -> Any)? = definedExternally /* null */): firebase.Promise<Any>
}
external interface ThenableReference : firebase.database.Reference, firebase.Thenable<Any>
external fun enableLogging(logger: Any? = definedExternally /* null */, persistent: Boolean? = definedExternally /* null */): Any = definedExternally
