@file:Suppress("unused")
package enzyme

import kotlinext.js.jsObject
import react.*
import react.ReactElement
import kotlin.reflect.KClass

private val configureEnzyme = configure(jsObject { adapter = Adapter() })

fun <P : RProps, S : RState, C : Component<P, S>> shallow(
    klazz: KClass<out C>
): ShallowWrapper<P, S, C> {
    return shallow(toElement(klazz))
}

fun <P : RProps, S : RState, C : Component<P, S>> shallow(
    klazz: KClass<out C>,
    handler: RHandler<P>
): ShallowWrapper<P, S, C> {
    return shallow(toElement(klazz, handler))
}

fun <P : RProps, S : RState, C : Component<P, S>> shallow(
    klazz: KClass<out C>,
    props: P,
    handler: RHandler<P>
): ShallowWrapper<P, S, C> {
    return shallow(toElement(klazz, props, handler))
}

fun <P : RProps, S : RState, C : Component<P, S>> mount(
    klazz: KClass<out C>
): ReactWrapper<P, S, C> {
    return mount(toElement(klazz))
}

fun <P : RProps, S : RState, C : Component<P, S>> mount(
    klazz: KClass<out C>,
    handler: RHandler<P>
): ReactWrapper<P, S, C> {
    return mount(toElement(klazz, handler))
}

fun <P : RProps, S : RState, C : Component<P, S>> mount(
    klazz: KClass<out C>,
    props: P,
    handler: RHandler<P>
): ReactWrapper<P, S, C> {
    return mount(toElement(klazz, props, handler))
}

private fun <P : RProps, S : RState, C : Component<P, S>> toElement(
    klazz: KClass<out C>
): ReactElement {
    return toElement(klazz, handler = {})
}

private fun <P : RProps, S : RState, C : Component<P, S>> toElement(
    klazz: KClass<out C>,
    handler: RHandler<P>
): ReactElement {
    return toElement(klazz, jsObject(), handler)
}

private fun <P : RProps, S : RState, C : Component<P, S>> toElement(
    klazz: KClass<out C>,
    props: P
): ReactElement {
    return toElement(klazz, props) {}
}

private fun <P : RProps, S : RState, C : Component<P, S>> toElement(
    klazz: KClass<out C>,
    props: P,
    handler: RHandler<P>
): ReactElement {
    val children = with(RElementBuilder(props)) {
        handler()
        childList
    }
    return createElement(klazz.rClass, props, *children.toTypedArray())
}
