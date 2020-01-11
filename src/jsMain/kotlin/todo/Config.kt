package todo

import component.FileBackupComponent
import component.UndoComponent
import platform.handleError
import component.firebase.*
import component.repository.LocalStorageRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.get
import firebase.app.User
import firebase.app.initializeApp
import todo.model.ToDo
import todo.model.ToDoJS
import todo.model.toNormal
import kotlin.browser.localStorage
import kotlin.js.json

object Config {
    // To use firebase, add your app at https://console.firebase.google.com/
    private val firebaseConfig = json(
        "apiKey" to "ZZZFirebaseApiKeyZZZ",
        "authDomain" to "ZZZAppIdZZZ.firebaseapp.com",
        "databaseURL" to "https://ZZZAppIdZZZ.firebaseio.com",
        "projectId" to "ZZZAppIdZZZ",
        "storageBucket" to "",
        "messagingSenderId" to "ZZZFirebaseMessagingSenderIdZZZ"
    )
    private val firebaseApp = if ((firebaseConfig["authDomain"] as String?)?.contains("ZZZ") != false) {
        null
    } else {
        initializeApp(firebaseConfig)
    }
    var user: User? = null

    val toDoRepository = LocalStorageRepository<ToDo, ToDoJS>("toDos", "toDos") { it.toNormal() }
    private val allRepositories = listOf(toDoRepository)
    val fileBackupComponent = FileBackupComponent(appNameForFilesystem, allRepositories = allRepositories)

    init {
        if (toDoRepository.localStorageKeys.all { localStorage[it] == null }) {
            GlobalScope.launch {
                val client = HttpClient()
                val initialData = client.get<String>("initial-data.json")
                fileBackupComponent.initializeData(initialData)
            }
        }

        UndoComponent.watch(toDoRepository)
        firebaseApp?.auth()?.useDeviceLanguage()
        firebaseApp?.auth()?.onUserChanged { _, newUser ->
            if (newUser == null) {
                println("newUser == null so calling signInAnonymously()")
                firebaseApp.auth().signInAnonymously().catch { handleError(it) }
            }
            val providerData = newUser?.providerData?.joinToString(";", "[", "]")
            console.log("uid=${newUser?.uid} isAnonymous=${newUser?.isAnonymous} displayName=${newUser?.displayName} email=${newUser?.email} photoURL=${newUser?.anyPhotoURL} $providerData")
        }
    }
}
