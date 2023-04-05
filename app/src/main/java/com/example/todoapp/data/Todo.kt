package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("todos")
data class Todo (
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val completed: Boolean = false
)
