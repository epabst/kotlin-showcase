package common

import common.RichDate.Companion.today

/**
 * The core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */
data class ToDo(val name: String, val dueDate: RichDate? = null, val note: String? = null, val createDate: RichDate = today(), val id: ID? = null) : WithID<ToDo> {
    override fun getID(): ID? = id

    override fun withID(id: ID): ToDo = copy(id = id)

    /** Used by [UndoComponent.watch]. */
    override fun toString(): String = "'$name'"
}
