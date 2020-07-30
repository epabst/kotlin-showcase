// Generated from @types/cheerio version 0.22.21
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION",
    "unused",
    "DEPRECATION",
    "PropertyName"
)

package cheerio

import kotlin.js.*
import kotlin.js.Json
import org.w3c.dom.*

typealias AttrFunction = (el: CheerioElement, i: Number, currentValue: String) -> Any

external interface CheerioAttrs {
    @nativeGetter
    operator fun get(attr: String): String?
    @nativeSetter
    operator fun set(attr: String, value: String)
}

external interface SerializeArrayElement {
    var name: String
    var value: String
}

external interface Cheerio {
    @nativeGetter
    operator fun get(index: Number): CheerioElement?
    @nativeSetter
    operator fun set(index: Number, value: CheerioElement)
    var cheerio: String
    var length: Number
    fun attr(): CheerioAttrs
    fun attr(name: String): String?
    fun attr(name: String, value: AttrFunction): Cheerio
    fun attr(name: String, value: String): Cheerio
    fun attr(map: Json): Cheerio
    fun data(): Any
    fun data(name: String): Any
    fun data(name: String, value: Any): Any
    fun `val`(): String
    fun `val`(value: String): Cheerio
    fun removeAttr(name: String): Cheerio
    fun has(selector: String): Cheerio
    fun has(element: CheerioElement): Cheerio
    fun hasClass(className: String): Boolean
    fun addClass(classNames: String): Cheerio
    fun removeClass(): Cheerio
    fun removeClass(className: String): Cheerio
    fun removeClass(func: (index: Number, className: String) -> String): Cheerio
    fun toggleClass(className: String): Cheerio
    fun toggleClass(className: String, toggleSwitch: Boolean): Cheerio
    fun toggleClass(toggleSwitch: Boolean? = definedExternally /* null */): Cheerio
    fun toggleClass(func: (index: Number, className: String, toggleSwitch: Boolean) -> String, toggleSwitch: Boolean? = definedExternally /* null */): Cheerio
    fun `is`(selector: String): Boolean
    fun `is`(element: CheerioElement): Boolean
    fun `is`(element: Array<CheerioElement>): Boolean
    fun `is`(selection: Cheerio): Boolean
    fun `is`(func: (index: Number, element: CheerioElement) -> Boolean): Boolean
    fun serialize(): String
    fun serializeArray(): Array<SerializeArrayElement>
    fun find(selector: String): Cheerio
    fun find(element: Cheerio): Cheerio
    fun parent(selector: String? = definedExternally /* null */): Cheerio
    fun parents(selector: String? = definedExternally /* null */): Cheerio
    fun parentsUntil(selector: String? = definedExternally /* null */, filter: String? = definedExternally /* null */): Cheerio
    fun parentsUntil(element: CheerioElement, filter: String? = definedExternally /* null */): Cheerio
    fun parentsUntil(element: Cheerio, filter: String? = definedExternally /* null */): Cheerio
    fun prop(name: String): Any
    fun prop(name: String, value: Any): Cheerio
    fun closest(): Cheerio
    fun closest(selector: String): Cheerio
    fun next(selector: String? = definedExternally /* null */): Cheerio
    fun nextAll(): Cheerio
    fun nextAll(selector: String): Cheerio
    fun nextUntil(selector: String? = definedExternally /* null */, filter: String? = definedExternally /* null */): Cheerio
    fun nextUntil(element: CheerioElement, filter: String? = definedExternally /* null */): Cheerio
    fun nextUntil(element: Cheerio, filter: String? = definedExternally /* null */): Cheerio
    fun prev(selector: String? = definedExternally /* null */): Cheerio
    fun prevAll(): Cheerio
    fun prevAll(selector: String): Cheerio
    fun prevUntil(selector: String? = definedExternally /* null */, filter: String? = definedExternally /* null */): Cheerio
    fun prevUntil(element: CheerioElement, filter: String? = definedExternally /* null */): Cheerio
    fun prevUntil(element: Cheerio, filter: String? = definedExternally /* null */): Cheerio
    fun slice(start: Number, end: Number? = definedExternally /* null */): Cheerio
    fun siblings(selector: String? = definedExternally /* null */): Cheerio
    fun children(selector: String? = definedExternally /* null */): Cheerio
    fun contents(): Cheerio
    fun each(func: (index: Number, element: CheerioElement) -> Any): Cheerio
    fun map(func: (index: Number, element: CheerioElement) -> Any): Cheerio
    fun filter(selector: String): Cheerio
    fun filter(selection: Cheerio): Cheerio
    fun filter(element: CheerioElement): Cheerio
    fun filter(elements: Array<CheerioElement>): Cheerio
    fun filter(func: (index: Number, element: CheerioElement) -> Boolean): Cheerio
    fun not(selector: String): Cheerio
    fun not(selection: Cheerio): Cheerio
    fun not(element: CheerioElement): Cheerio
    fun not(func: (index: Number, element: CheerioElement) -> Boolean): Cheerio
    fun first(): Cheerio
    fun last(): Cheerio
    fun eq(index: Number): Cheerio
    fun get(): Array<Any>
    fun get(index: Number): Any
    fun index(): Number
    fun index(selector: String): Number
    fun index(selection: Cheerio): Number
    fun end(): Cheerio
    fun add(selectorOrHtml: String): Cheerio
    fun add(selector: String, context: Document): Cheerio
    fun add(element: CheerioElement): Cheerio
    fun add(elements: Array<CheerioElement>): Cheerio
    fun add(selection: Cheerio): Cheerio
    fun addBack(): Cheerio
    fun addBack(filter: String): Cheerio
    fun appendTo(target: Cheerio): Cheerio
    fun prependTo(target: Cheerio): Cheerio
    fun append(content: String, vararg contents: Any): Cheerio
    fun append(content: Document, vararg contents: Any): Cheerio
    fun append(content: Array<Document>, vararg contents: Any): Cheerio
    fun append(content: Cheerio, vararg contents: Any): Cheerio
    fun prepend(content: String, vararg contents: Any): Cheerio
    fun prepend(content: Document, vararg contents: Any): Cheerio
    fun prepend(content: Array<Document>, vararg contents: Any): Cheerio
    fun prepend(content: Cheerio, vararg contents: Any): Cheerio
    fun after(content: String, vararg contents: Any): Cheerio
    fun after(content: Document, vararg contents: Any): Cheerio
    fun after(content: Array<Document>, vararg contents: Any): Cheerio
    fun after(content: Cheerio, vararg contents: Any): Cheerio
    fun insertAfter(content: String): Cheerio
    fun insertAfter(content: Document): Cheerio
    fun insertAfter(content: Cheerio): Cheerio
    fun before(content: String, vararg contents: Any): Cheerio
    fun before(content: Document, vararg contents: Any): Cheerio
    fun before(content: Array<Document>, vararg contents: Any): Cheerio
    fun before(content: Cheerio, vararg contents: Any): Cheerio
    fun insertBefore(content: String): Cheerio
    fun insertBefore(content: Document): Cheerio
    fun insertBefore(content: Cheerio): Cheerio
    fun remove(selector: String? = definedExternally /* null */): Cheerio
    fun replaceWith(content: String): Cheerio
    fun replaceWith(content: CheerioElement): Cheerio
    fun replaceWith(content: Array<CheerioElement>): Cheerio
    fun replaceWith(content: Cheerio): Cheerio
    fun replaceWith(content: () -> Cheerio): Cheerio
    fun empty(): Cheerio
    fun html(): String?
    fun html(html: String): Cheerio
    fun text(): String
    fun text(text: String): Cheerio
    fun wrap(content: String): Cheerio
    fun wrap(content: Document): Cheerio
    fun wrap(content: Cheerio): Cheerio
    fun css(propertyName: String): String
    fun css(propertyNames: Array<String>): Array<String>
    fun css(propertyName: String, value: String): Cheerio
    fun css(propertyName: String, value: Number): Cheerio
    fun css(propertyName: String, func: (index: Number, value: String) -> String): Cheerio
    fun css(propertyName: String, func: (index: Number, value: String) -> Number): Cheerio
    fun css(properties: Any): Cheerio
    fun clone(): Cheerio
    fun toArray(): Array<CheerioElement>
    @nativeGetter
    operator fun get(index: Number): CheerioElement?
}

