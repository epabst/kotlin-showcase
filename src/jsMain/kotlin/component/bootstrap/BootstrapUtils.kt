@file:Suppress("unused")

package component.bootstrap

import bootstrap.*
import component.flaticon
import org.w3c.dom.HTMLButtonElement
import platform.formatCurrencyForInput
import react.RBuilder
import react.RElementBuilder
import react.ReactElement
import react.dom.option
import util.emptyToNull
import util.fromNaNtoNull
import kotlin.js.Date

private var nextControlId: Int = 10

fun RElementBuilder<RowProps>.column(
    xs: Int?,
    className: String? = null,
    block: RElementBuilder<ColProps>.() -> Unit
) {
    child(Col::class) {
        xs?.let { attrs.xs = xs }
        className?.let { attrs.className = it }
        block()
    }
}

fun RBuilder.textInput(
    label: String,
    value: String?,
    placeholder: String = "",
    required: Boolean = false,
    inputDescription: String = placeholder,
    type: String? = null,
    pattern: String? = null,
    autoComplete: String? = null,
    onValueChanged: (String?) -> Unit
) {
    child(Form.Group::class) {
        attrs.controlId = "formControl${nextControlId++}"
        child(Form.Label::class) { +label }
        child(InputGroup::class) {
            inputFormControl {
                attrs.value = value ?: ""
                attrs.type = type
                attrs.autoComplete = autoComplete
                attrs.pattern = pattern
                attrs.required = required
                attrs.placeholder = placeholder
                attrs.onAnyChange = { onValueChanged(it.target?.value?.emptyToNull()) }
            }
            child(Form.Control.Feedback::class) {
                attrs.type = "invalid"
                +"Please specify $inputDescription."
            }
        }
    }
}

fun RBuilder.telInput(
    label: String,
    value: String?,
    placeholder: String = "",
    required: Boolean = false,
    inputDescription: String = placeholder,
    onValueChanged: (String?) -> Unit
) {
    textInput(
        label = label,
        value = value,
        type = "tel",
        pattern = """^(?:(?:\+?1\s*(?:[.-]\s*)?)?(?:\(\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\s*\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\s*(?:[.-]\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\s*(?:[.-]\s*)?([0-9]{4})(?:\s*(?:#|x\.?|ext\.?|extension)\s*(\d+))?${'$'}""",
        autoComplete = "tel tel-national",
        placeholder = placeholder,
        inputDescription = inputDescription,
        required = required,
        onValueChanged = onValueChanged
    )
}

fun RBuilder.intInput(label: String, value: Int?, min: Int? = null, required: Boolean = false, onValueChanged: (Int?) -> Unit) {
    child(Form.Group::class) {
        attrs.controlId = "quantity"
        child(Form.Label::class) { +label }
        child(InputGroup::class) {
            inputFormControl {
                attrs.defaultValue = value?.toString() ?: ""
                attrs.type = "number"
                attrs.inputMode = "numeric"
                min?.let { attrs.min = it.toString() }
                attrs.required = required
                attrs.onAnyChange = { onValueChanged(it.target?.value?.toIntOrNull()) }
            }
            child(Form.Control.Feedback::class) {
                attrs.type = "invalid"
                +"Please specify"
            }
        }
    }
}

fun RBuilder.currencyInput(label: String, value: Double?, onAnyChange: (Double?) -> Unit) {
    child(Form.Group::class) {
        attrs.controlId = "formControl${nextControlId++}"
        child(Form.Label::class) { +label }
        child(InputGroup::class) {
            inputFormControl {
                attrs.defaultValue = value?.formatCurrencyForInput() ?: ""
                attrs.placeholder = ""
                attrs.type = "number"
                attrs.inputMode = "numeric"
                attrs.onAnyChange = { onAnyChange(it.target?.valueAsNumber) }
            }
            child(Form.Control.Feedback::class) {
                attrs.type = "invalid"
            }
        }
    }
}

fun RBuilder.dateInput(
    label: String,
    value: String?,
    placeholder: String = "",
    required: Boolean = false,
    inputDescription: String = placeholder,
    onValueChanged: (Date?) -> Unit
) {
    textInput(
        label = label,
        value = value,
        type = "date",
        placeholder = placeholder,
        inputDescription = inputDescription,
        required = required,
        onValueChanged = { textOrNull ->
            val date = textOrNull.emptyToNull()?.let { text ->
                Date.parse(text).fromNaNtoNull()?.let { Date(it) }
            }
            onValueChanged(date)
        }
    )
}

fun <T> RBuilder.singleSelectInput(label: String?,
                                   value: T?,
                                   inputDescription: String,
                                   options: List<T>,
                                   required: Boolean = false,
                                   toOptionLabel: (T) -> String? = { it.toString() },
                                   toOptionValue: (T) -> String?,
                                   onAnyChange: (T?) -> Unit) {
    child(Form.Group::class) {
        attrs.controlId = "formControl${nextControlId++}"
        if (label != null) {
            child(Form.Label::class) { +label }
        }
        child(InputGroup::class) {
            selectControl {
                attrs.value = value?.let { toOptionValue(it) } ?: ""
                attrs.required = required
                attrs.onAnyChange = { event ->
                    onAnyChange.invoke(event.target?.selectedIndex?.let { options[it] })
                }
                options.forEach { option ->
                    option {
                        attrs.label = toOptionLabel(option) ?: ""
                        attrs.value = toOptionValue(option) ?: ""
                    }
                }
            }
            child(Form.Control.Feedback::class) {
                attrs.type = "invalid"
                +"Please choose $inputDescription."
            }
        }
    }
}

fun RBuilder.textButton(text: String,
                        variant: String = "secondary",
                        size: String? = null,
                        className: String? = null,
                        disabled: Boolean = false,
                        onClick: (React.ClickEvent<HTMLButtonElement>) -> Unit) {
    child(Button::class) {
        +text
        attrs.variant = variant
        attrs.size = size
        attrs.className = className
        attrs.disabled = disabled
        attrs.onClick = onClick
    }
}

fun RBuilder.flaticonButton(flaticon: String,
                            text: String,
                            variant: String = "secondary",
                            className: String? = null,
                            disabled: Boolean = false,
                            onClick: (React.ClickEvent<HTMLButtonElement>) -> Unit) {
    child(Button::class) {
        attrs.variant = variant
        attrs.className = className
        attrs.disabled = disabled
        attrs.onClick = onClick
        flaticon(flaticon)
        +text
    }
}

enum class NavbarPlacement {
    Top, Bottom
}

/** A navbar that avoids covering up later content by duplicating the navbar content behind it as well. */
fun RBuilder.navbar(fixed: NavbarPlacement? = null, handler: RElementBuilder<NavbarProps>.() -> Unit): ReactElement {
    val navbar = child(Navbar::class) {
        attrs.fixed = fixed?.name?.toLowerCase()
        handler()
    }
    if (fixed == NavbarPlacement.Top) {
        child(Navbar::class) {
            attrs.className = (attrs.className ?: "") + " invisible"
            // take up the same much space for the following content
            handler()
        }
    }
    return navbar
}
