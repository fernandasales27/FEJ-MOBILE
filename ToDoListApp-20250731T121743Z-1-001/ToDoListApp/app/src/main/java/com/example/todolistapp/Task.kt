package com.example.todolistapp

import retrofit2.http.Url


data class Task(

    val id: Int? = null, // ID pode ser nulo ao criar, mas precisa existir ao apagar
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val imageUrl: String
)