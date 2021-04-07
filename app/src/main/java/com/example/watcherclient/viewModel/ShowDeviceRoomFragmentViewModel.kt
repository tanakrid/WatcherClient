package com.example.watcherclient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.DevicesInRoom
import kotlinx.coroutines.launch

class ShowDeviceRoomFragmentViewModel:  ViewModel() {

    val myResponseList: MutableLiveData<Map<String, DevicesInRoom>> = MutableLiveData()

    fun showDevice(user_id : String) {
        viewModelScope.launch {
            myResponseList.value = ApiClients.roomAPIClient.showDevice(user_id)
        }
    }
}