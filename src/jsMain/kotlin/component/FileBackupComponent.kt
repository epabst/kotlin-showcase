package component

import component.bootstrap.textButton
import component.repository.LocalStorageRepository
import component.repository.Repository
import cordova.*
import org.w3c.dom.get
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import platform.PlatformProvider
import platform.handleError
import platform.handlingErrors
import react.RBuilder
import kotlin.browser.localStorage
import kotlin.browser.window
import kotlin.js.json

/**
 * A component to create and restore backups.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 12/14/17
 * Time: 10:29 PM
 */
class FileBackupComponent(val appNameForFilesystem: String, val allRepositories: List<Repository<*>>) {

    private fun getBackupDataAsString(): String {
        val backupItems = allRepositories.flatMap { it.localStorageKeys }.map {
            Pair(it, localStorage[it]?.let { JSON.parse<Any>(it) })
        }.toTypedArray()
        return JSON.stringify(json(*backupItems))
    }

    suspend fun initializeData(initialData: Any) {
        initializeDataFromJson(if (initialData is String) JSON.parse(initialData) else initialData)
    }

    suspend fun initializeDataFromJson(initialDataJson: dynamic) {
        allRepositories.filterIsInstance<LocalStorageRepository<*, *>>().forEach { repository ->
            val entities = initialDataJson[repository.relativePath]
            if (entities != null) {
                repository.replaceAll(entities)
            }
        }
    }

    fun createBackup() {
        handlingErrors("createBackup") {
            val cordova = window.cordova
            val backupData = getBackupDataAsString()
            if (cordova != null) {
                val handleError: ((error: FileError) -> Unit) = { error -> handleError(RuntimeException("error ${error.code}")) }
                window.resolveLocalFileSystemURL(cordova.file.documentsDirectory
                        ?: cordova.file.externalDataDirectory, { directoryEntry ->
                    handlingErrors("createBackup/directoryEntry") {
                        val flags = object : Flags {}
                        flags.create = true
                        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
                        val directory = directoryEntry as DirectoryEntry
                        directory.getFile("$appNameForFilesystem-${PlatformProvider.now().toIsoDateString()}.json", flags, { fileEntry ->
                            handlingErrors("createBackup/fileEntry") {
                                fileEntry.createWriter({ writer ->
                                    handlingErrors("createBackup/writer") {
                                        writer.onerror = { window.alert("writer.error") }
                                        writer.onwriteend = {
                                            window.alert("Backup created: ${fileEntry.fullPath}")
                                        }
                                        val dataObj = Blob(arrayOf(backupData), BlobPropertyBag(type = "application/json"))
                                        writer.write(dataObj)
                                    }
                                }, handleError)
                            }
                        }, handleError)
                    }
                }, handleError)
            } else {
                console.info(backupData)
                window.alert("Backup can be found in the brower's console.")
            }
        }
    }
}

fun RBuilder.backupButton(fileBackupComponent: FileBackupComponent) {
    textButton("Backup") { fileBackupComponent.createBackup() }
}
