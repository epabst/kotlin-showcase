package client.util

import common.util.ID
import common.util.RichDate
import net.yested.ext.moment.Moment

/**
 * Serialization support for util classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/18/17
 * Time: 1:41 AM
 */
external interface LongJS {
    val high_: Int
    val low_: Int
}

private val HIGH_AMOUNT: Long = (Int.MAX_VALUE.toLong() + 1) * 2

fun LongJS?.toNormal(): Long? {
    if (this == null) {
        return null
    }
    else if (high_ == undefined) {
        val number: Number = asDynamic()
        return number.toLong()
    }
    else {
        return high_.toLong() * HIGH_AMOUNT + (if (low_ >= 0) low_.toLong() else low_.toLong() + HIGH_AMOUNT)
    }
}

fun String.toID(): ID? = JSON.parse<LongJS>(this).toNormal()?.let { ID(it) }

fun RichDate.toMoment(): Moment = (date as MomentDate).moment

external interface RichDateJS {
    val months: Int
    val days: Int
}

fun RichDateJS.toNormal(): RichDate = RichDate(months, days)

external interface IDJS {
    val id: LongJS
}

fun IDJS.toNormal(): ID? = id.toNormal()?.let { ID(it) }
