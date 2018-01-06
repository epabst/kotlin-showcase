package client

import client.component.UndoComponent
import client.ext.firebase.FirebaseRepositorySync
import client.util.LocalStorageRepository
import common.*
import common.util.*
import firebase.app.App
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
    val timerRepository: Repository<Timer> = if (true) TimerFirebaseRepository(firebaseApp) else InMemoryRepository()
    val allRepositories = listOf(timerRepository)

    init {
        UndoComponent.watch(timerRepository)
    }
}

open class TimerLocalStorageRepository : LocalStorageRepository<Timer, TimerJS>("timers", { it.toNormal() })
open class TimerFirebaseRepository(firebaseApp: App) : FirebaseRepositorySync<Timer, TimerJS>(TimerLocalStorageRepository(), "timers", { it.toNormal() }, firebaseApp)
