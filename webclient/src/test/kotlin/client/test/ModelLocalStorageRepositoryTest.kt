package client.test

import client.ToDoJS
import client.toNormal
import client.util.*
import common.*
import common.util.*
import kotlin.test.Test

/**
 * A test for [LocalStorageRepository] with the model.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/16/16
 * Time: 6:17 AM
 */
class ModelLocalStorageRepositoryTest {
    init {
        PlatformProvider.instance = JavascriptProvider
    }

    @Test
    fun itShouldReadAllToDos() {
        val repository = LocalStorageRepository<ToDo, ToDoJS>("toDoList", { it.toNormal() })
        val itemId1 = repository.save(null, ToDo("Item #1"))
        val itemId2 = repository.save(null, ToDo("Item #2"))
        val itemId3 = repository.save(null, ToDo("Item #3"))

        try {
            val reloadedRepository = LocalStorageRepository<ToDo, ToDoJS>("toDoList", { it.toNormal() })
            val allItems = reloadedRepository.list()
            allItems.mustBe(repository.list())
        } finally {
            repository.remove(itemId1)
            repository.remove(itemId2)
            repository.remove(itemId3)
        }
    }
}
