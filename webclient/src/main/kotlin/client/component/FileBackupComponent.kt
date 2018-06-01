package client.component

import client.Factory
import client.appNameForFilesystem
import client.cordova.*
import client.util.LocalStorageRepository
import client.util.handleError
import client.util.handlingErrors
import net.yested.core.html.addClass2
import net.yested.core.html.nbsp
import net.yested.core.html.span
import net.yested.ext.bootstrap3.*
import net.yested.ext.moment.Moment
import net.yested.ext.moment.format
import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import org.w3c.files.*
import kotlin.browser.localStorage
import kotlin.browser.window
import kotlin.dom.appendText
import kotlin.js.json

/**
 * A component to create and restore backups.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 12/14/17
 * Time: 10:29 PM
 */
object FileBackupComponent {
    private val backupDateFormat = format {
        year.fourDigits + "-" + month.twoDigits + "-" + dayOfMonth.twoDigits + "-" +
                hour24.twoDigits + "-" + minutes.twoDigits + "-" + seconds.twoDigits
    }

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
            val entities = initialDataJson[repository.localStorageKey]
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
                val handleError: ((error: FileError) -> Unit) =  { error -> handleError(RuntimeException("error ${error.code}")) }
                window.resolveLocalFileSystemURL(cordova.file.documentsDirectory ?: cordova.file.externalDataDirectory, { directoryEntry ->
                    handlingErrors("createBackup/directoryEntry") {
                        val flags = object : Flags {}
                        flags.create = true
                        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
                        val directory = directoryEntry as DirectoryEntry
                        directory.getFile("${appNameForFilesystem}-${Moment.now().format(backupDateFormat)}.json", flags, { fileEntry ->
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

    fun HTMLElement.backupButton() {
        btsButton(onclick = { createBackup() }) {
            appendText("Backup")
            span { addClass2("hidden-tn"); nbsp(); appendText("Data") }
        }
    }
}
