package todo

import bootstrap.*
import extensions.firebase.reactbootstrap.ButtonBar
import extensions.reactbootstrap.textInput
import extensions.pouchdb.get
import extensions.pouchdb.removeAllowingUndo
import extensions.pouchdb.saveAllowingUndo
import util.toProviderDate
import react.*
import react.router.dom.RouteResultHistory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import util.launchHandlingErrors
import util.toJsDate
import react.dom.div
import util.ID
import util.emptyToNull
import kotlin.js.Date

interface ToDoProps : RProps {
    var id: ID<ToDo>?
    var history: RouteResultHistory
}

interface ToDoState : RState {
    var loading: Boolean
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
        loading = true
    }

    override fun componentDidMount() {
        GlobalScope.launch {
            val entity = props.id?.let { Config.toDoDb.get(it).await().toNormal() }
            setToDo(entity)
        }
    }

    fun setToDo(toDo: ToDo?) {
        setState {
            original = toDo
            name = original?.name ?: ""
            dueDate = original?.dueDate?.toJsDate()
            notes = original?.notes ?: ""
            validated = false
            loading = false
        }
    }

    private fun save(event: React.SubmitEvent) {
        if (event.currentTarget?.checkValidity() == true && state.name.isNotBlank()) {
            val updatedToDo = ToDo(
                name = state.name,
                dueDate = state.dueDate?.toProviderDate(),
                notes = state.notes.emptyToNull(),
                id = state.original?.id,
                rev = state.original?.rev
            )
            launchHandlingErrors("save $updatedToDo") {
                Config.toDoDb.saveAllowingUndo(state.original, updatedToDo, ToDoJS::toNormal)
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
        state.original?.let { toDo ->
            launchHandlingErrors("delete ${toDo.id}") {
                Config.toDoDb.removeAllowingUndo(toDo, ToDoJS::toNormal)
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
            if (state.loading) {
                div { +"Loading..." }
            } else {
                child(Form::class) {
                    attrs.onSubmit = { save(it); it.preventDefault() }
                    attrs.validated = state.validated
                    attrs.onSubmit = { event -> save(event); event.preventDefault() }
                    textInput("To Do", state.name, "(some description)", required = true) {
                        setState { name = it ?: "" }
                    }
                    textInput("Notes", state.notes, "(some notes)") {
                        setState { notes = it ?: "" }
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
}

fun RBuilder.toDoScreen(id: ID<ToDo>?, history: RouteResultHistory): ReactElement = child(ToDoScreen::class) {
    attrs.id = id
    attrs.history = history
}
