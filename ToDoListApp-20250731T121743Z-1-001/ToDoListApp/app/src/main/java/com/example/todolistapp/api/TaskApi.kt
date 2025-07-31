package com.example.todolistapp.api

import com.example.todolistapp.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApi {

    @GET("tasks")
    suspend fun getTasks(): List<Task>

    @POST("tasks")
    suspend fun addTask(@Body task: Task): Response<Void>

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Void>
}