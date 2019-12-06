@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")

package cordova

// Generated using ts2kt with https://github.com/DefinitelyTyped/DefinitelyTyped/blob/master/types/cordova-plugin-file/index.d.ts

import kotlin.js.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.files.*
import org.w3c.xhr.*

fun Window.requestFileSystem(type: LocalFileSystem, size: Number, successCallback: (fileSystem: FileSystem) -> Unit, errorCallback: ((fileError: FileError) -> Unit)? = null) {
    this.asDynamic().requestFileSystem(type, size, successCallback, errorCallback)
}
fun Window.resolveLocalFileSystemURL(url: String, successCallback: (entry: Entry) -> Unit, errorCallback: ((error: FileError) -> Unit)? = null) {
    this.asDynamic().resolveLocalFileSystemURL(url, successCallback, errorCallback)
}
fun Window.resolveLocalFileSystemURI(uri: String, successCallback: (entry: Entry) -> Unit, errorCallback: ((error: FileError) -> Unit)? = null) {
    this.asDynamic().resolveLocalFileSystemURI(uri, successCallback, errorCallback)
}
val Window.PERSISTENT: Number get() = 0
val Window.TEMPORARY: Number get() = 1
external interface FileSystem {
    var name: String
    var root: DirectoryEntry
}
external interface Entry {
    var isFile: Boolean
    var isDirectory: Boolean
    var name: String
    var fullPath: String
    var fileSystem: FileSystem
    var nativeURL: String
    fun getMetadata(successCallback: (metadata: Metadata) -> Unit, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
    fun moveTo(parent: DirectoryEntry, newName: String? = definedExternally /* null */, successCallback: ((entry: Entry) -> Unit)? = definedExternally /* null */, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
    fun copyTo(parent: DirectoryEntry, newName: String? = definedExternally /* null */, successCallback: ((entry: Entry) -> Unit)? = definedExternally /* null */, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
    fun toURL(): String
    fun toInternalURL(): String
    fun remove(successCallback: () -> Unit, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
    fun getParent(successCallback: (entry: Entry) -> Unit, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
}
external interface Metadata {
    var modificationTime: Date
    var size: Number
}
external interface DirectoryEntry : Entry {
    fun createReader(): DirectoryReader
    fun getFile(path: String, options: Flags? = definedExternally /* null */, successCallback: ((entry: FileEntry) -> Unit)? = definedExternally /* null */, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
    fun getDirectory(path: String, options: Flags? = definedExternally /* null */, successCallback: ((entry: DirectoryEntry) -> Unit)? = definedExternally /* null */, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
    fun removeRecursively(successCallback: () -> Unit, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
}
external interface Flags {
    var create: Boolean? get() = definedExternally; set(value) = definedExternally
    var exclusive: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface DirectoryReader {
    fun readEntries(successCallback: (entries: Array<Entry>) -> Unit, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
}
external interface FileEntry : Entry {
    fun createWriter(successCallback: (writer: FileWriter) -> Unit, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
    fun file(successCallback: (file: File) -> Unit, errorCallback: ((error: FileError) -> Unit)? = definedExternally /* null */)
}
external interface FileSaver : EventTarget {
    fun abort()
    var readyState: Number
    var onwritestart: (event: ProgressEvent) -> Unit
    var onprogress: (event: ProgressEvent) -> Unit
    var onwrite: (event: ProgressEvent) -> Unit
    var onabort: (event: ProgressEvent) -> Unit
    var onerror: (event: ProgressEvent) -> Unit
    var onwriteend: (event: ProgressEvent) -> Unit
    var error: Error
}
external interface FileWriter : FileSaver {
    var position: Number
    var length: Number
    fun write(data: String)
    fun write(data: Blob)
    fun seek(offset: Number)
    fun truncate(size: Number)
}
external interface FileError {
    var code: Number
}
external interface DirectorySpecs {
    var applicationDirectory: String
    var applicationStorageDirectory: String
    var dataDirectory: String
    var cacheDirectory: String
    var externalApplicationStorageDirectory: String
    var externalDataDirectory: String
    var externalCacheDirectory: String
    var externalRootDirectory: String
    var tempDirectory: String
    var syncedDataDirectory: String?
    var documentsDirectory: String?
    var sharedDirectory: String
}
val Cordova.file: DirectorySpecs get() = this.asDynamic().file
external enum class LocalFileSystem {
    PERSISTENT /* = 0 */,
    TEMPORARY /* = 1 */
}
