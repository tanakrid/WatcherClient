package com.example.watcherclient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.Device
import com.example.watcherclient.model.Room
import kotlinx.coroutines.launch

class ShowDeviceInRoomActivityViewModel:  ViewModel(){

    val myResponseList: MutableLiveData<List<Device>> = MutableLiveData()

    fun showDevice(user_id : String, room_id: String) {
        viewModelScope.launch {
            val room: Room = ApiClients.roomAPIClient.show(user_id, room_id)
            val devices: ArrayList<Device> = ArrayList()
            for (device_id in room.devices_id){
                val device: Device = ApiClients.roomAPIClient.showSingleDevice(device_id)
                device.device_id = device_id
                devices.add(device)
            }
            myResponseList.value = devices
        }
    }

    fun addMember(user_id: String, room_id: String, member_id: String){
        viewModelScope.launch {
            ApiClients.roomAPIClient.addMember(user_id, room_id, member_id)
        }
    }

    fun removeMember(user_id: String, room_id: String, member_id: String){
        viewModelScope.launch {
            ApiClients.roomAPIClient.removeMember(user_id, room_id, member_id)
        }
    }

    fun addDevice(user_id: String, room_id: String, device_id: String){
        viewModelScope.launch {
            ApiClients.roomAPIClient.addDevice(user_id, room_id, device_id)
        }
    }

    fun removeDevice(user_id: String, room_id: String, device_id: String){
        viewModelScope.launch{
            ApiClients.roomAPIClient.removeDevice(user_id, room_id, device_id)
        }
    }

}