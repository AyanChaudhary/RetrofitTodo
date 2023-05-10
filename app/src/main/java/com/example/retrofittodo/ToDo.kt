package com.example.retrofittodo

data class ToDo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)