package com.example.watcherclient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.Device
import com.example.watcherclient.model.MemberInRoom
import com.example.watcherclient.model.Person
import kotlinx.coroutines.launch

class ShowMemberRoomFragmentViewModel:  ViewModel(){

    val myResponseList: MutableLiveData<Map<String, MemberInRoom>> = MutableLiveData()

    fun showMember(user_id : String) {
        viewModelScope.launch {
            myResponseList.value = ApiClients.roomAPIClient.showMember(user_id)
        }
    }
}