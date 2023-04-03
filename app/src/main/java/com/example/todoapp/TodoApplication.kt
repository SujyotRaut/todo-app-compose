package com.example.todoapp

import android.app.Application
import android.util.Log

class TodoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Application Started")
    }
}