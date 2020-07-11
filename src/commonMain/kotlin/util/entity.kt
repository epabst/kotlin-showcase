@file:Suppress("unused")

package util

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

