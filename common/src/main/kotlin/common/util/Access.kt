package common.util

/**
 * A level of protection for some data.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 3/19/18
 * Time: 6:47 AM
 */
enum class ProtectionLevel(val label: String) {
    /** Global to all users. */
    GLOBAL("Global: available to all users"),

    /** Limited to those who are granted access. */
    PROTECTED("Protected: available to users who are granted access"),

    /** Private to the user.  It is visible an all of their devices. */
    PRIVATE("Private: only available to me, but on any device"),

    /** Only stored on a particular device.  It is not stored in the cloud at all. */
    DEVICE("Device: kept only on this device")
}

data class Access(val protection: String, val accessSpaceId: ID<AccessSpace>? = null) : Comparable<Access> {
    constructor(protectionLevel: ProtectionLevel, accessSpaceId: ID<AccessSpace>? = null)
            : this(protectionLevel.name, accessSpaceId)

    val protectionLevel: ProtectionLevel get() = ProtectionLevel.valueOf(protection)

    override fun compareTo(other: Access): Int = protectionLevel.compareTo(other.protectionLevel)
}

data class AccessSpace(val name: String, val id: ID<AccessSpace>? = null) : WithID<AccessSpace> {
    override fun getID(): ID<AccessSpace>? = id

    override fun withID(id: ID<AccessSpace>): AccessSpace = copy(id = id)
}

interface ProtectedWithID<T : ProtectedWithID<T>> : WithID<T> {
    val protectedAccess: Access

    fun copy(newAccess: Access): T

    fun replacingAccess(oldAccess: Access?, newAccess: Access): T {
        return if (oldAccess == null || protectedAccess == oldAccess) {
            copy(newAccess)
        } else {
            @Suppress("UNCHECKED_CAST")
            this as T
        }
    }
}

interface ProtectedChildWithID<T: ProtectedChildWithID<T,P>,P: WithID<P>> : ChildWithID<T,P>, ProtectedWithID<T>
