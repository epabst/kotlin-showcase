@file:[JsModule("firebase/analytics") JsNonModule]
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "unused", "DEPRECATION", "PropertyName")
package firebase.analytics

import firebase.CustomEventName
import firebase.app.App
import kotlin.js.*
import kotlin.js.Json

external interface EventParams1 {
    var currency: Any? get() = definedExternally; set(value) = definedExternally
    var value: Any? get() = definedExternally; set(value) = definedExternally
    var items: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface EventParams2 {
    var currency: Any? get() = definedExternally; set(value) = definedExternally
    var coupon: Any? get() = definedExternally; set(value) = definedExternally
    var value: Any? get() = definedExternally; set(value) = definedExternally
    var items: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface EventParams3 {
    var currency: Any? get() = definedExternally; set(value) = definedExternally
    var coupon: Any? get() = definedExternally; set(value) = definedExternally
    var value: Any? get() = definedExternally; set(value) = definedExternally
    var items: Any? get() = definedExternally; set(value) = definedExternally
    var checkout_step: Any? get() = definedExternally; set(value) = definedExternally
    var checkout_option: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface EventParams4 {
    var description: Any? get() = definedExternally; set(value) = definedExternally
    var fatal: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface EventParams5 {
    var value: Any? get() = definedExternally; set(value) = definedExternally
    var currency: Any? get() = definedExternally; set(value) = definedExternally
    var transaction_id: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface EventParams6 {
    var method: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface EventParams7 {
    var page_title: String? get() = definedExternally; set(value) = definedExternally
    var page_location: String? get() = definedExternally; set(value) = definedExternally
    var page_path: String? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface EventParams8 {
    var value: Any? get() = definedExternally; set(value) = definedExternally
    var currency: Any? get() = definedExternally; set(value) = definedExternally
    var transaction_id: Any
    var tax: Any? get() = definedExternally; set(value) = definedExternally
    var shipping: Any? get() = definedExternally; set(value) = definedExternally
    var items: Any? get() = definedExternally; set(value) = definedExternally
    var coupon: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface RefundEventParams {
    var value: Any? get() = definedExternally; set(value) = definedExternally
    var currency: Any? get() = definedExternally; set(value) = definedExternally
    var transaction_id: Any
    var tax: Any? get() = definedExternally; set(value) = definedExternally
    var shipping: Any? get() = definedExternally; set(value) = definedExternally
    var items: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface ScreenViewEventParams {
    var app_name: String
    var screen_name: Any
    var app_id: String? get() = definedExternally; set(value) = definedExternally
    var app_version: String? get() = definedExternally; set(value) = definedExternally
    var app_installer_id: String? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface SearchEventParams {
    var search_term: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface SelectContentEventParams {
    var items: Any? get() = definedExternally; set(value) = definedExternally
    var promotions: Any? get() = definedExternally; set(value) = definedExternally
    var content_type: Any? get() = definedExternally; set(value) = definedExternally
    var content_id: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface SetCheckoutOptionEventParams {
    var checkout_step: Any? get() = definedExternally; set(value) = definedExternally
    var checkout_option: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface ShareEventParams {
    var method: Any? get() = definedExternally; set(value) = definedExternally
    var content_type: Any? get() = definedExternally; set(value) = definedExternally
    var content_id: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface TimingCompleteEventParams {
    var name: String
    var value: Number
    var event_category: String? get() = definedExternally; set(value) = definedExternally
    var event_label: String? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface ViewItemEventParams {
    var items: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface ViewPromotionEventParams {
    var promotions: Any? get() = definedExternally; set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external interface Analytics {
    var app: App
    fun logEvent(eventName: String /* 'add_payment_info' */, eventParams: Json? = definedExternally /* null */, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'add_to_cart' */, eventParams: EventParams1, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'add_to_wishlist' */, eventParams: EventParams1, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'remove_from_cart' */, eventParams: EventParams1, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'begin_checkout' */, eventParams: EventParams2, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'checkout_progress' */, eventParams: EventParams3, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'exception' */, eventParams: EventParams4, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'generate_lead' */, eventParams: EventParams5, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'login' */, eventParams: EventParams6, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'page_view' */, eventParams: EventParams7, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'purchase' */, eventParams: EventParams8, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'refund' */, eventParams: RefundEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'screen_view' */, eventParams: ScreenViewEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'search' */, eventParams: SearchEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'view_search_results' */, eventParams: SearchEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'select_content' */, eventParams: SelectContentEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'set_checkout_option' */, eventParams: SetCheckoutOptionEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'share' */, eventParams: ShareEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'sign_up' */, eventParams: EventParams6, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'timing_complete' */, eventParams: TimingCompleteEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'view_item' */, eventParams: ViewItemEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'view_item_list' */, eventParams: ViewItemEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: String /* 'view_promotion' */, eventParams: ViewPromotionEventParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun logEvent(eventName: CustomEventName<String>, eventParams: Json? = definedExternally /* null */, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun setCurrentScreen(screenName: String, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun setUserId(id: String, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun setUserProperties(properties: CustomParams, options: AnalyticsCallOptions? = definedExternally /* null */)
    fun setAnalyticsCollectionEnabled(enabled: Boolean)
}
external interface AnalyticsCallOptions {
    var global: Boolean
}
external interface SettingsOptions {
    var gtagName: String? get() = definedExternally; set(value) = definedExternally
    var dataLayerName: String? get() = definedExternally; set(value) = definedExternally
}
external fun settings(settings: SettingsOptions)
external interface ControlParams {
    var groups: dynamic /* String | Array<String> */
    var send_to: dynamic /* String | Array<String> */
    var event_callback: (() -> Unit)? get() = definedExternally; set(value) = definedExternally
    var event_timeout: Number? get() = definedExternally; set(value) = definedExternally
}
external interface EventParams {
    var checkout_option: String? get() = definedExternally; set(value) = definedExternally
    var checkout_step: Number? get() = definedExternally; set(value) = definedExternally
    var content_id: String? get() = definedExternally; set(value) = definedExternally
    var content_type: String? get() = definedExternally; set(value) = definedExternally
    var coupon: String? get() = definedExternally; set(value) = definedExternally
    var currency: String? get() = definedExternally; set(value) = definedExternally
    var description: String? get() = definedExternally; set(value) = definedExternally
    var fatal: Boolean? get() = definedExternally; set(value) = definedExternally
    var items: Array<Item>? get() = definedExternally; set(value) = definedExternally
    var method: String? get() = definedExternally; set(value) = definedExternally
    var number: String? get() = definedExternally; set(value) = definedExternally
    var promotions: Array<Promotion>? get() = definedExternally; set(value) = definedExternally
    var screen_name: String? get() = definedExternally; set(value) = definedExternally
    var search_term: String? get() = definedExternally; set(value) = definedExternally
    var shipping: dynamic /* String | Number */
    var tax: dynamic /* String | Number */
    var transaction_id: String? get() = definedExternally; set(value) = definedExternally
    var value: Number? get() = definedExternally; set(value) = definedExternally
    var event_label: String? get() = definedExternally; set(value) = definedExternally
    var event_category: String
}
external interface CustomParams {
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}
external enum class EventName {
    ADD_PAYMENT_INFO /* = 'add_payment_info' */,
    ADD_TO_CART /* = 'add_to_cart' */,
    ADD_TO_WISHLIST /* = 'add_to_wishlist' */,
    BEGIN_CHECKOUT /* = 'begin_checkout' */,
    CHECKOUT_PROGRESS /* = 'checkout_progress' */,
    EXCEPTION /* = 'exception' */,
    GENERATE_LEAD /* = 'generate_lead' */,
    LOGIN /* = 'login' */,
    PAGE_VIEW /* = 'page_view' */,
    PURCHASE /* = 'purchase' */,
    REFUND /* = 'refund' */,
    REMOVE_FROM_CART /* = 'remove_from_cart' */,
    SCREEN_VIEW /* = 'screen_view' */,
    SEARCH /* = 'search' */,
    SELECT_CONTENT /* = 'select_content' */,
    SET_CHECKOUT_OPTION /* = 'set_checkout_option' */,
    SHARE /* = 'share' */,
    SIGN_UP /* = 'sign_up' */,
    TIMING_COMPLETE /* = 'timing_complete' */,
    VIEW_ITEM /* = 'view_item' */,
    VIEW_ITEM_LIST /* = 'view_item_list' */,
    VIEW_PROMOTION /* = 'view_promotion' */,
    VIEW_SEARCH_RESULTS /* = 'view_search_results' */
}
external interface Item {
    var brand: String? get() = definedExternally; set(value) = definedExternally
    var category: String? get() = definedExternally; set(value) = definedExternally
    var creative_name: String? get() = definedExternally; set(value) = definedExternally
    var creative_slot: String? get() = definedExternally; set(value) = definedExternally
    var id: String? get() = definedExternally; set(value) = definedExternally
    var location_id: String? get() = definedExternally; set(value) = definedExternally
    var name: String? get() = definedExternally; set(value) = definedExternally
    var price: dynamic /* String | Number */
    var quantity: Number? get() = definedExternally; set(value) = definedExternally
}
external interface Promotion {
    var creative_name: String? get() = definedExternally; set(value) = definedExternally
    var creative_slot: String? get() = definedExternally; set(value) = definedExternally
    var id: String? get() = definedExternally; set(value) = definedExternally
    var name: String? get() = definedExternally; set(value) = definedExternally
}