package com.example.todoapp.models

import java.util.UUID

data class Todo(
    val id: String = UUID.randomUUID().toString(),
    val content: String
)
