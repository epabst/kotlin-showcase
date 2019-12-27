package platform

import kotlin.test.*
import common.util.*

/**
 * A test for [JavascriptProvider].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 7/1/16
 * Time: 6:46 AM
 */
@Suppress("unused")
class JavascriptProviderTest {
    init {
        PlatformProvider.instance = JavascriptProvider
    }

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
        "(1.00)".parseCurrency().mustBe(-1.0)
        "(1)".parseCurrency().mustBe(-1.0)
        "-$1.00".parseCurrency().mustBe(-1.0)
        "-$1".parseCurrency().mustBe(-1.0)
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
        date.toJsDate().getHours().mustBe(0)
    }

    @Test
    fun toDate_YYYY_MM_DD() {
        val date = PlatformProvider.instance.toDate("2018-12-25")
        date.year.mustBe(2018)
        date.month.mustBe(11)
        date.dayOfMonth.mustBe(25)
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
