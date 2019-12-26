package component

import component.repository.Repository
import component.repository.RepositoryListener
import component.repository.UndoProvider
import component.repository.WithID

/**
 * Support for undoing user actions.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/6/16
 * Time: 12:03 AM
 */
object UndoComponent : UndoProvider {
    private var commandRecorder: CommandRecorder = NormalCommandRecorder
    /** New commands are added at the end, calling [Property.set] each time. */
    private val undoCommands: MutableList<Command> = mutableListOf()
    /** New commands are added at the end. */
    private val redoCommands: MutableList<Command> = mutableListOf<Command>()

    val undoCount: Int get() = undoCommands.size
    val redoCount: Int get() = redoCommands.size

    suspend fun undo() {
        notUndoable {
            val undoCommandsCopy = undoCommands
            val index = undoCommandsCopy.size - 1
            val commandToUndo = undoCommandsCopy[index]
            val redoCommand = commandToUndo.executeAndGetOpposite()
            console.info("$commandToUndo (undoing: $commandToUndo)")
            undoCommands.removeAt(index)
            redoCommands.add(redoCommand)
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
        }
    }

//    internal fun render(builder: RBuilder) {
//        builder.apply {
//            btsButton(size = ButtonSize.Default, look = ButtonLook.Default, onclick = { undo() }) {
//                undoCommands.onNext {
//                    visible = it.isNotEmpty()
//                    disabled = it.isEmpty()
//                }
//                +"Undo"
//            }
//        }
//    }

    /**
     * Group a series of Repository actions into a single UndoableGroup.
     * Without doing this, each repository action (save or remove)
     * will each be individually undoable.
     */
    override suspend fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: suspend () -> T): T {
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
        redoCommands.clear()
        undoCommands.add(undoCommand)
    }

    /** Watch changes to a given Repository for the purposes of undoing. */
    fun <T : WithID<T>> watch(repository: Repository<T>) {
        repository.addListener(object : RepositoryListener<T> {
            override suspend fun onSaved(original: T?, replacementWithID: T) {
                val isUpdate = original != null && original.getID() != null
                commandRecorder.addUndoCommandIfAppropriate(object : Command(if (isUpdate) "Reverted $original" else "Deleted $replacementWithID") {
                    override suspend fun executeAndGetOpposite(): Command {
                        if (isUpdate) {
                            repository.save(replacementWithID, original!!)
                        } else {
                            repository.remove(replacementWithID)
                        }
                        val undoCommand = this
                        return object : Command(if (isUpdate) "Updated $original" else "Added $replacementWithID") {
                            override suspend fun executeAndGetOpposite(): Command {
                                repository.save(original, replacementWithID)
                                return undoCommand
                            }
                        }
                    }
                })
            }

            override suspend fun onRemoved(item: T) {
                commandRecorder.addUndoCommandIfAppropriate(object : Command("Restored $item") {
                    override suspend fun executeAndGetOpposite(): Command {
                        repository.save(null, item)
                        val undoCommand = this
                        return object : Command("Deleted $item") {
                            override suspend fun executeAndGetOpposite(): Command {
                                repository.remove(item)
                                return undoCommand
                            }
                        }
                    }
                })
            }

            override suspend fun onVisibilityChanged(item: T, visible: Boolean) {}
        })
    }
}

//fun RBuilder.undoComponent() {
//    UndoComponent.render(this)
//}

abstract class Command(val pastTenseDescription: String) {
    abstract suspend fun executeAndGetOpposite(): Command

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
