package sample

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title("Hello from Ktor!")
                    }
                    body {
                        +"Hello from Ktor"
                        div {
                            id = "js-response"
                            +"Loading..."
                        }
                        script(src = "/static/mpp.js") {}
                    }
                }
            }
            static("/static") {
                resource("mpp.js")
            }
        }
    }.start(wait = true)
}