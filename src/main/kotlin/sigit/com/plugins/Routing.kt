package sigit.com.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Todolist!")
        }

        get("/todos"){

        }

        get("/todos/{id}"){
            val id = call.parameters["id"]
            call.respondText("Todolist Details for ToDo Item #$id")
        }

        post("/todos"){

        }

        put("/todos/{id}"){

        }

        delete("/todos/{id}"){

        }
    }
}
