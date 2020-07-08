package component

import bootstrap.Button
import component.entity.Closeable
import component.entity.UndoProvider
import platform.launchHandlingErrors
import react.*

/**
 * Support for undoing user actions.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 12:03 AM
 */
object UndoComponent : UndoProvider {
    private var commandRecorder: CommandRecorder = NormalCommandRecorder

    /** New commands are added at the end. */
    private val undoCommands: MutableList<Command> = mutableListOf()

    /** New commands are added at the end. */
    private val redoCommands: MutableList<Command> = mutableListOf()

    val undoCount: Int get() = undoCommands.size
    val redoCount: Int get() = redoCommands.size

    private val listeners = mutableListOf<() -> Unit>()

    fun addListener(listener: () -> Unit): Closeable {
        listeners.add(listener)
        return Closeable { listeners.remove(listener) }
    }

    suspend fun undo() {
        notUndoable {
            val undoCommandsCopy = undoCommands
            val index = undoCommandsCopy.size - 1
            val commandToUndo = undoCommandsCopy[index]
            val redoCommand = commandToUndo.executeAndGetOpposite()
            console.info("$commandToUndo (undoing: $commandToUndo)")
            undoCommands.removeAt(index)
            redoCommands.add(redoCommand)
            listeners.forEach { it.invoke() }
        }
    }

    suspend fun redo() {
        notUndoable {
            val redoCommandsCopy = redoCommands
            val index = redoCommandsCopy.size - 1
            val commandToRedo = redoCommandsCopy[index]
            val undoCommand = commandToRedo.executeAndGetOpposite()
            console.info("$commandToRedo (redo)")
            redoCommands.removeAt(index)
            undoCommands.add(undoCommand)
            listeners.forEach { it.invoke() }
        }
    }

    /**
     * Group a series of Repository actions into a single UndoableGroup.
     * Without doing this, each repository action (save or remove)
     * will each be individually undoable.
     */
    override suspend fun <T> undoable(
        pastTenseDescription: String,
        undoPastTenseDescription: String,
        function: suspend () -> T
    ): T {
        if (commandRecorder == NormalCommandRecorder) {
            val undoableGroup = UndoableGroup(pastTenseDescription, undoPastTenseDescription)
            commandRecorder = undoableGroup
            try {
                val result = function()
                if (!undoableGroup.isEmpty()) {
                    addTopLevelUndoCommand(undoableGroup)
                }
                return result
            } finally {
                commandRecorder = NormalCommandRecorder
            }
        } else {
            // there is already a special commandRecorder, so just run the function
            return function()
        }
    }

    override suspend fun <T> notUndoable(function: suspend () -> T): T {
        val originalRecorder = commandRecorder
        commandRecorder = NoOpCommandRecorder
        try {
            return function()
        } finally {
            commandRecorder = originalRecorder
        }
    }

    fun addUndoCommand(undoCommand: Command) {
        commandRecorder.addUndoCommandIfAppropriate(undoCommand)
    }

    private fun addTopLevelUndoCommand(undoCommand: Command) {
        redoCommands.clear()
        undoCommands.add(undoCommand)
        listeners.forEach { it.invoke() }
    }

    private object NormalCommandRecorder : CommandRecorder {
        override fun addUndoCommandIfAppropriate(undoCommand: Command) {
            addTopLevelUndoCommand(undoCommand)
        }
    }
}

abstract class Command(private val pastTenseDescription: String) {
    abstract suspend fun executeAndGetOpposite(): Command

    override fun toString(): String = pastTenseDescription
}

private interface CommandRecorder {
    fun addUndoCommandIfAppropriate(undoCommand: Command)
}

private object NoOpCommandRecorder : CommandRecorder {
    override fun addUndoCommandIfAppropriate(undoCommand: Command) {
        //do nothing
    }
}

/** A group of commands that are undone as a single unit. */
private class UndoableGroup(private val redoPastTenseDescription: String, undoPastTenseDescription: String) : Command(undoPastTenseDescription), CommandRecorder {
    private val undoCommands: MutableList<Command> = mutableListOf()

    override fun addUndoCommandIfAppropriate(undoCommand: Command) {
        undoCommands.add(0, undoCommand)
        if (undoCommands.size > 100) {
            console.warn("undoGroupSize=${undoCommands.size}")
        }
    }

    fun isEmpty(): Boolean = undoCommands.isEmpty()

    /** Run all undoCommands and return the redo. */
    override suspend fun executeAndGetOpposite(): Command {
        val redoCommands = undoCommands.map { it.executeAndGetOpposite() }.reversed()
        val undoCommand = this
        return object : Command(redoPastTenseDescription) {
            override suspend fun executeAndGetOpposite(): Command {
                redoCommands.forEach { it.executeAndGetOpposite() }
                return undoCommand
            }
        }
    }
}

external interface UndoProps : RProps

external interface UndoState : RState {
    var showUndoButton: Boolean?
}

class UndoButtons(props: UndoProps) : RComponent<UndoProps, UndoState>(props) {
    private val resources = mutableListOf<Closeable>()

    override fun UndoState.init(props: UndoProps) {
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