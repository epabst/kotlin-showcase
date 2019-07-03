package client.util

import common.util.PlatformProvider
import common.util.ProviderDate
import net.yested.ext.moment.Moment

external private fun parseFloat(input: String): Double = definedExternally
// This is here because Double.isNaN() doesn't work in KotlinJS
external private fun isNaN(input: Double): Boolean = definedExternally

//Numeral
external @JsModule("numeral") @JsNonModule private fun numeral(input: Double): Numeral = definedExternally
external interface Numeral { fun format(format: String): String }

/**
 * A PlatformProvider backed by Javascript libraries.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/25/16
 * Time: 1:48 PM
 */
object JavascriptProvider : PlatformProvider {
    override fun now(): MomentDate = MomentDate(Moment.now())

    override fun parseCurrency(input: String): Double {
        val result = parseFloat(input.replace("$", "").replace(")", "").replace("(", "-"))
        if (isNaN(result)) {
            throw IllegalArgumentException("invalid number: " + input)
        }
        return result
    }

    override fun formatCurrency(input: Double): String {
        return numeral(input).format("0,0.00")
    }

    override fun formatCurrencyForInput(input: Double): String {
        return numeral(input).format("0.00")
    }

    override fun toDate(millis: Long): MomentDate {
        return MomentDate(Moment.parseMillisecondsSinceUnixEpoch(millis))
    }

    override fun toDate(input: String): MomentDate {
        val dateFormat = "YYYY-MM-DD"
        val moment = if (input.length <= dateFormat.length) {
            Moment.parse(input, dateFormat)
        } else {
            // parse as an ISO timestamp
            Moment.parse(input)
        }
        return MomentDate(moment)
    }

    override fun toDate(year: Int, month: Int, dayOfMonth: Int): ProviderDate {
        val moment = Moment.parseMillisecondsSinceUnixEpoch(0)
        moment.year = year
        moment.month = month
        moment.dayOfMonth = dayOfMonth
        moment.hour = 0
        return MomentDate(moment)
    }
}

private data class ToLocaleOptions(val minimumFractionDigits: Int = 0, val maximumFractionDigits: Int = 20, val useGrouping: Boolean = true)
