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

fun Repository<AccessSpace>.populateSpaceIdAndCopyLinkToClipboard(accessProperty: Property<Access>, spaceName: ReadOnlyProperty<String>, destinationHash: String) {
    var access = accessProperty.get()
    if (access.protectionLevel == ProtectionLevel.PROTECTED) {
        access = withNewSpaceIfNeeded(access, spaceName.get())
        accessProperty.set(access)
        val currentLocationHash = window.location.hash
        copyTextToClipboard(window.location.href.replace(currentLocationHash, AccessSpaceModel.toUrl(access.accessSpaceId!!, destinationHash)))
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
    val accessSpaceIds = accessSpaceRepository.idListProperty()

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

fun BtsFormContext.accessControl(access: Property<Access>,
                                 selectableAccessList: ReadOnlyProperty<List<Access>>,
                                 spaceName: ReadOnlyProperty<String>,
                                 destinationHash: ReadOnlyProperty<String>,
                                 accessSpaceRepository: Repository<AccessSpace>): HTMLDivElement {
    return btsFormItemSimple(state = Property(State.Default), label = "Access Level") {
        val singleSelectInput = singleSelectInput(access, selectableAccessList) {
            textContent = it.protectionLevel.label
        }
        selectableAccessList.onNext { singleSelectInput.disabled = it.size <= 1 }
        val copied = access.mapAsDefault { false }
        btsButton(size = ButtonSize.Default, look = ButtonLook.Info,
                onclick = {
                    it.stopImmediatePropagation()
                    accessSpaceRepository.populateSpaceIdAndCopyLinkToClipboard(access, spaceName, destinationHash.get())
                    copied.set(true)
                }) {
            access.onNext { visible = it.protectionLevel == ProtectionLevel.PROTECTED }
            appendText("Copy Link")
        }
        nbsp()
        span { copied.onNext { textContent = if (it) "Copied." else "" } }
    }
}
