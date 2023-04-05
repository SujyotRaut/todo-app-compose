package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalTodosRepository @Inject constructor(private val todoDao: TodoDao): TodosRepository {
    override fun getTodos(): Flow<List<Todo>> = todoDao.getTodos()

    override  fun getTodo(id: String): Flow<Todo> = todoDao.getTodo(id)

    override suspend fun addTodo(todo: Todo) = todoDao.add(todo)

    override suspend fun editTodo(todo: Todo) {
        TODO("Not yet implemented")
    }

    override suspend fun removeTodo(id: String) = todoDao.remove(id)
}