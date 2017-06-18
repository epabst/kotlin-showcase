package common

import common.util.ID
import common.util.InMemoryRepository
import common.util.Repository

/**
 * Persistence Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:01 AM
 */
object ToDoInMemoryRepository : InMemoryRepository<ToDo>()
