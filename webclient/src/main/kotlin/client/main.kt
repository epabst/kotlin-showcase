package client

import common.Hello
import net.yested.el
import net.yested.Div
import net.yested.with

/**
 * The main entrypoint of the app.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/17/16
 * Time: 6:25 AM
 */
fun main(args: Array<String>) {

    initializeForCordova()

    //here we create some div with a header and a list
    val div = Div() with {
        h1 {
            +Hello.hello() //plus add given text as textContent to HTML element (h1)
        }
        ul {
            li {
                strong {
                    +"Bolded text"
                }
            }
            li {
                a(href = "http://www.yested.net") {
                    +"Link to Framework"
                }
            }
        }
    }

    //when we have constructed a DOM, we can take a parent element (via div.element)
    //and append it as a child to "app" div in HTML page
    el("page")!!.appendChild(div.element)

}