@file:Suppress("unused")

package bootstrap

import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.RBuilder
import react.RElementBuilder
import kotlin.reflect.KClass

typealias FormControlBuilder<T> = RElementBuilder<FormControlProps<T>>

private fun <T : HTMLElement> formControlClass() = Form.Control::class.unsafeCast<KClass<Form.Control<T>>>()

fun RBuilder.selectControl(handler: FormControlBuilder<HTMLSelectElement>.() -> Unit) {
    child(formControlClass<HTMLSelectElement>()) {
        attrs.`as` = "select"
        handler()
    }
}

fun RBuilder.inputFormControl(handler: FormControlBuilder<HTMLInputElement>.() -> Unit) {
    child(formControlClass<HTMLInputElement>()) {
        attrs.`as` = "input"
        handler()
    }
}

var FormControlProps<HTMLInputElement>.onAnyChange: ((React.ChangeEvent<HTMLInputElement>) -> Unit)?
    get() = onChange
    set(value) { onChange = value; onKeyUp = value }

var <T : HTMLElement> FormControlProps<T>.onAnyChange: ((React.ChangeEvent<T>) -> Unit)?
    get() = onChange
    set(value) { onChange = value }
