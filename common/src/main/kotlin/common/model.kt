package common

import common.util.*

/**
 * The core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */
data class ToDo(val name: String, val dueDateString: String? = null, val note: String? = null, val createDateString: String, val id: ID<ToDo>? = null) : ProtectedWithID<ToDo> {
    constructor(name: String, dueDate: ProviderDate? = null, note: String? = null, createDate: ProviderDate = PlatformProvider.instance.now(), id: ID<ToDo>? = null) :
            this(name, dueDate?.toIsoTimestampString(), note, createDate.toIsoTimestampString(), id)

    val dueDate: ProviderDate? get() = dueDateString?.let { PlatformProvider.instance.toDate(it) }

    val createDate: ProviderDate? get() = PlatformProvider.instance.toDate(createDateString)

    override fun getID(): ID<ToDo>? = id

    override fun withID(id: ID<ToDo>): ToDo = copy(id = id)

    override val protectedAccess: Access get() = Access(ProtectionLevel.DEVICE)

    override fun copy(newAccess: Access): ToDo = this

    /** Used by [UndoComponent.watch]. */
    override fun toString(): String = "'$name'"
}