external interface CheerioOptionsInterface {
    var xmlMode: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var decodeEntities: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var lowerCaseTags: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var lowerCaseAttributeNames: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var recognizeCDATA: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var recognizeSelfClosing: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var normalizeWhitespace: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var withStartIndices: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var withEndIndices: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var ignoreWhitespace: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var _useHtmlParser2: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CheerioSelector {
    @nativeInvoke
    operator fun invoke(selector: String): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: String): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: CheerioElement): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: Array<CheerioElement>): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: Cheerio): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: String, root: String): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: CheerioElement, root: String): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: Array<CheerioElement>, root: String): Cheerio
    @nativeInvoke
    operator fun invoke(selector: String, context: Cheerio, root: String): Cheerio
    @nativeInvoke
    operator fun invoke(selector: Any): Cheerio
}

external interface CheerioStatic : CheerioSelector {
    fun root(): Cheerio
    fun contains(container: CheerioElement, contained: CheerioElement): Boolean
    fun parseHTML(data: String, context: Document? = definedExternally /* null */, keepScripts: Boolean? = definedExternally /* null */): Array<Document>
    fun html(options: CheerioOptionsInterface? = definedExternally /* null */): String
    fun html(dom: String, options: CheerioOptionsInterface? = definedExternally /* null */): String
    fun html(dom: Cheerio, options: CheerioOptionsInterface? = definedExternally /* null */): String
    fun html(dom: CheerioElement, options: CheerioOptionsInterface? = definedExternally /* null */): String
    fun xml(dom: String? = definedExternally /* null */): String
    fun xml(dom: Cheerio? = definedExternally /* null */): String
    fun xml(dom: CheerioElement? = definedExternally /* null */): String
    fun xml(): String
}

external interface CheerioElement {
    var tagName: String
    var type: String
    var name: String
    var attribs: CheerioAttrs
    var children: Array<CheerioElement>
    var childNodes: Array<CheerioElement>
    var lastChild: CheerioElement
    var firstChild: CheerioElement
    var next: CheerioElement
    var nextSibling: CheerioElement
    var prev: CheerioElement
    var previousSibling: CheerioElement
    var parent: CheerioElement
    var parentNode: CheerioElement
    var nodeValue: String
    var data: String?
        get() = definedExternally
        set(value) = definedExternally
    var startIndex: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CheerioAPI : CheerioSelector, CheerioStatic {
    fun load(html: String, options: CheerioOptionsInterface? = definedExternally /* null */): CheerioStatic
//    fun load(html: Buffer, options: cheerio.CheerioOptionsInterface? = definedExternally /* null */): cheerio.CheerioStatic
    fun load(element: CheerioElement, options: CheerioOptionsInterface? = definedExternally /* null */): CheerioStatic
}