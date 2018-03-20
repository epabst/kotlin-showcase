package client.component

import common.util.Repository
import common.util.RepositoryListener
import common.util.UndoProvider
import common.util.WithID
import net.yested.core.properties.*
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
object UndoComponent : UndoProvider {
    private var commandRecorder: CommandRecorder = NormalCommandRecorder
    /** New commands are added at the end, calling [Property.set] each time. */
    private val undoCommands: Property<List<Command>> = emptyList<Command>().toProperty()
    /** New commands are added at the end. */
    private val redoCommands: Property<List<Command>> = emptyList<Command>().toProperty()

    val undoCount: Int get() = undoCommands.get().size
    val redoCount: Int get() = redoCommands.get().size

    fun undo() {
        notUndoable {
            val undoCommandsCopy = undoCommands.get()
            val index = undoCommandsCopy.size - 1
            val commandToUndo = undoCommandsCopy[index]
            val redoCommand = commandToUndo.executeAndGetOpposite()
            console.info("$commandToUndo (undoing: $commandToUndo)")
            undoCommands.removeAt(index)
            redoCommands.add(redoCommand)
        }
    }

    fun redo() {
        notUndoable {
            val redoCommandsCopy = redoCommands.get()
            val index = redoCommandsCopy.size - 1
            val commandToRedo = redoCommandsCopy[index]
            val undoCommand = commandToRedo.executeAndGetOpposite()
            console.info("$commandToRedo (redo)")
            redoCommands.removeAt(index)
            undoCommands.add(undoCommand)
        }
    }

    internal fun render(element: HTMLElement) {
        element with {
            btsButton(size = ButtonSize.Default, look = ButtonLook.Default, onclick = { undo() }) {
                undoCommands.onNext {
                    visible = it.isNotEmpty()
                    disabled = it.isEmpty()
                }
                appendText("Undo")
            }
        }
    }

    /**
     * Group a series of Repository actions into a single UndoableGroup.
     * Without doing this, each repository action (save or remove)
     * will each be individually undoable.
     */
    override fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: () -> T): T {
        if (commandRecorder == NormalCommandRecorder) {
            val undoableGroup = UndoableGroup(pastTenseDescription, undoPastTenseDescription)
            commandRecorder = undoableGroup
            try {
                val result = function()
                if (!undoableGroup.isEmpty()) {
                    addUndoCommand(undoableGroup)
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

    override fun <T> notUndoable(function: () -> T): T {
        val originalRecorder = commandRecorder
        commandRecorder = NoOpCommandRecorder
        try {
            return function()
        } finally {
            commandRecorder = originalRecorder
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
                val isUpdate = original != null && original.getID() != null
                commandRecorder.addUndoCommandIfAppropriate(object : Command(if (isUpdate) "Reverted $original" else "Deleted $replacementWithID") {
                    override fun executeAndGetOpposite(): Command {
                        if (isUpdate) {
                            repository.save(replacementWithID, original!!)
                        } else {
                            repository.remove(replacementWithID)
                        }
                        val undoCommand = this
                        return object : Command(if (isUpdate) "Updated $original" else "Added $replacementWithID") {
                            override fun executeAndGetOpposite(): Command {
                                repository.save(original, replacementWithID)
                                return undoCommand
                            }
                        }
                    }
                })
            }

            override fun onRemoved(item: T) {
                commandRecorder.addUndoCommandIfAppropriate(object : Command("Restored $item") {
                    override fun executeAndGetOpposite(): Command {
                        repository.save(null, item)
                        val undoCommand = this
                        return object : Command("Deleted $item") {
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

fun HTMLElement.undoComponent() {
    UndoComponent.render(this)
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
private class UndoableGroup(private val redoPastTenseDescription: String, undoPastTenseDescription: String) : Command(undoPastTenseDescription), CommandRecorder {
    private val undoCommands: MutableList<Command> = mutableListOf()

    override fun addUndoCommandIfAppropriate(undoCommand: Command) {
        undoCommands.add(0, undoCommand)
    }

    fun isEmpty(): Boolean = undoCommands.isEmpty()

    /** Run all undoCommands and return the redo. */
    override fun executeAndGetOpposite(): Command {
        val redoCommands = undoCommands.map { it.executeAndGetOpposite() }.reversed()
        val undoCommand = this
        return object : Command(redoPastTenseDescription) {
            override fun executeAndGetOpposite(): Command {
                redoCommands.forEach { it.executeAndGetOpposite() }
                return undoCommand
            }
        }
    }
}
