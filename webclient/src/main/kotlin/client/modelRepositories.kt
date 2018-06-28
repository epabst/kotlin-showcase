package client

import client.component.AccessSpaceModel
import client.component.FileBackupComponent
import client.component.UndoComponent
import client.ext.firebase.*
import client.util.cached
import common.*
import common.util.*
import firebase.User
import firebase.app.App
import net.yested.core.properties.Property
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.mapAsDefault
import net.yested.core.properties.onChange
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
    val firebaseApp = if ((firebaseConfig["authDomain"] as String?)?.contains("ZZZ") != false) {
        null
    } else {
        firebase.initializeApp(firebaseConfig)
    }

    val user = Property<User?>(null)
    val userId = user.mapAsDefault { it?.uid }
    val accessSpaceModel = AccessSpaceModel(firebaseApp)
    val accessSpaceRepository = accessSpaceModel.accessSpaceRepository
    val accessSpaces = accessSpaceRepository.listProperty()
    val accessSpaceIds = accessSpaceRepository.idListProperty()
    val toDoRepository = protectionLevelWithGlobalChangeLogRepository<ToDo,ToDoJS>("toDoList", userId, accessSpaceIds, { it.toNormal() }, firebaseApp).cached
    val allRepositories = listOf(accessSpaceRepository, toDoRepository)

    init {
        if (toDoRepository.localStorageKeys.all { localStorage[it] == null }) {
            yestedJQuery.get<Any>("initial-data.json") { initialData ->
                FileBackupComponent.initializeData(initialData)
            }
        }

        UndoComponent.watch(toDoRepository)

        val indirectUser = Property<User?>(null)
        indirectUser.onChange { oldUser, newUser ->
            // If this is the first time authenticating, including with Firebase anonymous authentication,
            // move any localStorage data that is not tied to a user ID to be tied to one.
            // Since non of the Repository instances have a userId yet, no data should be deleted from Firebase.
            if (oldUser == null) {
                val dataFromOldUser = removePrivateDataFromOldUser()
                user.set(newUser)
                addPrivateDataToNewUser(dataFromOldUser)
            } else {
                user.set(newUser)
            }
        }
        FirebaseAuthentication.initialize(indirectUser)
    }

    fun addPrivateDataToNewUser(accessSpaceList: List<AccessSpace>) {
        UndoComponent.undoable("Move private data to new user", "Remove private data from new user") {
            accessSpaceList.forEach { Factory.accessSpaceRepository.save(it) }
        }
    }

    fun removePrivateDataFromOldUser(): List<AccessSpace> {
        return UndoComponent.undoable("Move private data from old user", "Restore private data to old user") {
            val data = Factory.accessSpaceRepository.list()
            data.forEach { Factory.accessSpaceRepository.remove(it) }
            data
        }
    }
}

fun <T : ProtectedWithID<T>,JS> protectionLevelWithGlobalChangeLogRepository(relativePath: String,
                                                                             userId: Property<String?>,
                                                                             accessSpaceIds: ReadOnlyProperty<List<ID<AccessSpace>>>,
                                                                             toData: (JS) -> T,
                                                                             firebaseApp: App?) : Repository<T> {
    val global = GlobalPathsSpecifier<T>(relativePath)
    val protected = ProtectedPathsSpecifier<T>(relativePath, accessSpaceIds)
    val private = PrivatePathsSpecifier<T>(relativePath, userId)
    return protectionLevelWithChangesRepository(global, protected, private, userId, relativePath, toData, firebaseApp)
}

