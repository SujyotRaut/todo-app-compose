package com.example.todoapp.ui

import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.models.Todo
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val context = LocalContext.current
    var todoInput by remember { mutableStateOf("") }

    val addTodo = remember { {
        if(todoInput.isNotEmpty()) {
            viewModel.addTodo(Todo(content = todoInput))
            todoInput = ""
        } else Toast.makeText(context, "Please enter todo input", Toast.LENGTH_SHORT).show()
    } }

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
                    value = todoInput,
                    onValueChange = { todoInput = it },
                    placeholder =  { Text(text = "Todo") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { addTodo() }),
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = addTodo,
                    modifier = Modifier.fillMaxHeight(),
                    shape = RectangleShape,
                ) {
                    Text(text = "Save".uppercase())
                }
            }

            TodoList(todos = viewModel.todos, onDeleteTodo = { viewModel.removeTodo(it) })
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
            text = todo.content,
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