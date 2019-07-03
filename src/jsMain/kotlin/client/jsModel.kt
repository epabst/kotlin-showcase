package client

import client.util.*
import client.util.IDJS
import common.*

/**
 * JSON support for the core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */

external interface ToDoJS {
    val name: String
    val dueDateString: String?
    val note: String?
    val createDateString: String?
    val id: IDJS?
}

fun ToDoJS.toNormal(): ToDo {
    return ToDo(name, dueDateString, note.emptyToNull(), createDateString ?: JavascriptProvider.now().toIsoTimestampString(), id?.toNormal())
}
