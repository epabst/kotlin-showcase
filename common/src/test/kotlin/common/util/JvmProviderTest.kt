package common.util

import org.junit.Test
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
        assertFailsWith(IllegalArgumentException::class) { "garbage".parseCurrency() }.message.mustContain("garbage")
        assertFailsWith(IllegalArgumentException::class) { "(1.00)".parseCurrency() }.message.mustContain("(1.00)")
        assertFailsWith(IllegalArgumentException::class) { "(1)".parseCurrency() }
        assertFailsWith(IllegalArgumentException::class) { "-$1.00".parseCurrency() }
        assertFailsWith(IllegalArgumentException::class) { "-$1".parseCurrency() }
    }

    @Test
    fun formatCurrencyMustFormatCorrectly() {
        1234.2.formatCurrency().mustBe("1,234.20")
        1234.22324.formatCurrency().mustBe("1,234.22")
    }

    @Test
    fun formatCurrencyForInputMustFormatCorrectly() {
        1234.2.formatCurrencyForInput().mustBe("1234.20")
        1234.22324.formatCurrencyForInput().mustBe("1234.22")
    }
}
