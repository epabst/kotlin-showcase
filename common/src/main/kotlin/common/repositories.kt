package common

/**
 * Persistence Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:01 AM
 */
object ToDoInMemoryRepository : InMemoryRepository<ToDo>(defaultList = arrayListOf(ToDo("Write down some to-dos")))
