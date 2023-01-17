package sigit.com.repository

import sigit.com.database.DatabaseManager
import sigit.com.entities.ToDo
import sigit.com.entities.TodoDraft

class MySQLTodoRepository : TodoRepository {

    private val database = DatabaseManager()
    override fun getAllToDos(): List<ToDo> {
        return database.getAllTodos().map { ToDo(it.id, it.title, it.done) }
    }

    override fun getToDo(id: Int): ToDo? {
        return database.getTodo(id)?.let { ToDo(it.id, it.title, it.done) }
    }

    override fun addTodo(draft: TodoDraft): ToDo {
        return database.addTodo(draft)
    }

    override fun removeTodo(id: Int): Boolean {
        return database.removeTodo(id)
    }

    override fun updateTodo(id: Int, draft: TodoDraft): Boolean {
        return database.updateTodo(id, draft)
    }
}