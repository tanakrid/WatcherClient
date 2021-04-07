package com.example.watcherclient.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClients {
    val userAPIClient by lazy {
        Retrofit.Builder()
            .baseUrl("http://18.139.235.66/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPIInterface::class.java)
    }

    val roomAPIClient by lazy {
        Retrofit.Builder()
            .baseUrl("http://18.139.235.66/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RoomAPIInterface::class.java)
    }
}