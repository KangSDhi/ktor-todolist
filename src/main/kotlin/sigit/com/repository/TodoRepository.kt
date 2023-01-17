package sigit.com.repository

import sigit.com.entities.ToDo

interface TodoRepository {

    fun getAllToDos(): List<ToDo>

    fun getToDo(id: Int): ToDo?
}