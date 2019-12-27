package platform

/**
 * A ProviderDate backed by Moment.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/25/16
 * Time: 1:48 PM
 */
class JavascriptDate(val date: kotlin.js.Date) : ProviderDate {
    override val year: Int
        get() = date.getFullYear()
    override val month: Int
        get() = date.getMonth()
    override val dayOfMonth: Int
        get() = date.getDate()
    override val hours: Int
        get() = date.getHours()
    override val minutes: Int
        get() = date.getMinutes()
    override val millisecondsSinceUnixEpoch: Long
        get() = date.getTime().toLong()
    override fun toString(): String = toLocalizedDateString()
    override fun toIsoDateString(): String = date.toISOString().substringBefore("T")
    override fun toLocalizedDateString(): String = date.toDateString()
    override fun toDisplayDateTimeString(): String = date.toString()
    override fun toIsoTimestampString(): String = date.toISOString()
}

fun kotlin.js.Date.toProviderDate(): JavascriptDate = JavascriptDate(this)

fun ProviderDate.toJsDate(): kotlin.js.Date = (this as JavascriptDate).date
