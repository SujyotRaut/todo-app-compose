package com.example.todoapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.models.Todo

class HomeViewModel: ViewModel() {
    private val _todos = mutableStateListOf<Todo>()
    val todos = _todos

    fun addTodo(todo: Todo) = _todos.add(todo)
    fun removeTodo(todo: Todo) = _todos.remove(todo)
    fun removeTodo(id: String) = _todos.removeIf { it.id.contentEquals(id) }
}