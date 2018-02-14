package client

import client.component.FileBackupComponent
import client.component.UndoComponent
import client.ext.firebase.FirebaseAndLocalRepository
import common.ToDo
import net.yested.ext.jquery.yestedJQuery
import org.w3c.dom.get
import kotlin.browser.localStorage
import kotlin.js.json

/**
 * Persistent Repositories.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/9/16
 * Time: 6:19 AM
 */

object Factory {
    // To use firebase, add your app at https://console.firebase.google.com/
    private val firebaseConfig = json(
            "apiKey" to "ZZZFirebaseApiKeyZZZ",
            "authDomain" to "ZZZAppIdZZZ.firebaseapp.com",
            "databaseURL" to "https://ZZZAppIdZZZ.firebaseio.com",
            "projectId" to "ZZZAppIdZZZ",
            "storageBucket" to "",
            "messagingSenderId" to "ZZZFirebaseMessagingSenderIdZZZ")
    private val firebaseApp = firebase.initializeApp(firebaseConfig)

    val toDoRepository = FirebaseAndLocalRepository<ToDo,ToDoJS>("toDoList", "toDoList", { it.toNormal() }, firebaseApp)
    val allRepositories = listOf(toDoRepository)

    init {
        if (toDoRepository.localStorageKeys.all { localStorage[it] == null }) {
            yestedJQuery.get<Any>("initial-data.json") { initialData ->
                FileBackupComponent.initializeData(initialData)
            }
        }

        UndoComponent.watch(toDoRepository)
    }
}
