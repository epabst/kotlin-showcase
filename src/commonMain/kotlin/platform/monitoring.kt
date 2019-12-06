package platform

var currentContext: String = "start-up"

fun <T> inContext(contextName: String, f: ()->T): T {
    val contextBefore = "before $contextName"
    currentContext = contextBefore
    val result: T
    try {
        result = f()
    } finally {
        val contextDuring = "during $contextName"
        currentContext = if (currentContext != contextBefore) "$contextDuring, $currentContext" else contextDuring
    }
    currentContext = "after $contextName"
    return result
}
