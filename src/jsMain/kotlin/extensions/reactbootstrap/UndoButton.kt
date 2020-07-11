package extensions.reactbootstrap

import util.undo.UndoComponent
import util.Closeable
import bootstrap.Button
import util.launchHandlingErrors
import react.*

external interface UndoButtonProps : RProps

external interface UndoButtonState : RState {
    var showUndoButton: Boolean?
}

class UndoButton(props: UndoButtonProps) : RComponent<UndoButtonProps, UndoButtonState>(props) {
    private val resources = mutableListOf<Closeable>()

    override fun UndoButtonState.init(props: UndoButtonProps) {
        showUndoButton = UndoComponent.undoCount > 0
    }

    override fun componentDidMount() {
        resources.add(UndoComponent.addListener {
            setState {
                showUndoButton = UndoComponent.undoCount > 0
            }
        })
    }

    override fun componentWillUnmount() {
        resources.forEach { it.close() }
        resources.clear()
    }

    override fun RBuilder.render() {
        if (state.showUndoButton == true) {
            child(Button::class) {
                attrs.variant = "secondary"
                attrs.onClick = {
                    it.stopPropagation()
                    launchHandlingErrors("Undo") {
                        UndoComponent.undo()
                    }
                }
                +"Undo"
            }
        }
    }
}