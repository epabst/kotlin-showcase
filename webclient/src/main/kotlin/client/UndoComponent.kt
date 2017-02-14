package client

import client.component.flaticon
import common.Repository
import common.RepositoryListener
import common.WithID
import net.yested.core.properties.*
import net.yested.core.html.*
import net.yested.core.utils.*
import net.yested.ext.bootstrap3.*
import org.w3c.dom.HTMLElement
import kotlin.dom.appendText

/**
 * Support for undoing user actions.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 12:03 AM
 */
object UndoComponent {
    private var commandRecorder: CommandRecorder = NormalCommandRecorder
    /** New commands are added at the end, calling [Property.set] each time. */
    private val undoCommands: Property<List<Command>> = emptyList<Command>().toProperty()
    /** New commands are added at the end. */
    private val redoCommands: Property<List<Command>> = emptyList<Command>().toProperty()

    val undoCount: ReadOnlyProperty<Int> = undoCommands.map { it.size }
    val redoCount: ReadOnlyProperty<Int> = redoCommands.map { it.size }

    fun undo() {
        commandRecorder = NoOpCommandRecorder
        try {
            val undoCommandsCopy = undoCommands.get()
            val index = undoCommandsCopy.size - 1
            val commandToUndo = undoCommandsCopy[index]
            val redoCommand = commandToUndo.executeAndGetOpposite()
            console.info("$commandToUndo (undoing: $commandToUndo)")
            undoCommands.removeAt(index)
            redoCommands.add(redoCommand)
        } finally {
            commandRecorder = NormalCommandRecorder
        }
    }

    fun redo() {
        commandRecorder = NoOpCommandRecorder
        try {
            val redoCommandsCopy = redoCommands.get()
            val index = redoCommandsCopy.size - 1
            val commandToRedo = redoCommandsCopy[index]
            val undoCommand = commandToRedo.executeAndGetOpposite()
            console.info("$commandToRedo (redo)")
            redoCommands.removeAt(index)
            undoCommands.add(undoCommand)
        } finally {
            commandRecorder = NormalCommandRecorder
        }
    }

    internal fun render(element: HTMLElement, showUndo: ReadOnlyProperty<Boolean>) {
        element with {
            div { id = "undoBar"; className = ButtonGroupSize.Small.toString()
                showUndo.onNext { visible = it }
                undoCount.zip(redoCount, showUndo).onNext { triple ->
                    val (undoCnt, redoCnt, shwUndo) = triple
                    visible = shwUndo && (undoCnt != 0 || redoCnt != 0)
                }
                btsButton(size = ButtonSize.Small, look = ButtonLook.Default, onclick = { undo() }) {
                    undoCount.onNext { disabled = it == 0 }
                    appendText("Undo")
                }
                nbsp()
                span {
                    className = "commandDescription"
                    undoCommands.onNext { commands ->
                        val undoCommand = commands.lastOrNull()
                        setContent(undoCommand?.pastTenseDescription ?: "")
                    }
                }
                nbsp()
                btsButton(size = ButtonSize.Small,
                        look = ButtonLook.Default, onclick = { redo() }) {
                    redoCount.onNext { disabled = it == 0 }
                    appendText("Redo")
                }
            }
        }
    }

    /**
     * Group a series of Repository actions into a single UndoableGroup.
     * Without doing this, each repository action (save or remove)
     * will each be individually undoable.
     */
    fun undoable(pastTenseDescription: String, function: () -> Unit?) {
        if (commandRecorder == NormalCommandRecorder) {
            val undoableGroup = UndoableGroup(pastTenseDescription)
            commandRecorder = undoableGroup
            try {
                function()
                addUndoCommand(undoableGroup)
            } finally {
                commandRecorder = NormalCommandRecorder
            }
        } else {
            // there is already a special commandRecorder, so just run the function
            function()
        }
    }

    fun addUndoCommand(undoCommand: Command) {
        redoCommands.clear()
        undoCommands.add(undoCommand)
    }

    /** Watch changes to a given Repository for the purposes of undoing. */
    fun <T : WithID<T>> watch(repository: Repository<T>) {
        repository.addListener(object : RepositoryListener<T> {
            override fun onSaved(original: T?, replacementWithID: T) {
                commandRecorder.addUndoCommandIfAppropriate(object : Command("Saved $replacementWithID") {
                    override fun executeAndGetOpposite(): Command {
                        val isUpdate = original != null && original.getID() != null
                        if (isUpdate) {
                            repository.save(replacementWithID, original!!)
                        } else {
                            repository.remove(replacementWithID)
                        }
                        val undoCommand = this
                        return object : Command(if (isUpdate) "Reverted $original" else "Redeleted $replacementWithID") {
                            override fun executeAndGetOpposite(): Command {
                                repository.save(original, replacementWithID)
                                return undoCommand
                            }
                        }
                    }
                })
            }

            override fun onRemoved(item: T) {
                commandRecorder.addUndoCommandIfAppropriate(object : Command("Deleted $item") {
                    override fun executeAndGetOpposite(): Command {
                        repository.save(null, item)
                        val undoCommand = this
                        return object : Command("Restored $item") {
                            override fun executeAndGetOpposite(): Command {
                                repository.remove(item)
                                return undoCommand
                            }
                        }
                    }
                })
            }
        })
    }
}

fun HTMLElement.undoComponent(showUndo: ReadOnlyProperty<Boolean> = true.toProperty()) {
    UndoComponent.render(this, showUndo)
}

abstract class Command(val pastTenseDescription: String) {
    abstract fun executeAndGetOpposite(): Command

    override fun toString(): String = pastTenseDescription
}

private interface CommandRecorder {
    fun addUndoCommandIfAppropriate(undoCommand: Command)
}

private object NormalCommandRecorder : CommandRecorder {
    override fun addUndoCommandIfAppropriate(undoCommand: Command) {
        UndoComponent.addUndoCommand(undoCommand)
    }
}

private object NoOpCommandRecorder : CommandRecorder {
    override fun addUndoCommandIfAppropriate(undoCommand: Command) {
        //do nothing
    }
}

/** A group of commands that are undone as a single unit. */
private class UndoableGroup(pastTenseDescription: String) : Command(pastTenseDescription), CommandRecorder {
    private val undoCommands: MutableList<Command> = mutableListOf()

    override fun addUndoCommandIfAppropriate(undoCommand: Command) {
        undoCommands.add(undoCommand)
    }

    /** Run all undoCommands and return the redo. */
    override fun executeAndGetOpposite(): Command {
        val redoCommands = undoCommands.map { it.executeAndGetOpposite() }
        val undoCommand = this
        return object : Command("Reversed $pastTenseDescription") {
            override fun executeAndGetOpposite(): Command {
                redoCommands.forEach { it.executeAndGetOpposite() }
                return undoCommand
            }
        }
    }
}