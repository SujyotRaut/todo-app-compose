package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.data.Todo
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(bottom = 0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {

                TextField(
                    value = viewModel.todoInput,
                    onValueChange = {
                        val lastChar = it[it.length - 1]
                        if(lastChar == '\n') viewModel.addTodo()
                        else viewModel.onChangeTodoInput(it)
                    },
                    placeholder =  { Text(text = "Todo") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { viewModel.addTodo() }),
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = { viewModel.addTodo() },
                    modifier = Modifier.fillMaxHeight(),
                    shape = RectangleShape,
                ) {
                    Text(text = "Save".uppercase())
                }
            }

            when (val uiState = viewModel.uiState) {
                is HomeScreenUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())

                is HomeScreenUiState.Success -> TodoList(
                    todos = uiState.todos,
                    onDeleteTodo = { viewModel.removeTodo(it) }
                )

                is HomeScreenUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TodoAppTheme {
        HomeScreen()
    }
}

@Composable
fun TodoList(todos: List<Todo>, onDeleteTodo: (todo: Todo) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(todos) {
            TodoListItem(todo = it, onDeleteTodo)
            Divider()
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, onDeleteTodo: (todo: Todo) -> Unit ) {
    Row (modifier = Modifier) {
        Text(
            text = todo.title,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
        )

        IconButton(onClick = { onDeleteTodo(todo) }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete todo")
        }
    }
}