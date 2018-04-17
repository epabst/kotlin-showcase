package common.util

/**
 * A level of protection for some data.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 3/19/18
 * Time: 6:47 AM
 */
enum class ProtectionLevel(val label: String) {
    /** Owned by all users. */
    PUBLIC("Public: modifiable by all users"),

    /** Private to those who have the link.  It is visible an all of their devices. */
    PRIVATE_VIA_LINK("Private shared via link: modifiable by users who have the private link"),

    /** Private to the owning user.  It is visible an all of their devices. */
    PRIVATE("Private: only visible to me, but on any device"),

    /**
     * Only stored on the particular device.
     * It is not stored in a cloud and is not accessible by anyone unless they have access to the device.
     */
    DEVICE("Device: Kept only on this device")
}

data class PrivateViaLinkSpace(val id: ID<PrivateViaLinkSpace>? = null) : WithID<PrivateViaLinkSpace> {
    override fun getID(): ID<PrivateViaLinkSpace>? = id

    override fun withID(id: ID<PrivateViaLinkSpace>): PrivateViaLinkSpace = copy(id = id)
}

interface ProtectedWithID<T : ProtectedWithID<T>> : WithID<T> {
    val protectionLevel: ProtectionLevel

    val privateViaLinkSpaceId: ID<PrivateViaLinkSpace>

    fun copy(protectionLevel: ProtectionLevel): T

    fun replacingProtectionLevel(oldProtectionLevel: ProtectionLevel?, newProtectionLevel: ProtectionLevel): T {
        return if (oldProtectionLevel == null || protectionLevel == oldProtectionLevel) {
            copy(newProtectionLevel)
        } else {
            @Suppress("UNCHECKED_CAST")
            this as T
        }
    }
}
