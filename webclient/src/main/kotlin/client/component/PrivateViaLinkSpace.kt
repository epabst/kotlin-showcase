package client.component

import client.ext.firebase.PrivateFirebaseRepository
import client.util.IDJS
import client.util.toNormal
import common.util.ID
import common.util.PrivateViaLinkSpace
import common.util.Repository
import firebase.app.App
import net.yested.core.properties.ReadOnlyProperty

/**
 * An entity that tracks private links spaces that the current user has access to.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 4/19/18
 * Time: 9:57 PM
 */
external interface PrivateViaLinkSpaceJS {
    val id: IDJS?
}

fun PrivateViaLinkSpaceJS.toNormal(): PrivateViaLinkSpace = PrivateViaLinkSpace(id?.toNormal())

fun PrivateViaLinkSpaceRepository(userId: ReadOnlyProperty<String?>, firebaseApp: App)
        = PrivateFirebaseRepository<PrivateViaLinkSpace,PrivateViaLinkSpaceJS>(userId, "privateViaLinkSpaceList", { it.toNormal() }, firebaseApp)

fun Repository<PrivateViaLinkSpace>.addIfMissing(privateViaLinkSpaceId: ID<PrivateViaLinkSpace>) {
    val privateViaLinkSpaceIds = list().map { it.id }
    if (!privateViaLinkSpaceIds.contains(privateViaLinkSpaceId)) {
        save(PrivateViaLinkSpace(privateViaLinkSpaceId))
    }
}
