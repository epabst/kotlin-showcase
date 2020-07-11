package util

import java.text.*
import java.util.*

actual object PlatformProvider {

    actual val platform: Platform = Platform.Jvm

    private val defaultCurrencyFormat by lazy {
        FunctionThreadLocal<NumberFormat> { NumberFormat.getCurrencyInstance() }
    }

    private val currencyFormat by lazy {
        FunctionThreadLocal<NumberFormat> {
            val format = NumberFormat.getNumberInstance()
            val defaultCurrencyFormat = defaultCurrencyFormat.get()
            format.setMinimumFractionDigits(defaultCurrencyFormat.minimumFractionDigits)
            format.setMaximumFractionDigits(defaultCurrencyFormat.maximumFractionDigits)
            format.setGroupingUsed(defaultCurrencyFormat.isGroupingUsed)
            format
        }
    }

    private val currencyInputFormat by lazy {
        FunctionThreadLocal<NumberFormat> {
            val format = NumberFormat.getNumberInstance()
            val currencyFormat = currencyFormat.get()
            format.setMinimumFractionDigits(currencyFormat.minimumFractionDigits)
            format.setMaximumFractionDigits(currencyFormat.maximumFractionDigits)
            format.setGroupingUsed(false)
            format
        }
    }

    actual fun parseCurrency(input: String): Double {
        return try {
            currencyFormat.get().parse(input).toDouble()
        } catch (e: ParseException) {
            try {
                defaultCurrencyFormat.get().parse(input).toDouble()
            } catch (e: ParseException) {
                throw IllegalArgumentException(e)
            }
        }
    }

    actual fun formatCurrency(input: Double): String = currencyFormat.get().format(input)

    actual fun formatCurrencyForInput(input: Double): String = currencyInputFormat.get().format(input)

    actual fun now(): ProviderDate =
        JvmDate(GregorianCalendar())

    actual fun toDate(millis: Long): ProviderDate {
        return toJvmDate(Date(millis))
    }

    actual fun toDate(input: String): ProviderDate {
        val parsePosition = ParsePosition(0)
        // first try ISO Timestamp
        var parsedDate = SimpleDateFormat.getDateTimeInstance().parse(input, parsePosition)
        if (parsePosition.index == 0) {
            parsedDate = JvmDate.yyyymmddInstance.parse(input, parsePosition)
        }
        if (parsePosition.index == 0) {
            parsedDate = SimpleDateFormat("MM/dd/yyyy").parse(input)
        }
        return toJvmDate(parsedDate)
    }

    private fun toJvmDate(javaDate: Date): JvmDate {
        val calendar = GregorianCalendar()
        calendar.time = javaDate
        return JvmDate(calendar)
    }

    actual fun toDate(year: Int, monthIndex: Int, dayOfMonth: Int, hours: Int, minutes: Int): ProviderDate {
        return JvmDate(GregorianCalendar(year, monthIndex, dayOfMonth, hours, minutes))
    }
}

class JvmDate(private val calendar: Calendar) : ProviderDate {
    override val year: Int
        get() = calendar.get(Calendar.YEAR)
    override val monthIndex: Int
        get() = calendar.get(Calendar.MONTH)
    override val dayOfMonth: Int
        get() = calendar.get(Calendar.DAY_OF_MONTH)
    override val dayOfWeekIndex: Int
        get() = calendar.get(Calendar.DAY_OF_WEEK) - 1
    override val millisecondsSinceUnixEpoch: Long
        get() = calendar.timeInMillis
    override val hours: Int
        get() = calendar.get(Calendar.HOUR_OF_DAY)
    override val minutes: Int
        get() = calendar.get(Calendar.MINUTE)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as JvmDate

        if (calendar != other.calendar) return false

        return true
    }

    override fun hashCode(): Int = calendar.hashCode()

    override fun toIsoDateString(): String = yyyymmddInstance.format(calendar.time)

    override fun toString(): String = toLocalizedDateString()

    override fun toLocalizedDateString(): String = localizedDateFormat.format(calendar.time)

    override fun toDisplayDateTimeString(): String = dateTimeInstance.format(calendar.time)

    override fun toIsoTimestampString(): String = dateTimeInstance.format(calendar.time)

    override fun plusDays(days: Int): ProviderDate {
        val result = GregorianCalendar(year, monthIndex, dayOfMonth)
        result.add(Calendar.DATE, days)
        return JvmDate(result)
    }

    companion object {
        val yyyymmddInstance = SimpleDateFormat("yyyy-MM-dd")
        private val localizedDateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
        private val dateTimeInstance = DateFormat.getDateTimeInstance()
    }
}

class FunctionThreadLocal<T>(private val factory: () -> T) : ThreadLocal<T>() {
    override fun initialValue() = factory()
}
