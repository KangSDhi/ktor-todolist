package sigit.com.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import sigit.com.entities.ToDo
import sigit.com.repository.InMemoryToDoRepository
import sigit.com.repository.TodoRepository

fun Application.configureRouting() {
    routing {

        val repository: TodoRepository = InMemoryToDoRepository()

        get("/") {
            call.respondText("Hello Todolist!")
        }

        get("/todos"){
            call.respond(repository.getAllToDos())
        }

        get("/todos/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null){
                call.respond(
                    HttpStatusCode.BadRequest,
                    "id parameter has to be a number"
                )
                return@get
            }

            val todo = repository.getToDo(id)

             if (todo == null){
                call.respond(
                    HttpStatusCode.NotFound,
                    "found no todo for the provided id $id"
                )
            } else {
                call.respond(todo)
            }
        }

        post("/todos"){

        }

        put("/todos/{id}"){

        }

        delete("/todos/{id}"){

        }
    }
}
