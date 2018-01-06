package client.test

import client.TimerLocalStorageRepository
import client.nowDateTime
import client.util.*
import common.*
import common.util.*

/**
 * A test for [TimerLocalStorageRepository].
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
object ToDoLocalStorageRepositoryTest {
    fun suite() {
        PlatformProvider.instance = JavascriptProvider

        it("should read all Timers") {
            val repository = TimerLocalStorageRepository()
            val itemId1 = repository.save(null, Timer("Item #1", nowDateTime(), 6))
            val itemId2 = repository.save(null, Timer("Item #2", nowDateTime(), 6))
            val itemId3 = repository.save(null, Timer("Item #3", nowDateTime(), 6))

            try {
                val reloadedRepository = TimerLocalStorageRepository()
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
