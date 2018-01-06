package client

import client.util.*
import client.util.IDJS
import client.util.RichDateJS
import common.*

/**
 * JSON support for the core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */

external interface ToDoJS {
    val name: String
    val dueDate: RichDateJS?
    val note: String?
    val createDate: RichDateJS
    val id: IDJS?
}

fun ToDoJS.toNormal(): ToDo {
    return ToDo(name, dueDate?.toNormal(), note.emptyToNull(), createDate.toNormal(), id?.toNormal())
}
