package client

import client.util.*
import client.util.IDJS
import common.*

/**
 * JSON support for the core model classes.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:27 AM
 */

external interface TimerJS {
    val device: String
    val date: String
    val durationSeconds: Int
    val id: IDJS?
}

fun TimerJS.toNormal(): Timer {
    return Timer(device, date, durationSeconds, id?.toNormal())
}
