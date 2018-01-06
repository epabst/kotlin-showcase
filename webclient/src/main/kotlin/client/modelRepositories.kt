package client

import client.component.FileBackupComponent
import client.component.UndoComponent
import client.ext.firebase.FirebaseRepositorySync
import client.util.LocalStorageRepository
import common.*
import common.util.*
import firebase.app.App
import net.yested.ext.jquery.yestedJQuery
import kotlin.js.json

/**
 * Persistent Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */

object Factory {
    val firebaseConfig = json(
            "apiKey" to "AIzaSyD42kElCiyIjKGsgiy63lXdK7-pfQJlGPk",
            "authDomain" to "jackdance-toothbrush-time.firebaseapp.com",
            "databaseURL" to "https://jackdance-toothbrush-time.firebaseio.com",
            "projectId" to "jackdance-toothbrush-time",
            "storageBucket" to "",
            "messagingSenderId" to "99366826872")
    val firebaseApp = firebase.initializeApp(firebaseConfig)
    val toDoRepository: Repository<ToDo> = if (true) ToDoFirebaseRepository(firebaseApp) else InMemoryRepository()
    val allRepositories = listOf(toDoRepository)

    init {
        if (toDoRepository is ToDoLocalStorageRepository) {
            if (!toDoRepository.isInitialized()) {
                yestedJQuery.get<Any>("initial-data.json") { initialData ->
                    FileBackupComponent.initializeData(initialData)
                }
            }
        }

        UndoComponent.watch(toDoRepository)
    }
}

open class ToDoLocalStorageRepository : LocalStorageRepository<ToDo, ToDoJS>("toDoList", { it.toNormal() })
open class ToDoFirebaseRepository(firebaseApp: App) : FirebaseRepositorySync<ToDo, ToDoJS>(ToDoLocalStorageRepository(), "toDos", { it.toNormal() }, firebaseApp)
