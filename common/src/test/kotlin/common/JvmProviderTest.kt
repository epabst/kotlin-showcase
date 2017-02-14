package common

import org.jetbrains.spek.api.Spek

/**
 * A test for [JvmProvider].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/30/16
 * Time: 9:12 PM
 */
class JvmProviderTest : Spek({
    PlatformProvider.instance = JvmProvider

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
            intercept<IllegalArgumentException> { "garbage".parseCurrency() }.message.mustContain("garbage")
            intercept<IllegalArgumentException> { "(1.00)".parseCurrency() }.message.mustContain("(1.00)")
            intercept<IllegalArgumentException> { "(1)".parseCurrency() }
            intercept<IllegalArgumentException> { "-$1.00".parseCurrency() }
            intercept<IllegalArgumentException> { "-$1".parseCurrency() }
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
})
