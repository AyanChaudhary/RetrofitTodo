package com.example.retrofittodo.api

import com.example.retrofittodo.ToDo
import retrofit2.Response
import retrofit2.http.GET

interface TodoApiInterface {

    @GET("/todos")
    suspend fun getTodos():Response<List<ToDo>>

}