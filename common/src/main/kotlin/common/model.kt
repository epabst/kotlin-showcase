package common

import common.util.*
import common.util.RichDate.Companion.today

/**
 * The core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */
data class ToDo(val name: String, val dueDate: RichDate? = null, val note: String? = null, val createDate: RichDate = today(), val id: ID<ToDo>? = null) : ProtectedWithID<ToDo> {
    override fun getID(): ID<ToDo>? = id

    override fun withID(id: ID<ToDo>): ToDo = copy(id = id)

    override val protectedAccess: Access get() = Access(ProtectionLevel.DEVICE)

    override fun copy(newAccess: Access): ToDo = this

    /** Used by [UndoComponent.watch]. */
    override fun toString(): String = "'$name'"
}
