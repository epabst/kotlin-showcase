package client

import client.component.UndoComponent
import client.util.LocalStorageRepository
import common.*
import common.util.*

/**
 * Persistent Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */

object Factory {
    val toDoRepository: Repository<ToDo>
        = if (true) ToDoLocalStorageRepository else ToDoInMemoryRepository
    val allRepositories = listOf(toDoRepository)

    init {
        UndoComponent.watch(toDoRepository)
    }
}

open class ToDoLocalStorageRepository : LocalStorageRepository<ToDo, ToDoJS>("toDoList", { it.toNormal() }) {
    companion object : ToDoLocalStorageRepository() {
        init {
            if (!isInitialized()) {
                save(ToDo("Write down some to-dos"))
            }
        }
    }
}
