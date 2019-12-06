package todo.model

import component.repository.IDJS
import component.repository.toNormal
import platform.JavascriptProvider
import util.emptyToNull

/**
 * JSON support for the core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */

external interface ToDoJS {
    val name: String
    val dueDateString: String?
    val notes: String?
    val createDateString: String?
    val id: IDJS?
}

fun ToDoJS.toNormal(): ToDo {
    return ToDo(name, dueDateString, notes.emptyToNull(), createDateString
            ?: JavascriptProvider.now().toIsoTimestampString(), id?.toNormal())
}
