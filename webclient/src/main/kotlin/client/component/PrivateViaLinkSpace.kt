package client.component

import client.Factory
import client.ext.firebase.PrivateFirebaseRepository
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
 * An entity that tracks private links spaces that the current user has access to.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 4/19/18
 * Time: 9:57 PM
 */
external interface PrivateViaLinkSpaceJS {
    val name: String
    val id: IDJS?
}

fun PrivateViaLinkSpaceJS.toNormal(): PrivateViaLinkSpace = PrivateViaLinkSpace(name, id?.toNormal())

external interface AccessJS {
    val protection: String
    val privateViaLinkSpaceId: IDJS?
}

fun AccessJS.toNormal(): Access = Access(protection, privateViaLinkSpaceId?.toNormal())

fun PrivateViaLinkSpaceRepository(userId: ReadOnlyProperty<String?>, firebaseApp: App): Repository<PrivateViaLinkSpace> {
    return PrivateFirebaseRepository<PrivateViaLinkSpace,PrivateViaLinkSpaceJS>(userId, "privateViaLinkSpaceList", { it.toNormal() }, firebaseApp)
}

fun Repository<PrivateViaLinkSpace>.withNewSpaceIfNeeded(access: Access, spaceName: String): Access {
    return if (access.protectionLevel != ProtectionLevel.PRIVATE_VIA_LINK || access.privateViaLinkSpaceId != null) {
        access
    } else {
        access.copy(privateViaLinkSpaceId = save(PrivateViaLinkSpace(spaceName)))
    }
}

fun Repository<PrivateViaLinkSpace>.addIfMissing(privateViaLinkSpaceId: ID<PrivateViaLinkSpace>) {
    val privateViaLinkSpaceIds = list().map { it.id }
    if (!privateViaLinkSpaceIds.contains(privateViaLinkSpaceId)) {
        save(PrivateViaLinkSpace("Shared " + PlatformProvider.instance.now().toDisplayDateTimeString(), privateViaLinkSpaceId))
    }
}

class PrivateViaLinkSpaceModel(firebaseApp: App) {
    val privateViaLinkSpaceRepository = PrivateViaLinkSpaceRepository(Factory.userId, firebaseApp)
    val privateViaLinkSpaceIds: ReadOnlyProperty<List<ID<PrivateViaLinkSpace>>>
            = privateViaLinkSpaceRepository.listProperty().map { it.map { it.id }.filterNotNull() }

    fun addIfMissingAndExtractNewHash(hash: Array<String>): String {
        val privateViaLinkSpaceId = hash[1].toID<PrivateViaLinkSpace>()!!
        privateViaLinkSpaceRepository.addIfMissing(privateViaLinkSpaceId)
        return hash.drop(2).joinToString("/")
    }

    companion object {
        fun toUrl(privateViaLinkSpaceId: ID<PrivateViaLinkSpace>, hash: String): String {
            return "#privateViaLinkSpace/$privateViaLinkSpaceId/$hash"
        }
    }
}

fun BtsFormContext.accessControl(access: Property<Access>,
                                 selectableAccessList: ReadOnlyProperty<List<Access>>,
                                 spaceName: ReadOnlyProperty<String>,
                                 destinationHash: ReadOnlyProperty<String>): HTMLDivElement {
    return btsFormItemSimple(state = Property(State.Default), label = "Access Level") {
        val singleSelectInput = singleSelectInput(access, selectableAccessList) {
            textContent = it.protectionLevel.label
        }
        selectableAccessList.onNext { singleSelectInput.disabled = it.size <= 1 }
        val copied = access.mapAsDefault { false }
        btsButton(size = ButtonSize.Default, look = ButtonLook.Info,
                onclick = { it.stopImmediatePropagation(); populateSpaceIdAndCopyLinkToClipboard(access, spaceName, destinationHash.get()); copied.set(true) }) {
            access.onNext { visible = it.protectionLevel == ProtectionLevel.PRIVATE_VIA_LINK }
            appendText("Copy Link")
        }
        nbsp()
        span { copied.onNext { textContent = if (it) "Copied." else "" } }
    }
}

fun populateSpaceIdAndCopyLinkToClipboard(accessProperty: Property<Access>, spaceName: ReadOnlyProperty<String>, destinationHash: String) {
    var access = accessProperty.get()
    if (access.protectionLevel == ProtectionLevel.PRIVATE_VIA_LINK) {
        access = Factory.privateViaLinkSpaceRepository.withNewSpaceIfNeeded(access, spaceName.get())
        accessProperty.set(access)
        val currentLocationHash = window.location.hash
        copyTextToClipboard(window.location.href.replace(currentLocationHash, PrivateViaLinkSpaceModel.toUrl(access.privateViaLinkSpaceId!!, destinationHash)))
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
