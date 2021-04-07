package com.example.watcherclient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.Room
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import org.json.JSONObject

class ShowRoomsActivityViewModel : ViewModel() {

    val myResponseList: MutableLiveData<Map<String, Room>> = MutableLiveData()

    fun showAll(user_id : String) {
        viewModelScope.launch {
            myResponseList.value = ApiClients.roomAPIClient.showAll(user_id)
        }
    }

    fun createRoom(room : Room): String{
        var response: String = ""
        viewModelScope.launch {
            response = ApiClients.roomAPIClient.createRoom(room.room_name, room.desc
                , room.limit_num, room.warn_num, room.host_id)
        }
        return response
    }

    fun removeRoom(user_id: String, room_id: String): String{
        var response: String = ""
        viewModelScope.launch {
            response = ApiClients.roomAPIClient.removeRoom(user_id, room_id)
        }
        return response
    }
}