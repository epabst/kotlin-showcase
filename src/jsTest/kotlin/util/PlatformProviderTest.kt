package util

import kotlin.test.*

class PlatformProviderJsTest {

    @Test
    fun toDate_YYYY_M_D() {
        val date = PlatformProvider.toDate("2018-2-9")
        date.year.mustBe(2018)
        date.monthIndex.mustBe(1)
        date.dayOfMonth.mustBe(9)
        date.toJsDate().getHours().mustBe(0)
    }
}
