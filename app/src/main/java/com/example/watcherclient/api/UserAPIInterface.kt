package com.example.watcherclient.api

import com.example.watcherclient.model.Person
import com.example.watcherclient.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPIInterface {

    @FormUrlEncoded
    @POST("/api/users/create/")
    suspend fun createUser(@Field("user_name") user_name: String
                           , @Field("email") email: String
                           , @Field("pass") pass: String): String

    @FormUrlEncoded
    @POST("/api/users/show/")
    suspend fun show(@Field("user_id") user_id: String): Person

    @FormUrlEncoded
    @POST("/api/users/profile/")
    suspend fun showProfile(@Field("user_id") user_id: String): User

    @FormUrlEncoded
    @POST("/api/login/")
    suspend fun login(@Field("username") username: String
                      , @Field("password") password: String): String

    @POST("/api/logout/")
    suspend fun logout()

}