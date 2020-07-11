package util.undo

import util.Entity

interface UndoProvider {
    suspend fun <T : Entity<T>, F> undoableSave(original: T?, replacementWithID: T, function: suspend () -> F): F {
        val isUpdate = original?.id != null
        val pastTenseDescription = if (isUpdate) "Updated $original" else "Added $replacementWithID"
        val undoPastTenseDescription = if (isUpdate) "Reverted $original" else "Deleted $replacementWithID"
        return undoable(pastTenseDescription, undoPastTenseDescription, function)
    }


    suspend fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: suspend () -> T): T

    suspend fun <T> notUndoable(function: suspend () -> T): T

    companion object {
        val empty: UndoProvider = object : UndoProvider {
            override suspend fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: suspend () -> T): T {
                return function()
            }

            override suspend fun <T> notUndoable(function: suspend () -> T): T {
                return function()
            }
        }
    }
}