package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow

interface TodosRepository {
    fun getTodos(): Flow<List<Todo>>
    fun getTodo(id: String): Flow<Todo>
    suspend fun addTodo(todo: Todo)
    suspend fun editTodo(todo: Todo)
    suspend fun removeTodo(id: String)
}