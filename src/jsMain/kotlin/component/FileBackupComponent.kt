package component

import bootstrap.Button
import todo.model.Factory
import todo.appNameForFilesystem
import cordova.*
import platform.JavascriptProvider
import component.repository.LocalStorageRepository
import platform.handleError
import platform.handlingErrors
import org.w3c.dom.get
import org.w3c.files.*
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
object FileBackupComponent {

    private fun getBackupDataAsString(): String {
        val backupItems = Factory.allRepositories.flatMap { it.localStorageKeys }.map {
            Pair(it, localStorage[it]?.let { JSON.parse<Any>(it) })
        }.toTypedArray()
        return JSON.stringify(json(*backupItems))
    }

    fun initializeData(initialData: Any) {
        initializeDataFromJson(if (initialData is String) JSON.parse(initialData) else initialData)
    }

    fun initializeDataFromJson(initialDataJson: dynamic) {
        Factory.allRepositories.filterIsInstance<LocalStorageRepository<*, *>>().forEach { repository ->
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
                        directory.getFile("$appNameForFilesystem-${JavascriptProvider.now().toIsoDateString()}.json", flags, { fileEntry ->
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

    fun RBuilder.backupButton() {
        child(Button::class) {
            attrs.onClick = { createBackup() }
            attrs.variant = "secondary"
            +"Backup"
        }
    }
}
