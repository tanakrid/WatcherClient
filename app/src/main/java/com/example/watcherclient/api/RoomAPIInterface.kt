package com.example.watcherclient.api

import com.example.watcherclient.model.Device
import com.example.watcherclient.model.DevicesInRoom
import com.example.watcherclient.model.MemberInRoom
import com.example.watcherclient.model.Room
import retrofit2.http.*

interface RoomAPIInterface {

    @FormUrlEncoded
    @POST("/api/rooms/show/")
    suspend fun show(@Field("user_id") user_id: String, @Field("room_id") room_id: String): Room

    @FormUrlEncoded
    @POST("/api/rooms/showAll/")
    suspend fun showAll(@Field("user_id") user_id: String): Map<String, Room>

    @FormUrlEncoded
    @POST("/api/rooms/editRoomName/")
    suspend fun editRoomName(@Field("user_id") user_id: String
                             , @Field("room_id") room_id: String
                             , @Field("room_name") room_name: String): String

    @FormUrlEncoded
    @POST("/api/rooms/editDesc/")
    suspend fun editDesc(@Field("user_id") user_id: String
                             , @Field("room_id") room_id: String
                             , @Field("desc") desc: String): String

    @FormUrlEncoded
    @POST("/api/rooms/editLimitNumber/")
    suspend fun editLimitNumber(@Field("user_id") user_id: String
                         , @Field("room_id") room_id: String
                         , @Field("limit_num") limit_num: Int): String

    @FormUrlEncoded
    @POST("/api/rooms/showMember/")
    suspend fun showMember(@Field("user_id") user_id: String): Map<String, MemberInRoom>

    @FormUrlEncoded
    @POST("/api/rooms/addMember/")
    suspend fun addMember(@Field("user_id") user_id: String
                                , @Field("room_id") room_id: String
                                , @Field("member_id") member_id: String): String

    @FormUrlEncoded
    @POST("/api/rooms/removeMember/")
    suspend fun removeMember(@Field("user_id") user_id: String
                          , @Field("room_id") room_id: String
                          , @Field("member_id") member_id: String): String
    @FormUrlEncoded
    @POST("/api/rooms/showDevice/")
    suspend fun showDevice(@Field("user_id") user_id: String): Map<String, DevicesInRoom>

    @FormUrlEncoded
    @POST("/api/rooms/showSingleDevice/")
    suspend fun showSingleDevice(@Field("device_id") device_id: String): Device

    @FormUrlEncoded
    @POST("/api/rooms/addDevice/")
    suspend fun addDevice(@Field("user_id") user_id: String
                             , @Field("room_id") room_id: String
                             , @Field("device_id") device_id: String): String

    @FormUrlEncoded
    @POST("/api/rooms/removeDevice/")
    suspend fun removeDevice(@Field("user_id") user_id: String
                          , @Field("room_id") room_id: String
                          , @Field("device_id") device_id: String): String

    @FormUrlEncoded
    @POST("/api/rooms/create/")
    suspend fun createRoom(@Field("room_name") room_name: String
                           , @Field("desc") desc: String
                           , @Field("limit_num") limit_num: Int
                           , @Field("warn_num") warn_num: Int
                           , @Field("user_id") user_id: String): String

    @FormUrlEncoded
    @POST("/api/rooms/removeRoom/")
    suspend fun removeRoom(@Field("user_id") user_id: String, @Field("room_id") room_id: String): String


}