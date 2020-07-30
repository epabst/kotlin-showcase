// Generated from @types/enzyme version 3.10.5
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "unused",
    "DEPRECATION",
    "CovariantEquals",
    "UNUSED_TYPEALIAS_PARAMETER",
    "PropertyName"
)
@file:JsModule("enzyme")
package enzyme

import cheerio.Cheerio
import kotlin.js.*
import org.w3c.dom.*
import react.*

abstract external class ElementClass(props: RProps) : Component<RProps, RState>

external interface ComponentClass<Props>

// todo what should this be?
external interface ReactHTMLAttributes<T> : RProps

external interface EnzymePropSelector {
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}

external interface DebugOptions {
    var ignoreProps: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var verbose: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CommonWrapper<P : RProps, S : RState, C : Component<P, S>> {
    fun filterWhere(predicate: (wrapper: CommonWrapper<P, S, C> /* this */) -> Boolean): CommonWrapper<P, S, C> /* this */
    fun contains(node: react.ReactElement): Boolean
    fun contains(node: Array<react.ReactElement>): Boolean
    fun contains(node: String): Boolean
    fun containsMatchingElement(node: react.ReactElement): Boolean
    fun containsMatchingElement(node: Array<react.ReactElement>): Boolean
    fun containsAllMatchingElements(nodes: Array<react.ReactElement>): Boolean
    fun containsAllMatchingElements(nodes: Array<Array<react.ReactElement>>): Boolean
    fun containsAnyMatchingElements(nodes: Array<react.ReactElement>): Boolean
    fun containsAnyMatchingElements(nodes: Array<Array<react.ReactElement>>): Boolean
    fun equals(node: react.ReactElement): Boolean
    fun matchesElement(node: react.ReactElement): Boolean
    fun hasClass(className: String): Boolean
    fun hasClass(className: RegExp): Boolean
    fun <K : Any> invoke(invokePropName: K): Any
    fun `is`(selector: String): Boolean
    fun `is`(selector: StatelessComponent<Any>): Boolean
    fun `is`(selector: ComponentClass<Any>): Boolean
    fun `is`(selector: EnzymePropSelector): Boolean
    fun isEmpty(): Boolean
    fun exists(selector: String = definedExternally /* null */): Boolean
    fun exists(selector: StatelessComponent<Any> = definedExternally /* null */): Boolean
    fun exists(selector: ComponentClass<Any> = definedExternally /* null */): Boolean
    fun exists(selector: EnzymePropSelector = definedExternally /* null */): Boolean
    fun not(selector: String): CommonWrapper<P, S, C> /* this */
    fun not(selector: StatelessComponent<Any>): CommonWrapper<P, S, C> /* this */
    fun not(selector: ComponentClass<Any>): CommonWrapper<P, S, C> /* this */
    fun not(selector: EnzymePropSelector): CommonWrapper<P, S, C> /* this */
    fun text(): String
    fun html(): String
    fun get(index: Number): react.ReactElement
    fun getNode(): react.ReactElement
    fun getNodes(): Array<react.ReactElement>
    fun getElement(): react.ReactElement
    fun getElements(): Array<react.ReactElement>
    fun <T : JsxElement> getDOMNode(): T
    fun at(index: Number): CommonWrapper<P, S, C> /* this */
    fun first(): CommonWrapper<P, S, C> /* this */
    fun last(): CommonWrapper<P, S, C> /* this */
    fun slice(begin: Number? = definedExternally /* null */, end: Number? = definedExternally /* null */): CommonWrapper<P, S, C> /* this */
    fun tap(intercepter: Intercepter<CommonWrapper<P, S, C> /* this */>): CommonWrapper<P, S, C> /* this */
    fun state(): S
    fun <K : Any> state(key: K): Any
    fun <T> state(key: String): T
    fun context(): Any
    fun <T> context(key: String): T
    fun props(): P
    fun <K : Any> prop(key: K): Any
    fun <T> prop(key: String): T
    fun key(): String
    fun simulate(event: String, vararg args: Any): CommonWrapper<P, S, C> /* this */
    fun simulateError(error: Any): CommonWrapper<P, S, C> /* this */
    fun setState(state: S, callback: (() -> Unit)? = definedExternally /* null */): CommonWrapper<P, S, C> /* this */
    fun setProps(props: P, callback: (() -> Unit)? = definedExternally /* null */): CommonWrapper<P, S, C> /* this */
    fun setContext(context: Any): CommonWrapper<P, S, C> /* this */
    fun instance(): C
    fun update(): CommonWrapper<P, S, C> /* this */
    fun debug(options: DebugOptions? = definedExternally /* null */): String
    fun name(): String
    fun forEach(fn: (wrapper: CommonWrapper<P, S, C> /* this */, index: Number) -> Any): CommonWrapper<P, S, C> /* this */
    fun <V> map(fn: (wrapper: CommonWrapper<P, S, C> /* this */, index: Number) -> V): Array<V>
    fun <R> reduce(fn: (prevVal: R, wrapper: CommonWrapper<P, S, C> /* this */, index: Number) -> R, initialValue: R? = definedExternally /* null */): R
    fun <R> reduceRight(fn: (prevVal: R, wrapper: CommonWrapper<P, S, C> /* this */, index: Number) -> R, initialValue: R? = definedExternally /* null */): R
    fun some(selector: String): Boolean
    fun some(selector: StatelessComponent<Any>): Boolean
    fun some(selector: ComponentClass<Any>): Boolean
    fun some(selector: EnzymePropSelector): Boolean
    fun someWhere(fn: (wrapper: CommonWrapper<P, S, C> /* this */) -> Boolean): Boolean
    fun every(selector: String): Boolean
    fun every(selector: StatelessComponent<Any>): Boolean
    fun every(selector: ComponentClass<Any>): Boolean
    fun every(selector: EnzymePropSelector): Boolean
    fun everyWhere(fn: (wrapper: CommonWrapper<P, S, C> /* this */) -> Boolean): Boolean
    fun isEmptyRender(): Boolean
    fun render(): Cheerio
    fun type(): dynamic /* String | enzyme.ComponentClass<P> | enzyme.StatelessComponent<P> */
    var length: Number
    fun exists(): Boolean
}

abstract external class ShallowWrapper<P : RProps, S : RState, C : Component<P, S>> : CommonWrapper<P, S, C> {
    constructor(nodes: Array<JsxElement>, root: ShallowWrapper<P, S, Component<P, S>>?, options: ShallowRendererProps?)
    constructor(nodes: JsxElement, root: ShallowWrapper<P, S, Component<P, S>>?, options: ShallowRendererProps?)
    open fun shallow(options: ShallowRendererProps? = definedExternally /* null */): ShallowWrapper<P, S, Component<P, S>>
    open fun unmount(): ShallowWrapper<P, S, C> /* this */
    open fun <P2 : RProps> find(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> find(component: ComponentClass<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> find(component: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <C2 : Component<P2, S2>, P2 : RProps, S2 : RState> find(componentClass: ComponentClass<Any>): ShallowWrapper<P2, S2, C2>
    open fun find(props: EnzymePropSelector): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun find(selector: String): ShallowWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun <P2 : RProps> filter(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> filter(component: ComponentClass<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> filter(component: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun filter(props: EnzymePropSelector): ShallowWrapper<P, S, Component<P, S>>
    open fun filter(props: String): ShallowWrapper<P, S, Component<P, S>>
    open fun findWhere(predicate: (wrapper: ShallowWrapper<RProps, RState, Component<RProps, RState>>) -> Boolean): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun <P2 : RProps> children(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> children(component: ComponentClass<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> children(component: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun children(selector: String): ShallowWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun children(props: EnzymePropSelector? = definedExternally /* null */): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun childAt(index: Number): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun <P2 : RProps, S2 : RState> childAt(index: Number): ShallowWrapper<P2, S2, Component<P2, S2>>
    open fun <C2 : Component<P2, S2>, P2 : RProps, S2 : RState> dive(options: ShallowRendererProps? = definedExternally /* null */): ShallowWrapper<P2, S2, C2>
    open fun <P2 : RProps, S2 : RState> dive(options: ShallowRendererProps? = definedExternally /* null */): ShallowWrapper<P2, S2, Component<P2, S2>>
    open fun <P2 : RProps, S2 : RState, C2 : Component<P2, S2>> dive(options: ShallowRendererProps? = definedExternally /* null */): ShallowWrapper<P2, S2, C2>
    open fun hostNodes(): ShallowWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun <P2 : RProps> parents(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> parents(component: ComponentClass<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> parents(component: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun parents(selector: String): ShallowWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun parents(props: EnzymePropSelector? = definedExternally /* null */): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun <P2 : RProps> closest(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> closest(component: ComponentClass<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> closest(component: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun closest(props: EnzymePropSelector): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun closest(selector: String): ShallowWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun parent(): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun <PropName : Any> renderProp(prop: PropName): (params: Parameters<Any>) -> ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open var getWrappingComponent: () -> ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun <P2 : RProps> find(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> filter(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> children(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun childAt(index: Number): ShallowWrapper<RProps, RState, Component<RProps, RState>>
    open fun <C2 : Component<P2, S2>, P2 : RProps, S2 : RState> dive(): ShallowWrapper<P2, S2, C2>
    open fun <P2 : RProps> parents(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> closest(statelessComponent: StatelessComponent<P2>): ShallowWrapper<P2, RState, Component<P2, RState>>
}

abstract external class ReactWrapper<P : RProps, S : RState, C : Component<P, S>> : CommonWrapper<P, S, C> {
    constructor(nodes: JsxElement, root: ReactWrapper<P, S, Component<P, S>>?, options: MountRendererProps?)
    constructor(nodes: Array<JsxElement>, root: ReactWrapper<P, S, Component<P, S>>?, options: MountRendererProps?)
    open fun unmount(): ReactWrapper<P, S, C> /* this */
    open fun mount(): ReactWrapper<P, S, C> /* this */
    open fun ref(refName: String): ReactWrapper<P, S, Component<P, S>>
    open fun <P2 : RProps, S2 : RState> ref(refName: String): ReactWrapper<P2, S2, Component<P2, S2>>
    open fun detach()
    open fun hostNodes(): ReactWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun <P2 : RProps> find(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> find(component: ComponentClass<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> find(component: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <C2 : Component<P2, S2>, P2 : RProps, S2: RState> find(componentClass: ComponentClass<Any>): ReactWrapper<P2, S2, C2>
    open fun find(props: EnzymePropSelector): ReactWrapper<P, S, Component<P, S>>
    open fun find(selector: String): ReactWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun findWhere(predicate: (wrapper: ReactWrapper<P, S, Component<P, S>>) -> Boolean): ReactWrapper<P, S, Component<P, S>>
    open fun <P2 : RProps> filter(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> filter(component: ComponentClass<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> filter(component: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun filter(props: EnzymePropSelector): ReactWrapper<P, S, Component<P, S>>
    open fun filter(props: String): ReactWrapper<P, S, Component<P, S>>
    open fun <P2 : RProps> children(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> children(component: ComponentClass<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> children(component: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun children(selector: String): ReactWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun children(props: EnzymePropSelector? = definedExternally /* null */): ReactWrapper<P, S, Component<P, S>>
    open fun childAt(index: Number): ReactWrapper<P, S, Component<P, S>>
    open fun <P2 : RProps, S2 : RState> childAt(index: Number): ReactWrapper<P2, S2, Component<P2, S2>>
    open fun <P2 : RProps> parents(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> parents(component: ComponentClass<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> parents(component: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun parents(selector: String): ReactWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun parents(props: EnzymePropSelector? = definedExternally /* null */): ReactWrapper<P, S, Component<P, S>>
    open fun <P2 : RProps> closest(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> closest(component: ComponentClass<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> closest(component: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun closest(props: EnzymePropSelector): ReactWrapper<P, S, Component<P, S>>
    open fun closest(selector: String): ReactWrapper<ReactHTMLAttributes<Any> /* ReactHTMLAttributes<Any> & ReactSVGAttributes<Any> */, RState, Component<ReactHTMLAttributes<Any>, RState>>
    open fun parent(): ReactWrapper<P, S, Component<P, S>>
    open var getWrappingComponent: () -> ReactWrapper<RProps, RState, Component<RProps, RState>>
    open fun ref(refName: String): ReactWrapper<P, S, Component<P, S>>
    open fun <P2 : RProps> find(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> filter(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> children(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun childAt(index: Number): ReactWrapper<P, S, Component<P, S>>
    open fun <P2 : RProps> parents(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
    open fun <P2 : RProps> closest(statelessComponent: StatelessComponent<P2>): ReactWrapper<P2, RState, Component<P2, RState>>
}

external interface ComponentDidUpdate {
    var onSetState: Boolean
    var prevContext: Boolean
}

external interface GetDerivedStateFromProps {
    var hasShouldComponentUpdateBug: Boolean
}

external interface GetChildContext {
    var calledByRenderer: Boolean
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}

external interface Lifecycles {
    var componentDidUpdate: ComponentDidUpdate?
        get() = definedExternally
        set(value) = definedExternally
    var getDerivedStateFromProps: dynamic /* GetDerivedStateFromProps | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var getChildContext: GetChildContext?
        get() = definedExternally
        set(value) = definedExternally
    var setState: Any?
        get() = definedExternally
        set(value) = definedExternally
    @nativeGetter
    operator fun get(lifecycleName: String): Any?
    @nativeSetter
    operator fun set(lifecycleName: String, value: Any)
}

external interface ShallowRendererProps {
    var disableLifecycleMethods: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var lifecycleExperimental: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var context: Any?
        get() = definedExternally
        set(value) = definedExternally
    var enableComponentDidUpdateOnSetState: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var supportPrevContextArgumentOfComponentDidUpdate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var lifecycles: Lifecycles?
        get() = definedExternally
        set(value) = definedExternally
    var wrappingComponent: dynamic /* enzyme.ComponentClass<Any> | enzyme.StatelessComponent<Any> */
        get() = definedExternally
        set(value) = definedExternally
    var wrappingComponentProps: Any?
        get() = definedExternally
        set(value) = definedExternally
    var suspenseFallback: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var adapter: EnzymeAdapter?
        get() = definedExternally
        set(value) = definedExternally
    var attachTo: Any?
        get() = definedExternally
        set(value) = definedExternally
    var hydrateIn: Any?
        get() = definedExternally
        set(value) = definedExternally
    var PROVIDER_VALUES: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MountRendererProps {
    var context: Any?
        get() = definedExternally
        set(value) = definedExternally
    var attachTo: HTMLElement?
        get() = definedExternally
        set(value) = definedExternally
    var childContextTypes: Any?
        get() = definedExternally
        set(value) = definedExternally
    var wrappingComponent: dynamic /* enzyme.ComponentClass<Any> | enzyme.StatelessComponent<Any> */
        get() = definedExternally
        set(value) = definedExternally
    var wrappingComponentProps: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external fun <C : Component<P, S>, P : RProps, S : RState> shallow(node: ReactElement<P>, options: ShallowRendererProps? = definedExternally /* null */): ShallowWrapper<P, S, C>

external fun <P : RProps> shallow(node: ReactElement<P>, options: ShallowRendererProps? = definedExternally /* null */): ShallowWrapper<P, RState, Component<P, RState>>

external fun <P : RProps, S : RState> shallow(node: ReactElement<P>, options: ShallowRendererProps? = definedExternally /* null */): ShallowWrapper<P, S, Component<P, S>>

external fun <C : Component<P, S>, P : RProps, S : RState> mount(node: ReactElement<P>, options: MountRendererProps? = definedExternally /* null */): ReactWrapper<P, S, C>

external fun <P : RProps> mount(node: ReactElement<P>, options: MountRendererProps? = definedExternally /* null */): ReactWrapper<P, RState, Component<P, RState>>

external fun <P : RProps, S : RState> mount(node: ReactElement<P>, options: MountRendererProps? = definedExternally /* null */): ReactWrapper<P, S, Component<P, S>>

external fun <P> render(node: ReactElement<P>, options: Any? = definedExternally /* null */): Cheerio

open external class EnzymeAdapter {
    open var wrapWithWrappingComponent: (node: react.ReactElement, options: ShallowRendererProps? /* = null */) -> Any
}

external interface ConfigureOptions {
    var adapter: EnzymeAdapter
    var disableLifecycleMethods: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external fun configure(options: ConfigureOptions)

external fun <C : Component<P, S>, P : RProps, S : RState> shallow(node: ReactElement<P>): ShallowWrapper<P, S, C>

external fun <C : Component<P, S>, P : RProps, S : RState> mount(node: ReactElement<P>): ReactWrapper<P, S, C>