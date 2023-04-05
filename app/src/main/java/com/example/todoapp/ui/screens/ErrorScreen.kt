package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.R

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, onRetry: () -> Unit = {}) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Icon(painterResource(R.drawable.ic_error), contentDescription = "Loading")
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    TodoAppTheme {
        ErrorScreen()
    }
}