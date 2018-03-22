package client.util

import common.util.ProviderDate
import net.yested.ext.moment.Moment

/**
 * A ProviderDate backed by Moment.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/25/16
 * Time: 1:48 PM
 */
class MomentDate(val moment: Moment) : ProviderDate {
    override val year: Int
        get() = moment.year
    override val month: Int
        get() = moment.month
    override val dayOfMonth: Int
        get() = moment.dayOfMonth
    override val millisecondsSinceUnixEpoch: Long
        get() = moment.millisecondsSinceUnixEpoch
    override fun toString(): String = moment.format("ll")
    override fun toDisplayDateTimeString(): String = moment.format("lll")
    override fun toIsoTimestampString(): String = moment.format()
}
