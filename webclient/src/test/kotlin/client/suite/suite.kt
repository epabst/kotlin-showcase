package client.suite

import client.JSModelTest
import client.UITest
import client.test.ToDoLocalStorageRepositoryTest
import client.util.JavascriptProviderTest
import client.util.LocalStorageRepositoryTest
import client.util.RepositoryCacheTest

/**
 * A QUnit test.  To run this, build this module and open runner.html in a browser.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/10/16
 * Time: 10:33 PM
 */

fun main(args: Array<String>) {
    JavascriptProviderTest.suite()
    JSModelTest.suite()
    LocalStorageRepositoryTest.suite()
    RepositoryCacheTest.suite()
    ToDoLocalStorageRepositoryTest.suite()
    UITest.suite()
//    UndoComponentTest.suite()
}
