package common

fun RichDate(date: ProviderDate): RichDate = RichDate(date.year, date.month, date.dayOfMonth)

fun ProviderDate.toRichDate(): RichDate { return RichDate(this) }

fun RichDate(year: Int, month: Int, day: Int): RichDate = RichDate((year - RichDate.baseYear) * 12 + month, day)

fun RichDate(millis: Long): RichDate = PlatformProvider.instance.toDate(millis).toRichDate()

fun RichDate(input: String): RichDate {
  val date: ProviderDate = PlatformProvider.instance.toDate(input)
  return date.toRichDate()
}

/** A wrapper around Date that gives it needed functionality.
  * @author Eric Pabst (epabst@gmail.com)
  * @param months # of months since right before 1970 with 1 being January 1970
  * @param days # of days since months with 1 being the first of the month
  */
data class RichDate(val months: Int, val days: Int) : Comparable<RichDate> {
  override fun compareTo(other: RichDate): Int = millis.compareTo(other.millis)

//  /** not a val since mutable. */
//  fun calendar: Calendar = GregorianCalendar(baseYear, months - 1, days)
  /** not a val since mutable. */
  val date: ProviderDate = PlatformProvider.instance.toDate((months / 12) + baseYear, months % 12, days)

  override fun toString(): String = date.toString()

  companion object {
    internal val baseYear = 1970

    fun today(): RichDate = RichDate(PlatformProvider.instance.now())
  }

  fun after(other: RichDate): Boolean = this > other
}

/** This is especially useful because it is immutable. */
val RichDate.millis: Long get() = date.millisecondsSinceUnixEpoch

val RichDate.dayOfMonth: Int get() = date.dayOfMonth
