@file:Suppress("unused")

package component.entity

import platform.PlatformProvider
import kotlin.random.Random

/**
 * Persistence Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:01 AM
 */

inline class ID<T : Entity<T>>(val _id: String) {
    constructor(id: Long) : this(id.toString())

    override fun toString(): String = _id
}

inline class Revision<T : Entity<T>>(val _rev: String) {
    override fun toString(): String = _rev
}

interface Entity<T : Entity<T>> {
    val requiredId: ID<T> get() = id ?: error("$this must have an ID to be used")

    val id: ID<T>?

    val requiredRev: Revision<T> get() = rev ?: error("$this must have a revision to be used")

    val rev: Revision<T>?

    fun withID(id: ID<T>): T

    fun withRevision(revision: Revision<T>): T
}

object IdGenerator {
    fun generateID(): String {
        return PlatformProvider.now().toIsoTimestampString() + "." + Random.nextInt(100000)
    }
}

fun interface Closeable {
    fun close()
}

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
