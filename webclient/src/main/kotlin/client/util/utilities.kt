/**
 * Utilities.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 6/3/17
 * Time: 9:47 AM
 */
package client.util

import net.yested.core.properties.Property
import net.yested.core.properties.ReadOnlyProperty
import net.yested.core.properties.collectAsDefault
import net.yested.core.properties.mapAsDefault

/**
 * Map a List to another list, reusing the items from the time before whenever equal to the function result.
 * The function is still run each time, but the result is quickly discarded if it has a match in the prior result list.
 * @return a mutable Property that defaults to a List having the results of the transform.
 * @see mapAsDefault for more information about the return type
 */
fun <T,OUT> ReadOnlyProperty<List<T>?>.mapEachReusing(transform: (T)->OUT): Property<List<OUT>?> {
    return collectAsDefault { oldResult, list ->
        val oldResultMap = oldResult?.groupBy { it }
        list?.map(transform)?.map { oldResultMap?.get(it)?.first() ?: it }
    }
}
