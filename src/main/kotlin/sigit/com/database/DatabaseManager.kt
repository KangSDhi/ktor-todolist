package sigit.com.database

import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import sigit.com.entities.ToDo
import sigit.com.entities.TodoDraft

class DatabaseManager {

    private val hostname = "localhost"
    private val databaseName = "ktor_todo"
    private val username = "root"
    private val password = "toor"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllTodos(): List<DBTodoEntity> {
        return ktormDatabase.sequenceOf(DBTodoTable).toList()
    }

    fun getTodo(id: Int): DBTodoEntity? {
        return ktormDatabase.sequenceOf(DBTodoTable).firstOrNull { it.id eq id }
    }

    fun addTodo(draft: TodoDraft): ToDo {
        val insertedId = ktormDatabase.insertAndGenerateKey(DBTodoTable){
            set(DBTodoTable.title, draft.title)
            set(DBTodoTable.done, draft.done)
        } as Int

        return ToDo(insertedId, draft.title, draft.done)
    }

    fun updateTodo(id: Int, draft: TodoDraft): Boolean {
        val updatedRows = ktormDatabase.update(DBTodoTable) {
            set(DBTodoTable.title, draft.title)
            set(DBTodoTable.done, draft.done)
            where {
                it.id eq id
            }
        }

        return updatedRows > 0
    }

    fun removeTodo(id: Int): Boolean {
        val deleteRows = ktormDatabase.delete(DBTodoTable) {
            it.id eq id
        }
        return deleteRows > 0
    }
}