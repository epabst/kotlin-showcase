package todo

import bootstrap.*
import component.ButtonBar
import platform.toDate
import platform.toProviderDate
import react.*
import react.router.dom.RouteResultHistory
import component.repository.ID
import platform.launchHandlingErrors
import todo.model.Factory
import todo.model.ToDo
import util.emptyToNull
import kotlin.js.Date

interface ToDoProps : RProps {
    var id: ID<ToDo>?
    var history: RouteResultHistory
}

interface ToDoState : RState {
    var original: ToDo?
    var name: String
    var dueDate: Date?
    var notes: String
    var validated: Boolean
}

/**
 * UI for showing a list of ToDos.
 * @author Eric Pabst (epabst@gmail.com)
 */
class ToDoScreen(props: ToDoProps) : RComponent<ToDoProps, ToDoState>(props) {

    override fun ToDoState.init(props: ToDoProps) {
        original = props.id?.let { Factory.toDoRepository.find(it) }
        name = original?.name ?: ""
        dueDate = original?.dueDate?.toDate()
        notes = original?.notes ?: ""
        validated = false
    }

    private fun save(event: React.SubmitEvent) {
        if (event.currentTarget?.checkValidity() == true && state.name.isNotBlank()) {
            val updatedToDo = ToDo(state.name, state.dueDate?.toProviderDate(), state.notes.emptyToNull(), id = state.original?.id)
            launchHandlingErrors("save $updatedToDo") {
                Factory.toDoRepository.save(updatedToDo)
            }
            props.history.goBack()
        }
        setState {
            validated = true
        }
    }

    private fun cancel() {
        props.history.goBack()
    }

    private fun delete() {
        state.original?.id?.let { toDoId ->
            launchHandlingErrors("delete $toDoId") {
                Factory.toDoRepository.remove(toDoId)
            }
        }
        props.history.goBack()
    }

    override fun RBuilder.render() {
        child(ButtonBar::class) {
            attrs.history = props.history
            attrs.heading = "To-Do"
        }
        child(Container::class) {
            child(Form::class) {
                attrs.onSubmit = { save(it); it.preventDefault() }
                attrs.validated = state.validated
                attrs.onSubmit = { event -> save(event); event.preventDefault() }
                child(Form.Group::class) {
                    attrs.controlId = "name"
                    child(Form.Label::class) { +"Description" }
                    child(InputGroup::class) {
                        textInputControl {
                            attrs.value = state.name
                            attrs.required = true
                            attrs.placeholder = "(some description)"
                            attrs.size = 40
                            attrs.onAnyChange = { it.target?.let { setState { name = it.value } } }
                            attrs.required = true
                        }
                        child(Form.Control.Feedback::class) {
                            attrs.type = "invalid"
                            +"Please Specify a Name"
                        }
                    }
                }
                child(Form.Group::class) {
                    attrs.controlId = "note"
                    child(Form.Label::class) { +"Notes" }
                    textInputControl {
                        attrs.value = state.notes
                        attrs.placeholder = "(some notes)"
                        attrs.size = 60
                        attrs.onAnyChange = { it.target?.let { setState { notes = it.value } } }
                    }
                }
                child(Form.Group::class) {
                    child(ButtonToolbar::class) {
                        child(Button::class) {
                            attrs.type = "submit"
                            attrs.variant = "primary"
                            +"Save"
                        }
                        child(Button::class) {
                            attrs.variant = "secondary"
                            attrs.className = "mr-2"
                            attrs.onClick = { cancel() }
                            +"Cancel"
                        }
                        if (state.original?.id != null) {
                            child(Button::class) {
                                attrs.variant = "danger"
                                attrs.onClick = { delete() }
                                +"Delete To-Do"
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.toDoScreen(id: ID<ToDo>?, history: RouteResultHistory): ReactElement = child(ToDoScreen::class) {
    attrs.id = id
    attrs.history = history
}
