package client

import client.component.visible
import client.util.emptyToNull
import client.util.toMoment
import client.util.toProviderDate
import common.*
import common.util.ID
import common.util.inContext
import net.yested.ext.pickadate.dateInput
import net.yested.core.html.*
import net.yested.core.properties.*
import net.yested.core.utils.*
import net.yested.ext.bootstrap3.*
import net.yested.ext.jquery.backToHash
import org.w3c.dom.HTMLDivElement
import kotlin.browser.window
import kotlin.dom.appendText

/**
 * UI for editing a single To-Do.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/7/16
 * Time: 6:37 AM
 */
class ToDoModel(val toDoId: Property<ID<ToDo>?>) {
    val toDoRepository = Factory.toDoRepository
    val persistedToDo = toDoId.flatMapIfNotNull { it.let { toDoRepository.findProperty(it) } }
    val name = persistedToDo.mapAsDefault { it?.name ?: "" }
    val validation = name.validate("Description is mandatory", { it.length > 0})
    val dueDate = persistedToDo.mapAsDefault { it?.dueDate?.toMoment() }
    val notes = persistedToDo.mapAsDefault { it?.note ?: "" }
    val backHash = ToDosModel.toUrl().toProperty()

    fun save(): Boolean {
        if (validation.get().success) {
            val updatedToDo = ToDo(name.get(), dueDate.get()?.toProviderDate(), notes.get().emptyToNull(), id = toDoId.get())
            val newId = toDoRepository.save(updatedToDo)
            toDoId.set(newId)
            window.history.backToHash(backHash.get())
            return true
        } else {
            return false
        }
    }

    fun cancel() {
        toDoId.set(null)
        window.history.backToHash(backHash.get())
    }

    fun delete() {
        toDoId.get()?.let { toDoId ->
            toDoRepository.remove(toDoId)
        }
        window.history.backToHash(ToDosModel.toUrl())
    }

    fun setFromHash(hash: Array<String>) {
        val toDoId: ID<ToDo>? = if (hash.size > 1) ID(hash[1]) else null
        this.toDoId.set(toDoId)
    }

    companion object {
        fun toUrl(toDoId: ID<ToDo>?): String = "#toDo" + if (toDoId != null) "/" + toDoId else ""
    }
}

fun toDoScreen(model: ToDoModel): HTMLDivElement {
    return Div {
        inContext("buttonBar") { buttonBar(model.backHash, "To-Do".toProperty()) }
        btsFormHorizontal(labelWidth = Col.Width.Sm(4), inputWidth = Col.Width.Sm(8)) {
            btsFormItemSimple(state = model.validation.map { it.toState() }, label = "To-Do") {
                textInput(model.name) { placeholder = "To-Do"; size = 40 }
            }
            btsFormItemSimple(state = Property(State.Default), label = "Due Date") {
                dateInput(model.dueDate, placeholder = "(none)", formatter = { month.twoDigits + "/" + dayOfMonth.twoDigits + "/" + year.fourDigits })
            }
            btsFormItemSimple(state = Property(State.Default), label = "Notes") {
                textInput(model.notes) { placeholder = "notes"; size = 60 }
            }
            btsFormItemSimple(state = Property(State.Default), label = "") {
                addClass2("btn-toolbar")
                btsButton(look = ButtonLook.Primary, onclick = { model.save() }) { appendText("Save") }
                btsButton(onclick = { model.cancel() }) { appendText("Cancel") }
                nbsp(4)
                btsButton(onclick = { model.delete() }, look = ButtonLook.Danger) {
                    model.toDoId.onNext { visible = it != null }
                    appendText("Delete ToDo")
                }
            }
        }
    }
}
