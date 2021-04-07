package com.example.watcherclient.model

data class User(
    override val user_id: String,
    override val user_name: String,
    val pass: String,
    val rooms: List<String>
): Person(user_id, user_name)
