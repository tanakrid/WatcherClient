package com.example.watcherclient.model

data class Device(
        var device_id: String,
        val host_room_id: String,
        val is_active: Boolean,
        val registered: Boolean
)