package com.example.watcherclient.model

data class DevicesInRoom (
    val room_name: String,
    val room_id: String,
    val devices: List<Device>
)