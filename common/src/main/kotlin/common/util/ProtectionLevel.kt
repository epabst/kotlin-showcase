package common.util

/**
 * A level of protection for some data.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 3/19/18
 * Time: 6:47 AM
 */
enum class ProtectionLevel {
    /**
     * Only stored on the particular device.
     * It is not stored in a cloud and is not accessible by anyone unless they have access to the device.
     */
    DEVICE,

    /** Private to the owning user.  It is visible an all of their devices. */
    PRIVATE,

    /**
     * Owned by all users.
     */
    PUBLIC
}

interface ProtectedWithID<T : ProtectedWithID<T>> : WithID<T> {
    val protectionLevel: ProtectionLevel

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
