package util

/**
 * A provider for date functionality.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/25/16
 * Time: 1:31 PM
 */
expect object PlatformProvider {

    val platform: Platform

    fun now(): ProviderDate

    /** @throws IllegalArgumentException if unable to parse */
    fun parseCurrency(input: String): Double

    fun formatCurrency(input: Double): String

    fun formatCurrencyForInput(input: Double): String

    fun toDate(millis: Long): ProviderDate

    fun toDate(input: String): ProviderDate

    fun toDate(year: Int, monthIndex: Int, dayOfMonth: Int, hours: Int = 0, minutes: Int = 0): ProviderDate
}

enum class Platform {
    Javascript, Jvm
}

fun String.parseCurrency(): Double = PlatformProvider.parseCurrency(this)

fun Double.formatCurrency(): String = PlatformProvider.formatCurrency(this)

fun Double.formatCurrencyForInput(): String = PlatformProvider.formatCurrencyForInput(this)

interface ProviderDate : Comparable<ProviderDate> {
    val year: Int
    val monthIndex: Int
    val dayOfMonth: Int
    val dayOfWeekIndex: Int
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

fun ProviderDate(input: String): ProviderDate = PlatformProvider.toDate(input)
