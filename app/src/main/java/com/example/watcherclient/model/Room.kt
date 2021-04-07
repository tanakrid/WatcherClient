package com.example.watcherclient.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Room (

    @SerializedName("room_id")
    @Expose
    val room_id: String,
    @SerializedName("host_id")
    @Expose
    val host_id: String,
    @SerializedName("curr_num")
    @Expose
    val curr_num: Int,
    @SerializedName("limit_num")
    @Expose
    val limit_num: Int,
    @SerializedName("warn_num")
    @Expose
    val warn_num: Int,
    @SerializedName("room_name")
    @Expose
    val room_name: String,
    @SerializedName("desc")
    @Expose
    val desc: String,
    @SerializedName("devices_id")
    @Expose
    val devices_id: List<String>,
    @SerializedName("members_id")
    @Expose
    val members_id: List<String>
)
