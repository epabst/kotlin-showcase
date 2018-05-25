package client

import client.component.AccessSpaceModel
import client.component.FileBackupComponent
import client.component.UndoComponent
import client.ext.firebase.*
import client.util.cached
import client.util.handleError
import common.*
import common.util.*
import firebase.app.App
import net.yested.core.properties.Property
import net.yested.core.properties.ReadOnlyProperty
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
    private val firebaseApp = if ((firebaseConfig["authDomain"] as String?)?.contains("ZZZ") != false) {
        null
    } else {
        firebase.initializeApp(firebaseConfig)
    }

    val userId = Property<String?>(null)
    val accessSpaceModel = AccessSpaceModel(firebaseApp)
    val accessSpaces = accessSpaceModel.accessSpaces
    val accessSpaceIds = accessSpaceModel.accessSpaceIds
    val accessSpaceRepository = accessSpaceModel.accessSpaceRepository
    val toDoRepository = protectionLevelWithGlobalChangeLogRepository<ToDo,ToDoJS>("toDoList", userId, accessSpaceIds, { it.toNormal() }, firebaseApp).cached
    val allRepositories = listOf(accessSpaceRepository, toDoRepository)

    init {
        firebaseApp?.auth()?.onAuthStateChanged({ user ->
            console.log("uid=${user?.uid} isAnonymous=${user?.isAnonymous} displayName=${user?.displayName} email=${user?.email} photoURL=${user?.photoURL}")
            userId.set(user?.uid)
        }, opt_error = { handleError(Exception("Error ${it.code}: ${it.message}")) })

        firebaseApp?.auth()?.signInAnonymously()

        if (toDoRepository.localStorageKeys.all { localStorage[it] == null }) {
            yestedJQuery.get<Any>("initial-data.json") { initialData ->
                FileBackupComponent.initializeData(initialData)
            }
        }

        UndoComponent.watch(toDoRepository)
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

