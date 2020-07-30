@file:Suppress("unused", "UNUSED_TYPEALIAS_PARAMETER")

package enzyme

typealias StatelessComponent<Props> = (props: Props, context: Any? /* = null */) -> JsxElement?

typealias ReactElement<@Suppress("UNUSED_TYPEALIAS_PARAMETER") P> = react.ReactElement

// todo what should this be?
typealias JsxElement = react.ReactElement

typealias Intercepter<T> = (intercepter: T) -> Unit

typealias Parameters<T> = Any
