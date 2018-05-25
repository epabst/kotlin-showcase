package client.component

import client.Factory
import client.ext.firebase.FirebaseRepositorySync
import client.ext.firebase.PrivatePathsSpecifier
import client.util.*
import common.util.*
import firebase.app.App
import net.yested.core.html.nbsp
import net.yested.core.html.span
import net.yested.core.properties.Property
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.map
import net.yested.core.properties.mapAsDefault
import net.yested.ext.bootstrap3.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLTextAreaElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.appendText

/**
 * An entity that tracks access spaces that the current user has access to.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 4/19/18
 * Time: 9:57 PM
 */
external interface AccessSpaceJS {
    val name: String
    val id: IDJS?
}

fun AccessSpaceJS.toNormal(): AccessSpace = AccessSpace(name, id?.toNormal())

external interface AccessJS {
    val protection: String
    /** @deprecated use [accessSpaceId] */
    val privateViaLinkSpaceId: IDJS?
    val accessSpaceId: IDJS?
}

fun AccessJS.toNormal(): Access = Access(protectionLevel.name, privateViaLinkSpaceId?.toNormal() ?: accessSpaceId?.toNormal())

private val AccessJS.protectionLevel: ProtectionLevel get() {
    return when (protection) {
        "PUBLIC" -> ProtectionLevel.GLOBAL
        "PRIVATE_VIA_LINK" -> ProtectionLevel.PROTECTED
        else -> ProtectionLevel.valueOf(protection)
    }
}

fun Repository<AccessSpace>.populateSpaceIdAndCopyLinkToClipboard(accessSummaryProperty: Property<AccessSummary>, spaceName: ReadOnlyProperty<String>, destinationHash: String) {
    var accessSummary = accessSummaryProperty.get()
    if (accessSummary.protectionLevel == ProtectionLevel.PROTECTED) {
        accessSummary = withNewSpaceIfNeeded(accessSummary, spaceName.get())
        accessSummaryProperty.set(accessSummary)
        val currentLocationHash = window.location.hash
        copyTextToClipboard(window.location.href.replace(currentLocationHash, AccessSpaceModel.toUrl(accessSummary.accessSpaceId!!, destinationHash)))
    }
}

fun Repository<AccessSpace>.withNewSpaceIfNeeded(accessSummary: AccessSummary, spaceName: String): AccessSummary {
    return if (accessSummary.protectionLevel != ProtectionLevel.PROTECTED || accessSummary.accessSpaceId != null) {
        accessSummary
    } else {
        accessSummary.copy(accessSpace = saveAndGet(AccessSpace(spaceName)))
    }
}

fun Repository<AccessSpace>.withNewSpaceIfNeeded(access: Access, spaceName: String): Access {
    return if (access.protectionLevel != ProtectionLevel.PROTECTED || access.accessSpaceId != null) {
        access
    } else {
        access.copy(accessSpaceId = save(AccessSpace(spaceName)))
    }
}

fun Repository<AccessSpace>.addIfMissing(accessSpaceId: ID<AccessSpace>) {
    val accessSpaceIds = list().map { it.id }
    if (!accessSpaceIds.contains(accessSpaceId)) {
        save(AccessSpace("Shared " + PlatformProvider.instance.now().toDisplayDateTimeString(), accessSpaceId))
    }
}

fun Repository<AccessSpace>.toAccessSummary(access: Access): AccessSummary {
    return AccessSummary(access.protectionLevel, access.accessSpaceId?.let { find(it) })
}

private fun copyTextToClipboard(text: String) {
    val element = document.createElement("textarea") as HTMLTextAreaElement
    val body = document.body!!
    body.appendChild(element)
    element.value = text
    element.select()
    document.execCommand("copy")
    body.removeChild(element)
}

class AccessSpaceModel(firebaseApp: App?) {
    val accessSpaceRepository = if (firebaseApp == null) {
        EmptyRepository<AccessSpace>().cached
    } else {
        val pathsSpecifier = PrivatePathsSpecifier<AccessSpace>("accessSpaceList", Factory.userId)
        FirebaseRepositorySync<AccessSpace, AccessSpaceJS>(pathsSpecifier, { it.toNormal() }, firebaseApp).cached
    }
    val accessSpaces = accessSpaceRepository.listProperty()
    val accessSpaceIds = accessSpaces.map { it.map { it.id }.filterNotNull() }

    fun addIfMissingAndExtractNewHash(hash: Array<String>): String {
        val accessSpaceId = hash[1].toID<AccessSpace>()!!
        accessSpaceRepository.addIfMissing(accessSpaceId)
        return hash.drop(2).joinToString("/")
    }

    companion object {
        fun toUrl(accessSpaceId: ID<AccessSpace>, hash: String): String {
            return "#accessSpace/$accessSpaceId/$hash"
        }
    }
}

fun BtsFormContext.accessChooser(accessSummary: Property<AccessSummary>,
                                 selectableAccessSummaryList: ReadOnlyProperty<List<AccessSummary>>,
                                 spaceName: ReadOnlyProperty<String>,
                                 destinationHash: ReadOnlyProperty<String>,
                                 accessSpaceRepository: Repository<AccessSpace>): HTMLDivElement {
    return btsFormItemSimple(state = Property(State.Default), label = "Access Level") {
        val singleSelectInput = singleSelectInput(accessSummary, selectableAccessSummaryList) { accessSummary ->
            when {
                accessSummary.accessSpace == null && accessSummary.protectionLevel == ProtectionLevel.PROTECTED ->
                    spaceName.onNext {
                        textContent = accessSummary.protectionLevel.label + ": (new) " + it
                    }
                accessSummary.accessSpace == null ->
                    textContent = accessSummary.protectionLevel.label + ""
                else ->
                    textContent = accessSummary.protectionLevel.label + ": " + accessSummary.accessSpace.name
            }
        }
        selectableAccessSummaryList.onNext { singleSelectInput.disabled = it.size <= 1 }
        val copied = accessSummary.mapAsDefault { false }
        btsButton(size = ButtonSize.Default, look = ButtonLook.Info,
                onclick = {
                    it.stopImmediatePropagation()
                    accessSpaceRepository.populateSpaceIdAndCopyLinkToClipboard(accessSummary, spaceName, destinationHash.get())
                    copied.set(true)
                }) {
            accessSummary.onNext { visible = it.protectionLevel == ProtectionLevel.PROTECTED }
            appendText("Copy Link")
        }
        nbsp()
        span { copied.onNext { textContent = if (it) "Copied." else "" } }
    }
}
