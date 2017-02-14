package client.test

import client.JavascriptProvider
import common.PlatformProvider
import common.formatCurrency
import common.formatCurrencyForInput
import common.parseCurrency

/**
 * A test for [JavascriptProvider].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 7/1/16
 * Time: 6:46 AM
 */
object JavascriptProviderTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider

        describe("parseCurrency") {
            it("must parse various number formats") {
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

            it("must fail clearly when unparseable") {
                interceptAny { "garbage".parseCurrency() }.message.mustContain("garbage")
            }
        }

        describe("formatCurrency") {
            it("must format correctly") {
                1234.2.formatCurrency().mustBe("1,234.20")
                1234.22324.formatCurrency().mustBe("1,234.22")
            }
        }

        describe("formatCurrencyForInput") {
            it("must format correctly") {
                1234.2.formatCurrencyForInput().mustBe("1234.20")
                1234.22324.formatCurrencyForInput().mustBe("1234.22")
            }
        }
    }
}
