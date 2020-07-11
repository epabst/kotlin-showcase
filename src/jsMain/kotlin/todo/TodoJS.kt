package todo

import util.PlatformProvider
import pouchdb.Document
import util.EntityJS
import util.emptyToNull

/**
 * JSON support for the core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */

@Suppress("PropertyName")
abstract class ToDoJS : EntityJS<ToDo> {
    abstract val name: String
    abstract val dueDateString: String?
    abstract val notes: String?
    abstract val createDateString: String?
    abstract val _id: String?
    abstract val _rev: String?
}


fun ToDoJS.toNormal(): ToDo {
    return ToDo(
        name = name,
        dueDateString = dueDateString,
        notes = notes.emptyToNull(),
        createDateString = createDateString ?: PlatformProvider.now().toIsoTimestampString(),
        _id = _id,
        _rev = _rev
    )
}

fun Document<ToDoJS>.parse(): ToDo = this.unsafeCast<ToDoJS>().toNormal()
