package firebase

typealias NextFn<T> = (value: T) -> Unit
typealias ErrorFn<Error> = (error: Error) -> Unit
typealias CompleteFn = () -> Unit
typealias Unsubscribe = () -> Unit
typealias CustomEventName<T> = String
