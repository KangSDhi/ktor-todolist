package sigit.com.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import sigit.com.entities.ToDo

fun Application.configureRouting() {
    routing {

        val todos = listOf<ToDo>(
            ToDo(1, "Place content for video #2", true),
            ToDo(2, "Record video #2", false),
            ToDo(3, "Upload video #2", false)
        )

        get("/") {
            call.respondText("Hello Todolist!")
        }

        get("/todos"){
            call.respond(todos)
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
