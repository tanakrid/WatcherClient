package com.example.watcherclient.model

data class User(
    val user_id: String,
    val user_name: String,
    val email: String,
    val pass: String,
    val rooms: List<String>
)
