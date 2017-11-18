package common.util

import client.util.mustBe
import client.util.JavascriptProvider
import kotlin.test.Test

/**
 * A test for [RichDate].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
@Suppress("unused")
class RichDateTest {
    init {
        PlatformProvider.instance = JavascriptProvider
    }

    @Test
    fun shouldSupportParsingYYYY_MM_DD() {
        RichDate("1971-12-25").mustBe(RichDate(1971, 11, 25))
    }

    @Test
    fun shouldSupportComparison() {
        RichDate("1971-12-25").compareTo(RichDate("1972-12-25")).mustBe(-1)
        RichDate("1971-12-25").compareTo(RichDate("1971-12-25")).mustBe(0)
        RichDate("1972-12-25").compareTo(RichDate("1971-12-25")).mustBe(1)
    }
}
