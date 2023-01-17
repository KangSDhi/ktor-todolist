package sigit.com.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import sigit.com.entities.ToDo
import sigit.com.entities.TodoDraft
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
            val todoDraft = call.receive<TodoDraft>()
            val todo = repository.addTodo(todoDraft)
            call.respond(todo)
        }

        put("/todos/{id}"){
            val todoDraft = call.receive<TodoDraft>()
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null){
                call.respond(HttpStatusCode.BadRequest, "id parameter has to be a number!")
                return@put
            }

            val updated = repository.updateTodo(todoId, todoDraft)
            if (updated){
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "found no todo with the id $todoId")
            }
        }

        delete("/todos/{id}"){
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null){
                call.respond(HttpStatusCode.BadRequest, "id parameter has to be a number")
                return@delete
            }

            val removed = repository.removeTodo(todoId)
            if (removed){
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "found no todo with the id $todoId")
            }
        }
    }
}
