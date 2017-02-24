package client.test

import client.ToDoLocalStorageRepository
import client.JavascriptProvider
import common.*

/**
 * A test for [ToDoLocalStorageRepository].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
object ToDoLocalStorageRepositoryTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider

        it("should read all To-Dos") {
            val repository = ToDoLocalStorageRepository()
            val itemId1 = repository.save(null, ToDo("Item #1"))
            val itemId2 = repository.save(null, ToDo("Item #2"))
            val itemId3 = repository.save(null, ToDo("Item #3"))

            try {
                val reloadedRepository = ToDoLocalStorageRepository()
                val allItems = reloadedRepository.list()
                allItems.mustBe(repository.list())
            } finally {
                repository.remove(itemId1)
                repository.remove(itemId2)
                repository.remove(itemId3)
            }
        }
    }
}
