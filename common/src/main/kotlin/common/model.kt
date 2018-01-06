package common

import common.util.ID
import common.util.WithID

/**
 * The core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */
data class Timer(val device: String, val date: String, val durationSeconds: Int, val id: ID<Timer>? = null) : WithID<Timer> {
    override fun getID(): ID<Timer>? = id

    override fun withID(id: ID<Timer>): Timer = copy(id = id)

    /** Used by [UndoComponent.watch]. */
    override fun toString(): String = "$durationSeconds seconds on $date"
}
