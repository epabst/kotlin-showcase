package client.util

import org.w3c.dom.Window
import kotlin.browser.window

/**
 * Support for Cordova.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/27/16
 * Time: 4:04 PM
 */

val Window.cordova: Cordova? get() = asDynamic().cordova

external interface Cordova { val plugins: CordovaPlugins? }

external interface CordovaPlugins { val Keyboard: CordovaKeyboard? }

external interface CordovaKeyboard {
	fun hideKeyboardAccessoryBar(hide: Boolean)

	fun disableScroll(disable: Boolean)
}

val Window.StatusBar: StatusBar? get() = asDynamic().StatusBar

external interface StatusBar { fun styleDefault() }

fun initializeForCordova() {
		// Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
	 // for form inputs)
	val keyboard = window.cordova?.plugins?.Keyboard
	if (keyboard != null) {
		keyboard.hideKeyboardAccessoryBar(true);
		keyboard.disableScroll(true);

	 }
	val statusBar = window.StatusBar
	if (statusBar != null) {
	     // org.apache.cordova.statusbar required
		 statusBar.styleDefault();
	 }
}
