package todo.model

import platform.PlatformProvider
import platform.ProviderDate
import component.repository.ID
import component.repository.WithID

/**
 * The core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */
data class ToDo(val name: String, val dueDateString: String? = null, val notes: String? = null, val createDateString: String, val id: ID<ToDo>? = null) : WithID<ToDo> {
    constructor(name: String, dueDate: ProviderDate? = null, notes: String? = null, createDate: ProviderDate = PlatformProvider.instance.now(), id: ID<ToDo>? = null) :
            this(name, dueDate?.toIsoTimestampString(), notes, createDate.toIsoTimestampString(), id)

    val dueDate: ProviderDate? get() = dueDateString?.let { PlatformProvider.instance.toDate(it) }

    val createDate: ProviderDate? get() = PlatformProvider.instance.toDate(createDateString)

    override fun getID(): ID<ToDo>? = id

    override fun withID(id: ID<ToDo>): ToDo = copy(id = id)

    /** Used by [UndoComponent.watch]. */
    override fun toString(): String = "'$name'"
}
