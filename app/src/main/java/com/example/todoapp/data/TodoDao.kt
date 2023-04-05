package com.example.todoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(todo: Todo)

    @Query("SELECT * from todos ORDER BY id ASC")
    fun getTodos(): Flow<List<Todo>>

    @Query("SELECT * from todos WHERE id = :id")
    fun getTodo(id: String): Flow<Todo>

    @Delete
    suspend fun remove(todo: Todo)

    @Query("DELETE FROM todos WHERE id = :id")
    suspend fun remove(id: String)
}