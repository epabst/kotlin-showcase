package platform

import kotlin.js.Date

private external fun parseFloat(input: String): Double = definedExternally
// This is here because Double.isNaN() doesn't work in KotlinJS
private external fun isNaN(input: Double): Boolean = definedExternally

@JsModule("numeral")
@JsNonModule
private external fun numeral(input: Double): Numeral = definedExternally
external interface Numeral { fun format(format: String): String }

/**
 * A PlatformProvider backed by Javascript libraries.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/25/16
 * Time: 1:48 PM
 */
actual object PlatformProvider {

    actual val platform: Platform = Platform.Javascript

    actual fun now(): ProviderDate = JavascriptDate(Date(Date.now()))

    actual fun parseCurrency(input: String): Double {
        val result = parseFloat(input.replace("$", "").replace(")", "").replace("(", "-"))
        if (isNaN(result)) {
            throw IllegalArgumentException("invalid number: $input")
        }
        return result
    }

    actual fun formatCurrency(input: Double): String {
        return numeral(input).format("0,0.00")
    }

    actual fun formatCurrencyForInput(input: Double): String {
        return numeral(input).format("0.00")
    }

    actual fun toDate(millis: Long): ProviderDate {
        return JavascriptDate(Date(millis))
    }

    actual fun toDate(input: String): ProviderDate {
        return if (input.length == "YYYY-MM-DD".length) {
            JavascriptDate(Date(input + "T12:00:00"))
        } else {
            JavascriptDate(Date(input))
        }
    }

    actual fun toDate(year: Int, monthIndex: Int, dayOfMonth: Int, hours: Int, minutes: Int): ProviderDate {
        return JavascriptDate(Date(year, monthIndex, dayOfMonth, hours, minutes))
    }
}
