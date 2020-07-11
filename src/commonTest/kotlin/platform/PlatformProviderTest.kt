package platform

import util.*
import kotlin.test.*

class PlatformProviderTest {

    @Test
    fun parseCurrency_mustParseVariousNumberFormats() {
        "1.00".parseCurrency().mustBe(1.0)
        "1".parseCurrency().mustBe(1.0)
        "-1.00".parseCurrency().mustBe(-1.0)
        "-1".parseCurrency().mustBe(-1.0)
        "$1.00".parseCurrency().mustBe(1.0)
        "$1".parseCurrency().mustBe(1.0)
        "($1.00)".parseCurrency().mustBe(-1.0)
        "($1)".parseCurrency().mustBe(-1.0)
        assertFailsWith(IllegalArgumentException::class) { "garbage".parseCurrency() }.message!!.mustContain("garbage")
        if (PlatformProvider.platform == Platform.Jvm) {
            assertFailsWith(IllegalArgumentException::class) { "(1.00)".parseCurrency() }.message!!.mustContain("(1.00)")
            assertFailsWith(IllegalArgumentException::class) { "(1)".parseCurrency() }
            assertFailsWith(IllegalArgumentException::class) { "-$1.00".parseCurrency() }
            assertFailsWith(IllegalArgumentException::class) { "-$1".parseCurrency() }
        } else {
            "(1.00)".parseCurrency().mustBe(-1.0)
            "(1)".parseCurrency().mustBe(-1.0)
            "-$1.00".parseCurrency().mustBe(-1.0)
            "-$1".parseCurrency().mustBe(-1.0)
        }
    }

    @Test
    fun parseCurrency_mustFailClearlyWhenUnparseable() {
        interceptAny { "garbage".parseCurrency() }.message.mustContain("garbage")
    }

    @Test
    fun formatCurrency_mustFormatCorrectly() {
        1234.2.formatCurrency().mustBe("1,234.20")
        1234.22324.formatCurrency().mustBe("1,234.22")
        (-1234.22324).formatCurrency().mustBe("-1,234.22")
        0.00.formatCurrency().mustBe("0.00")
    }

    @Test
    fun formatCurrencyForInput_mustFormatCorrectly() {
        1234.2.formatCurrencyForInput().mustBe("1234.20")
        1234.22324.formatCurrencyForInput().mustBe("1234.22")
    }

    @Test
    fun toDate_YYYY_0M_0D() {
        val date = PlatformProvider.toDate("2018-02-09")
        date.year.mustBe(2018)
        date.monthIndex.mustBe(1)
        date.dayOfMonth.mustBe(9)
    }

    @Test
    fun toDate_YYYY_M_D() {
        val date = PlatformProvider.toDate("2018-2-9")
        date.year.mustBe(2018)
        date.monthIndex.mustBe(1)
        date.dayOfMonth.mustBe(9)
    }

    @Test
    fun toDate_YYYY_MM_DD() {
        val date = PlatformProvider.toDate("2018-12-25")
        date.year.mustBe(2018)
        date.monthIndex.mustBe(11)
        date.dayOfMonth.mustBe(25)
    }

    @Test
    fun toDate_ISO_today() {
        val isoDateString = PlatformProvider.now().toIsoDateString()
        val date = PlatformProvider.toDate(isoDateString)
        date.toIsoDateString().mustBe(isoDateString)
    }

    @Test
    fun toDate_ISO_now() {
        val isoTimestampString = PlatformProvider.now().toIsoTimestampString()
        val date = PlatformProvider.toDate(isoTimestampString)
        date.toIsoTimestampString().mustBe(isoTimestampString)
    }

    @Test
    fun toDate_date() {
        val string = PlatformProvider.toDate(2018, 4, 22).toIsoDateString()
        val date = PlatformProvider.toDate(string)
        date.toIsoDateString().mustBe(string)
    }

    @Test
    fun toIsoDateString() {
        val date = PlatformProvider.toDate("2018-10-11")
        date.toIsoDateString().mustBe("2018-10-11")
    }

    @Test
    fun dayOfWeekIndex() {
        val date = PlatformProvider.toDate("2020-01-04")
        date.dayOfWeekIndex.mustBe(6)
    }
}
