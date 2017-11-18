package common.util

import org.junit.Test

/**
 * A test for [RichDate].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
class RichDateTest {
    init {
        PlatformProvider.instance = JvmProvider
    }

    @Test
    fun itShouldSupportParingYYYMMDD() {
        RichDate("1971-12-25").mustBe(RichDate(1971, 11, 25))
    }

    @Test
    fun itShouldSupportComparing() {
        (RichDate("1971-12-25") < RichDate("1971-12-26")).mustBe(true)
        (RichDate("1971-12-25") > RichDate("1971-12-24")).mustBe(true)
        (RichDate("1971-11-25") < RichDate("1971-12-25")).mustBe(true)

        (RichDate("1971-11-25") < RichDate("1971-11-25")).mustBe(false)
        (RichDate("1971-11-25") > RichDate("1971-11-25")).mustBe(false)
    }
}
