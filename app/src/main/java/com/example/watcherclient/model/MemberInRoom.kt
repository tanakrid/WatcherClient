package com.example.watcherclient.model

data class MemberInRoom (
    val room_name: String,
    val room_id: String,
    val members: List<Person>
)