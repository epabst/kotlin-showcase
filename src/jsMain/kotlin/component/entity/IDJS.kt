package component.entity

/**
 * Serialization support for util classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/18/17
 * Time: 1:41 AM
 */
external interface LongJS {
    val high_: Int
    val low_: Int
}

private val HIGH_AMOUNT: Long = (Int.MAX_VALUE.toLong() + 1) * 2

fun LongJS?.toNormal(): Long? {
    if (this == null) {
        return null
    }
    else if (high_ == undefined) {
        val number: Number = asDynamic()
        return number.toLong()
    }
    else {
        return high_.toLong() * HIGH_AMOUNT + (if (low_ >= 0) low_.toLong() else low_.toLong() + HIGH_AMOUNT)
    }
}

fun <E : Entity<E>> String.toID(): ID<E>? = ID(this)

interface EntityJS<T : Entity<T>>

external interface IDJS {
    val _id: String?
}

fun <E : Entity<E>> IDJS.toNormal(): ID<E> = _id?.let { ID(it) } ?: error("required ID is not present")

external interface RevisionJS {
    val _rev: String?
}

fun <E : Entity<E>> RevisionJS.toNormal(): Revision<E> = _rev?.let { Revision(it) } ?: error("required revision is not present")
