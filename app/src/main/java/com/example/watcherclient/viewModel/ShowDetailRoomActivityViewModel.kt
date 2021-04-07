package com.example.watcherclient.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.Device
import com.example.watcherclient.model.Person
import com.example.watcherclient.model.Room
import kotlinx.coroutines.launch

class ShowDetailRoomActivityViewModel:  ViewModel()  {
    val myResponseMemberList: MutableLiveData<List<Person>> = MutableLiveData()
    val myResponseDeviceList: MutableLiveData<List<Device>> = MutableLiveData()
    val myResponseRoom: MutableLiveData<Room> = MutableLiveData()

    fun show(user_id : String, room_id: String) {
        viewModelScope.launch {
            val room: Room = ApiClients.roomAPIClient.show(user_id, room_id)
            val members = ApiClients.roomAPIClient.showMember(user_id)[room_id]?.members
            val devices= ApiClients.roomAPIClient.showDevice(user_id)[room_id]?.devices

            myResponseRoom.value = room
            myResponseMemberList.value = members
            myResponseDeviceList.value = devices
        }
    }
}