package sigit.com.repository

import sigit.com.entities.ToDo

class InMemoryToDoRepository: TodoRepository {

    private val todos = listOf<ToDo>(
        ToDo(1, "Place content for video #2", true),
        ToDo(2, "Record video #2", false),
        ToDo(3, "Upload video #2", false)
    )
    override fun getAllToDos(): List<ToDo> {
        return todos
    }

    override fun getToDo(id: Int): ToDo? {
        return todos.firstOrNull{ it.id == id }
    }
}