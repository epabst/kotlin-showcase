package platform

import common.util.mustBe
import common.util.mustContain
import kotlin.test.Test
import kotlin.test.assertFailsWith

/**
 * A test for [JvmProvider].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/30/16
 * Time: 9:12 PM
 */
class JvmProviderTest {
    init {
        PlatformProvider.instance = JvmProvider
    }

    @Test
    fun parseCurrencyMustParseVariousNumberFormats() {
        "1.00".parseCurrency().mustBe(1.0)
        "1".parseCurrency().mustBe(1.0)
        "-1.00".parseCurrency().mustBe(-1.0)
        "-1".parseCurrency().mustBe(-1.0)
        "$1.00".parseCurrency().mustBe(1.0)
        "$1".parseCurrency().mustBe(1.0)
        "($1.00)".parseCurrency().mustBe(-1.0)
        "($1)".parseCurrency().mustBe(-1.0)
        assertFailsWith(IllegalArgumentException::class) { "garbage".parseCurrency() }.message!!.mustContain("garbage")
        assertFailsWith(IllegalArgumentException::class) { "(1.00)".parseCurrency() }.message!!.mustContain("(1.00)")
        assertFailsWith(IllegalArgumentException::class) { "(1)".parseCurrency() }
        assertFailsWith(IllegalArgumentException::class) { "-$1.00".parseCurrency() }
        assertFailsWith(IllegalArgumentException::class) { "-$1".parseCurrency() }
    }

    @Test
    fun formatCurrencyMustFormatCorrectly() {
        1234.2.formatCurrency().mustBe("1,234.20")
        1234.22324.formatCurrency().mustBe("1,234.22")
        (-1234.22324).formatCurrency().mustBe("-1,234.22")
        0.00.formatCurrency().mustBe("0.00")
    }

    @Test
    fun formatCurrencyForInputMustFormatCorrectly() {
        1234.2.formatCurrencyForInput().mustBe("1234.20")
        1234.22324.formatCurrencyForInput().mustBe("1234.22")
    }

    @Test
    fun toDate_YYYY_MM_DD() {
        val date = PlatformProvider.instance.toDate("2018-02-09")
        date.year.mustBe(2018)
        date.month.mustBe(1)
        date.dayOfMonth.mustBe(9)
    }

    @Test
    fun toDate_YYYY_M_D() {
        val date = PlatformProvider.instance.toDate("2018-2-9")
        date.year.mustBe(2018)
        date.month.mustBe(1)
        date.dayOfMonth.mustBe(9)
    }

    @Test
    fun toDate_ISO_today() {
        val isoDateString = PlatformProvider.instance.now().toIsoDateString()
        val date = PlatformProvider.instance.toDate(isoDateString)
        date.toIsoDateString().mustBe(isoDateString)
    }

    @Test
    fun toDate_ISO_now() {
        val isoTimestampString = PlatformProvider.instance.now().toIsoTimestampString()
        val date = PlatformProvider.instance.toDate(isoTimestampString)
        date.toIsoTimestampString().mustBe(isoTimestampString)
    }

    @Test
    fun toDate_date() {
        val string = PlatformProvider.instance.toDate(2018, 4, 22).toIsoDateString()
        val date = PlatformProvider.instance.toDate(string)
        date.toIsoDateString().mustBe(string)
    }

    @Test
    fun toIsoDateString() {
        val date = PlatformProvider.instance.toDate("2018-10-11")
        date.toIsoDateString().mustBe("2018-10-11")
    }
}
