package common.util

import org.jetbrains.spek.api.Spek

/**
 * A test for [DateOffset].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
class RichDateTest : Spek({
    PlatformProvider.instance = JvmProvider

    describe("RichDate") {
        it("should support parsing YYYY-MM-DD") {
            RichDate("1971-12-25").mustBe(RichDate(1971, 11, 25))
        }

        it("should support comparing") {
            (RichDate("1971-12-25") < RichDate("1971-12-26")).mustBe(true)
            (RichDate("1971-12-25") > RichDate("1971-12-24")).mustBe(true)
            (RichDate("1971-11-25") < RichDate("1971-12-25")).mustBe(true)

            (RichDate("1971-11-25") < RichDate("1971-11-25")).mustBe(false)
            (RichDate("1971-11-25") > RichDate("1971-11-25")).mustBe(false)
        }
    }
})
