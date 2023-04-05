package com.example.todoapp.di

import android.content.Context
import com.example.todoapp.data.AppDatabase
import com.example.todoapp.data.LocalTodosRepository
import com.example.todoapp.data.TodoDao
import com.example.todoapp.data.TodosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesTodoDao(@ApplicationContext context: Context): TodoDao = AppDatabase.getDatabase(context).todoDao()

    @Provides
    @Singleton
    fun providesTodosRepository(todoDao: TodoDao): TodosRepository = LocalTodosRepository(todoDao)
}