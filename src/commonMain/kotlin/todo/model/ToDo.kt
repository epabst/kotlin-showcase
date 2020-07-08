package todo.model

import platform.PlatformProvider
import platform.ProviderDate
import component.entity.ID
import component.entity.Entity
import component.entity.Revision

/**
 * The core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */
data class ToDo(
    val name: String,
    val dueDateString: String? = null,
    val notes: String? = null,
    val createDateString: String,
    val _id: String? = null,
    val _rev: String? = null
) : Entity<ToDo> {

    constructor(name: String, dueDate: ProviderDate? = null, notes: String? = null, createDate: ProviderDate = PlatformProvider.now(), id: ID<ToDo>? = null, rev: Revision<ToDo>? = null) :
            this(name, dueDate?.toIsoTimestampString(), notes, createDate.toIsoTimestampString(), id?._id, rev?._rev)

    val dueDate: ProviderDate? get() = dueDateString?.let { PlatformProvider.toDate(it) }

    val createDate: ProviderDate? get() = PlatformProvider.toDate(createDateString)

    override val id: ID<ToDo>? get() = _id?.let { ID(it) }

    override fun withID(id: ID<ToDo>): ToDo = copy(_id = id._id)

    override val rev: Revision<ToDo>? get() = _rev?.let { Revision(it) }

    override fun withRevision(revision: Revision<ToDo>): ToDo = copy(_rev = revision._rev)

}
