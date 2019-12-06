package todo.model

import component.access.AccessSpaceModel
import component.FileBackupComponent
import component.UndoComponent
import platform.handleError
import component.access.AccessSpace
import component.access.ProtectedWithID
import component.firebase.*
import firebase.User
import firebase.app.App
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import firebase.auth.Auth
import org.w3c.dom.get
import component.repository.Repository
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
    val firebaseApp = if ((firebaseConfig["authDomain"] as String?)?.contains("ZZZ") != false) {
        null
    } else {
        firebase.initializeApp(firebaseConfig)
    }
    var user: User? = null

    private val accessSpaceModel = AccessSpaceModel(firebaseApp)
    private val accessSpaceRepository = accessSpaceModel.accessSpaceRepository
    val toDoRepository = protectionLevelWithGlobalChangeLogRepository<ToDo, ToDoJS>("toDoList", firebaseApp?.auth(), accessSpaceRepository, { it.toNormal() }, firebaseApp)
    val allRepositories = listOf(accessSpaceRepository, toDoRepository)

    init {
        if (toDoRepository.localStorageKeys.all { localStorage[it] == null }) {
            GlobalScope.launch {
                val client = HttpClient()
                val initialData = client.get<String>("initial-data.json")
                FileBackupComponent.initializeData(initialData)
            }
        }

        UndoComponent.watch(toDoRepository)
        firebaseApp?.auth()?.useDeviceLanguage()
        firebaseApp?.auth()?.onAuthStateChanged({ newUser ->
            if (newUser == null) {
                println("newUser == null so calling signInAnonymously()")
                firebaseApp.auth().signInAnonymously().then { error ->
                    handleError(Error(error.message))
                }
            }
            val providerData = newUser?.providerData?.joinToString(";", "[", "]") { "uid=${it?.uid} displayName=${it?.displayName} photoURL=${it?.photoURL} email=${it?.email}" }
            console.log("uid=${newUser?.uid} isAnonymous=${newUser?.isAnonymous} displayName=${newUser?.displayName} email=${newUser?.email} photoURL=${newUser?.anyPhotoURL} $providerData")
        }, opt_error = {
            handleError(Exception("Error ${it.code}: ${it.message}"))
        })

        firebaseApp?.auth()?.onUserChanged { oldUser, newUser ->
            // If this is the first time authenticating, including with Firebase anonymous authentication,
            // move any localStorage data that is not tied to a user ID to be tied to one.
            // Since non of the Repository instances have a userId yet, no data should be deleted from Firebase.
            if (oldUser == null) {
                val dataFromOldUser = removePrivateDataFromOldUser()
                user = newUser
                addPrivateDataToNewUser(dataFromOldUser)
            } else {
                user = newUser
            }
        }
    }

    private fun addPrivateDataToNewUser(accessSpaceList: List<AccessSpace>) {
        UndoComponent.undoable("Move private data to new user", "Remove private data from new user") {
            accessSpaceList.forEach { accessSpaceRepository.save(it) }
        }
    }

    private fun removePrivateDataFromOldUser(): List<AccessSpace> {
        return UndoComponent.undoable("Move private data from old user", "Restore private data to old user") {
            val data = accessSpaceRepository.list()
            data.forEach { accessSpaceRepository.remove(it) }
            data
        }
    }
}

fun <T : ProtectedWithID<T>,JS> protectionLevelWithGlobalChangeLogRepository(relativePath: String,
                                                                             firebaseAuth: Auth?,
                                                                             accessSpaceRepository: Repository<AccessSpace>,
                                                                             toData: (JS) -> T,
                                                                             firebaseApp: App?) : Repository<T> {
    val global = GlobalPathsSpecifier<T>(relativePath)
    val protected = ProtectedPathsSpecifier<T>(relativePath, accessSpaceRepository)
    val private = PrivatePathsSpecifier<T>(relativePath, firebaseAuth)
    return protectionLevelWithChangesRepository(global, protected, private, firebaseAuth, relativePath, toData, firebaseApp)
}
