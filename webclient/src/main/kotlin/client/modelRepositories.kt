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
    // To use firebase, add your app at https://console.firebase.google.com/
    val firebaseConfig = json(
            "apiKey" to "ZZZFirebaseApiKeyZZZ",
            "authDomain" to "ZZZAppIdZZZ.firebaseapp.com",
            "databaseURL" to "https://ZZZAppIdZZZ.firebaseio.com",
            "projectId" to "ZZZAppIdZZZ",
            "storageBucket" to "",
            "messagingSenderId" to "ZZZFirebaseMessagingSenderIdZZZ")
    val firebaseApp = firebase.initializeApp(firebaseConfig)

    val toDoRepository: Repository<ToDo> = when ("localStorage") {
        "localStorage" -> ToDoLocalStorageRepository()
        "firebase" -> ToDoFirebaseRepository(firebaseApp)
        else -> InMemoryRepository()
    }
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
