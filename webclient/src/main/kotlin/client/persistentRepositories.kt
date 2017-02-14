package client

import common.*
import java.util.*
import kotlin.browser.localStorage

/**
 * Persistent Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */

object Factory {
    val toDoRepository: Repository<ToDo>
        = if (true) ToDoLocalStorageRepository else ToDoInMemoryRepository

    init {
        UndoComponent.watch(toDoRepository)
    }
}

abstract class LocalStorageRepository<T : WithID<T>,JS>(private val localStorageKey: String, toData: (JS) -> T) : Repository<T> {
    protected abstract val defaultList: List<T>
    private val listeners: ArrayList<RepositoryListener<T>> = ArrayList(4)

    override fun generateID(): ID {
        return ID((Math.random() * Long.MAX_VALUE).toLong())
    }

    override val list: ArrayList<T> by lazy {
        val listString = localStorage.getItem(localStorageKey)
        if (listString != null) {
            try {
//                println(listString)
                val jsArray = JSON.parse<Array<JS>>(listString)
                ArrayList(jsArray.map { toData(it) })
            } catch (t: Throwable) {
                println("Throwable: " + t)
                ArrayList(defaultList.map { withID(it) })
            }
        }
        else {
            ArrayList(defaultList.map { withID(it) })
        }
    }

    override fun save(original: T?, replacement: T): ID {
        val replacementWithID = putIntoList(list, replacement, original)
        val newID = replacementWithID.getID()!!
        store()
        listeners.forEach { it.onSaved(original, replacementWithID) }
        return newID
    }

    override fun remove(item: T) {
        list.remove(item)
        store()
        listeners.forEach { it.onRemoved(item) }
    }

    override fun addListener(listener: RepositoryListener<T>) {
        listeners += listener
    }

    private fun store() {
        localStorage.setItem(localStorageKey, JSON.stringify(list))
    }
}

open class ToDoLocalStorageRepository : LocalStorageRepository<ToDo, ToDoJS>("toDoList", { it.toNormal() }) {
    override val defaultList = ToDoInMemoryRepository.defaultList

    companion object : ToDoLocalStorageRepository()
}