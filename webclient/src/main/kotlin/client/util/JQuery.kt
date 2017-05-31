package client.util

import jquery.JQuery

/**
 * Additional @native access to [JQuery] plus some convenience functions.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/5/16
 * Time: 6:26 AM
 */
@native fun JQuery.closest(selector: String): JQuery = noImpl

@native fun JQuery.find(selector: String): JQuery = noImpl

@native fun JQuery.children(selector: String): JQuery = noImpl
