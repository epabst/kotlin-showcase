package client.component

import net.yested.core.html.*
import net.yested.ext.bootstrap3.Column
import org.w3c.dom.HTMLElement

/**
 * A [Column] that can be animated such as using JQuery's slideUp.
 * Apparently, a TD can't be animated well, but if every TD in a row has a DIV in it, then it can be.
 * That's what this class does.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 8/5/16
 * Time: 6:14 AM
 */
fun <T> animatableColumn(label: HTMLElement.() -> Unit,
                         render: HTMLElement.(T) -> Unit,
                         sortFunction:((T, T) -> Int)? = null,
                         align: Align = Align.LEFT,
                         sortAscending:Boolean = true): Column<T> {
    return Column<T>(label = label, render = { item -> div { render(item) } }, sortFunction = sortFunction,
            align = align, sortAscending = if (sortFunction != null) sortAscending else null)
}
