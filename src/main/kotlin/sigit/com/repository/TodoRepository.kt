package sigit.com.repository

import sigit.com.entities.ToDo
import sigit.com.entities.TodoDraft

interface TodoRepository {

    fun getAllToDos(): List<ToDo>

    fun getToDo(id: Int): ToDo?

    fun addTodo(draft: TodoDraft): ToDo

    fun removeTodo(id: Int): Boolean

    fun updateTodo(id: Int, draft: TodoDraft): Boolean
}