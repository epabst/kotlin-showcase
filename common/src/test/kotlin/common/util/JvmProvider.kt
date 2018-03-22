package common.util

import java.text.*
import java.util.*

/**
 * A [PlatformProvider] for the JVM.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/29/16
 * Time: 1:18 AM
 */
object JvmProvider : PlatformProvider {
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

    override fun parseCurrency(input: String): Double {
        try {
            return currencyFormat.get().parse(input).toDouble()
        } catch (e: ParseException) {
            try {
                return defaultCurrencyFormat.get().parse(input).toDouble()
            } catch (e: ParseException) {
                throw IllegalArgumentException(e)
            }
        }
    }

    override fun formatCurrency(input: Double): String = currencyFormat.get().format(input)

    override fun formatCurrencyForInput(input: Double): String = currencyInputFormat.get().format(input)

    override fun now(): ProviderDate = JvmDate(GregorianCalendar())

    override fun toDate(millis: Long): JvmDate {
        return toJvmDate(Date(millis))
    }

    override fun toDate(input: String): JvmDate {
        val parsePosition = ParsePosition(0)
        // first try ISO Timestamp
        var parsedDate = SimpleDateFormat.getDateTimeInstance().parse(input, parsePosition)
        if (parsePosition.index == 0) {
            parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(input, parsePosition)
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

    override fun toDate(year: Int, month: Int, dayOfMonth: Int): ProviderDate {
        return JvmDate(GregorianCalendar(year, month, dayOfMonth))
    }
}

class JvmDate(private val calendar: Calendar) : ProviderDate {
    override val year: Int
        get() = calendar.get(Calendar.YEAR)
    override val month: Int
        get() = calendar.get(Calendar.MONTH)
    override val dayOfMonth: Int
        get() = calendar.get(Calendar.DAY_OF_MONTH)
    override val millisecondsSinceUnixEpoch: Long
        get() = calendar.timeInMillis

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as JvmDate

        if (calendar != other.calendar) return false

        return true
    }

    override fun hashCode(): Int = calendar.hashCode()

    override fun toString(): String = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.time)

    override fun toIsoTimestampString(): String = DateFormat.getDateTimeInstance().format(calendar.time)
}

class FunctionThreadLocal<T>(private val factory: () -> T) : ThreadLocal<T>() {
    override fun initialValue() = factory()
}
