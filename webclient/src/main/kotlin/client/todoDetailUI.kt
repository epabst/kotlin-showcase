package client

import client.component.visible
import client.util.toRichDate
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
import kotlin.dom.addClass
import kotlin.dom.appendText

/**
 * UI for editing a single To-Do.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/7/16
 * Time: 6:37 AM
 */
class ToDoDetailModel(val toDoId: Property<ID<ToDo>?>) {
    val toDo = toDoId.mapAsDefault { it?.let { Factory.toDoRepository.find(it) } }
    val toDoRepository = Factory.toDoRepository
    val name = toDo.mapAsDefault { it?.name ?: "" }
    val validation = name.validate("Description is mandatory", { it.size > 0})
    val dueDate = toDo.mapAsDefault { it?.dueDate?.toMoment() }
    val notes = toDo.mapAsDefault { it?.note ?: "" }
    val backHash = ToDoMasterModel.toUrl().toProperty()

    fun save(): Boolean {
        if (validation.get().success) {
            val updatedToDo = ToDo(name.get(), dueDate.get()?.toRichDate(), notes.get(), id = toDo.get()?.id)
            val newId = toDoRepository.save(toDo.get(), updatedToDo)
            toDoId.set(newId)
            window.history.backToHash(backHash.get())
            return true
        } else {
            return false
        }
    }

    fun cancel() {
        toDo.set(null)
        window.history.backToHash(backHash.get())
    }

    fun delete() {
        toDo.get()?.let { toDo ->
            toDoRepository.remove(toDo)
        }
        window.history.backToHash(ToDoMasterModel.toUrl())
    }

    companion object {
        fun toUrl(toDoId: ID<ToDo>?): String = "#toDo" + if (toDoId != null) "_" + toDoId else ""
    }
}

fun toDoDetailScreen(model: ToDoDetailModel): HTMLDivElement {
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
                addClass("btn-toolbar")
                btsButton(look = ButtonLook.Primary, onclick = { model.save() }) { appendText("Save") }
                btsButton(onclick = { model.cancel() }) { appendText("Cancel") }
                nbsp(4)
                btsButton(onclick = { model.delete() }, look = ButtonLook.Danger) {
                    model.toDo.onNext { visible = it?.id != null }
                    appendText("Delete ToDo")
                }
            }
        }
    }
}
