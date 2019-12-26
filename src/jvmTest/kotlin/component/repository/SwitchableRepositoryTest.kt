package component.repository

class CountingUndoProvider : UndoProvider {
    var undoableCount: Int = 0
    var notUndoableCount: Int = 0

    override suspend fun <T> undoable(pastTenseDescription: String, undoPastTenseDescription: String, function: suspend () -> T): T {
        undoableCount++
        return function()
    }

    override suspend fun <T> notUndoable(function: suspend () -> T): T {
        notUndoableCount++
        return function()
    }
}
