package client.test

/**
 * A QUnit test.  To run this, build this module and open runner.html in a browser.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/10/16
 * Time: 10:33 PM
 */

fun main(args: Array<String>) {
    JavascriptProviderTest.suite()
    RichDateTest.suite()
    JSModelTest.suite()
    LocalStorageRepositoryTest.suite()
    ToDoLocalStorageRepositoryTest.suite()
    UITest.suite()
//    UndoComponentTest.suite()
}
