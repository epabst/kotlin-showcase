package client

import common.*
import net.yested.ext.moment.Moment

/**
 * JSON support for the core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */
@native
interface LongJS {
    val high_: Int
    val low_: Int
}

private val HIGH_AMOUNT: Long = (Int.MAX_VALUE.toLong() + 1) * 2

fun LongJS.toNormal(): Long {
    if (high_ == undefined) {
        val number: Number = asDynamic()
        return number.toLong()
    }
    else {
        return high_.toLong() * HIGH_AMOUNT + (if (low_ >= 0) low_.toLong() else low_.toLong() + HIGH_AMOUNT)
    }
}

fun String.toID(): ID = ID(JSON.parse<LongJS>(this).toNormal())

fun ProviderDate.toMoment(): Moment = (this as MomentDate).moment

@native interface ProviderDateJS {
    val millisecondsSinceUnixEpoch: LongJS
}

fun ProviderDateJS.toNormal(): ProviderDate = ProviderDate(millisecondsSinceUnixEpoch.toNormal())

@native interface IDJS {
    val id: LongJS
}

fun IDJS.toNormal(): ID = ID(id.toNormal())

@native interface ToDoJS {
    val name: String
    val dueDate: ProviderDateJS?
    val note: String?
    val createDate: ProviderDateJS
    val id: IDJS?
}

fun ToDoJS.toNormal(): ToDo {
    return ToDo(name, dueDate?.toNormal(), note, createDate.toNormal(), id?.toNormal())
}
