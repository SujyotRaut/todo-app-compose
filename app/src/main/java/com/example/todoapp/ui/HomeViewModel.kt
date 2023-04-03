package com.example.todoapp.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.todoapp.models.Todo

class HomeViewModel: ViewModel() {
    private val _todos = mutableStateListOf<Todo>()
    val todos get() = _todos

    private var _todoInput = mutableStateOf("")
    val todoInput get() = _todoInput.value

    fun onChangeTodoInput(value: String) {
        _todoInput.value = value
    }

    fun addTodo() {
        if(_todoInput.value.isNotEmpty()) {
            todos.add(Todo(content = _todoInput.value))
            _todoInput.value = ""
        }
    }

    fun removeTodo(todo: Todo) {
        _todos.remove(todo)
    }
}