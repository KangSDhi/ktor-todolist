package sigit.com.repository

import sigit.com.entities.ToDo
import sigit.com.entities.TodoDraft

class InMemoryToDoRepository: TodoRepository {

    private val todos = mutableListOf<ToDo>()
    override fun getAllToDos(): List<ToDo> {
        return todos
    }

    override fun getToDo(id: Int): ToDo? {
        return todos.firstOrNull{ it.id == id }
    }

    override fun addTodo(draft: TodoDraft): ToDo {
        val todo = ToDo(
            id = todos.size + 1,
            title = draft.title,
            done = draft.done
        )
        todos.add(todo)
        return todo
    }

    override fun removeTodo(id: Int): Boolean {
        return todos.removeIf{ it.id == id }
    }

    override fun updateTodo(id: Int, draft: TodoDraft): Boolean {
        val todo = todos.firstOrNull{ it.id == id } ?: return false

        todo.title = draft.title
        todo.done = draft.done

        return true
    }
}