package platform

import kotlin.properties.Delegates.notNull

/**
 * A provider for date functionality.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/25/16
 * Time: 1:31 PM
 */
interface PlatformProvider {
    fun now(): ProviderDate

    /** @throws IllegalArgumentException if unable to parse */
    fun parseCurrency(input: String): Double

    fun formatCurrency(input: Double): String

    fun formatCurrencyForInput(input: Double): String

    fun toDate(millis: Long): ProviderDate

    fun toDate(input: String): ProviderDate

    fun toDate(year: Int, month: Int, dayOfMonth: Int, hours: Int = 0, minutes: Int = 0): ProviderDate

    companion object {
        /** This must be set before methods will work. */
        var instance: PlatformProvider by notNull<PlatformProvider>()
    }
}

fun String.parseCurrency(): Double = PlatformProvider.instance.parseCurrency(this)

fun Double.formatCurrency(): String = PlatformProvider.instance.formatCurrency(this)

fun Double.formatCurrencyForInput(): String = PlatformProvider.instance.formatCurrencyForInput(this)

interface ProviderDate : Comparable<ProviderDate> {
    val year: Int
    val monthIndex: Int
    val dayOfMonth: Int
    val dayOfWeek: Int
    val millisecondsSinceUnixEpoch: Long
    val hours: Int
    val minutes: Int
    fun toIsoDateString(): String
    fun toLocalizedDateString(): String
    fun toDisplayDateTimeString(): String
    fun toIsoTimestampString(): String
    fun plusDays(days: Int): ProviderDate

    override fun compareTo(other: ProviderDate): Int {
        return millisecondsSinceUnixEpoch.compareTo(other.millisecondsSinceUnixEpoch)
    }
}

fun ProviderDate(input: String): ProviderDate = PlatformProvider.instance.toDate(input)
