package client.test

import client.JavascriptProvider
import common.PlatformProvider
import common.RichDate

/**
 * A test for [RichDate].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
object RichDateTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider

        describe("RichDate") {
            it("should support parsing YYYY-MM-DD") {
                RichDate("1971-12-25").mustBe(RichDate(1971, 11, 25))
            }

            it("should support comparison") {
                RichDate("1971-12-25").compareTo(RichDate("1972-12-25")).mustBe(-1)
                RichDate("1971-12-25").compareTo(RichDate("1971-12-25")).mustBe(0)
                RichDate("1972-12-25").compareTo(RichDate("1971-12-25")).mustBe(1)
            }
        }
    }
}
