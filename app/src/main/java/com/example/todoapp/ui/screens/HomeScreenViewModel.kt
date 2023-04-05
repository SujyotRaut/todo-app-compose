package com.example.todoapp.ui.screens

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodosRepository
import com.example.todoapp.data.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val todosRepository: TodosRepository): ViewModel() {
    private var _uiState by mutableStateOf<HomeScreenUiState>(HomeScreenUiState.Loading)
    val uiState get() = _uiState

    private var _todoInput by mutableStateOf("")
    val todoInput get() = _todoInput

    init {
        getTodos()
    }

    private fun getTodos() {
        _uiState = HomeScreenUiState.Loading
        viewModelScope.launch {
            todosRepository.getTodos().catch {
                _uiState = HomeScreenUiState.Error
            }.collect { todos ->
                _uiState = HomeScreenUiState.Success(todos)
            }
        }
    }

    fun onChangeTodoInput(value: String) {
        _todoInput = value
    }

    fun addTodo() {
        if(_todoInput.isNotEmpty()) {
            viewModelScope.launch {
                val todo = Todo(title = _todoInput.replace("\n", ""))
                todosRepository.addTodo(todo)
                _todoInput = ""
            }
        }
    }

    fun removeTodo(todo: Todo) {
        removeTodo(todo.id)
    }

    private fun removeTodo(id: String) {
        viewModelScope.launch {
            todosRepository.removeTodo(id)
        }
    }
}

sealed interface HomeScreenUiState {
    object Loading: HomeScreenUiState
    object Error: HomeScreenUiState
    data class Success(val todos: List<Todo>): HomeScreenUiState
}