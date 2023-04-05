package com.example.todoapp.network

import com.example.todoapp.data.Todo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

const val API_BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(GsonConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(API_BASE_URL)
    .build()

interface TodosApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: String): Todo

    @Headers("Content-type: application/json; charset=UTF-8")
    @POST("todos")
    suspend fun addTodo(@Body todo: Todo)

    @PUT("todos/{id}")
    suspend fun editTodo(@Path("id") id: String, @Body todo: Todo)

    @DELETE("todos/{id}")
    suspend fun removeTodo(@Path("id") id: String)
}

object TodosApi {
    val retrofitService : TodosApiService by lazy {
        retrofit.create(TodosApiService::class.java)
    }
}