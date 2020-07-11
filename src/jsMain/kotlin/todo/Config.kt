package todo

import PouchDB
import extensions.pouchdb.create
import util.handleError
import extensions.firebase.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import firebase.app.User
import firebase.app.initializeApp
import kotlinx.coroutines.await
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

    val toDoDb = PouchDB<ToDoJS>("toDos")

    init {
        GlobalScope.launch {
            if (toDoDb.allDocs().await().rows.isEmpty()) {
                toDoDb.create(ToDo("Write down some to-dos"), ToDoJS::toNormal)
            }
        }

        firebaseApp?.auth()?.useDeviceLanguage()
        firebaseApp?.auth()?.onUserChanged { _, newUser ->
            if (newUser == null) {
                println("newUser == null so calling signInAnonymouslyWithReuse()")
                firebaseApp.auth().signInAnonymouslyWithReuse().catch { handleError(it) }
            }
            val providerData = newUser?.providerData?.joinToString(";", "[", "]")
            console.log("uid=${newUser?.uid} isAnonymous=${newUser?.isAnonymous} displayName=${newUser?.displayName} email=${newUser?.email} photoURL=${newUser?.anyPhotoURL} $providerData")
        }
    }
}
