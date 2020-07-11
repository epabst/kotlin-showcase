package util

import kotlin.js.Date

/**
 * A ProviderDate backed by Moment.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/25/16
 * Time: 1:48 PM
 */
class JavascriptDate(val date: Date) : ProviderDate {
    override val year: Int
        get() = date.getFullYear()
    override val monthIndex: Int
        get() = date.getMonth()
    override val dayOfMonth: Int
        get() = date.getDate()
    override val dayOfWeekIndex: Int
        get() = date.getDay()
    override val hours: Int
        get() = date.getHours()
    override val minutes: Int
        get() = date.getMinutes()
    override val millisecondsSinceUnixEpoch: Long
        get() = date.getTime().toLong()
    override fun toString(): String = toLocalizedDateString()
    override fun toIsoDateString(): String = date.toIsoDateString()
    override fun toLocalizedDateString(): String = date.toDateString()
    override fun toDisplayDateTimeString(): String = date.toString()
    override fun toIsoTimestampString(): String = date.toISOString()
    override fun plusDays(days: Int): ProviderDate = JavascriptDate(Date(year, monthIndex, dayOfMonth + days))
}

fun Date.toProviderDate(): JavascriptDate = JavascriptDate(this)

fun ProviderDate.toJsDate(): Date = (this as JavascriptDate).date

fun Date.toIsoDateString() = toISOString().substringBefore("T")
