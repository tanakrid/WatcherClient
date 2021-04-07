package com.example.watcherclient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.User
import kotlinx.coroutines.launch

class ShowProfileFragmentViewModel: ViewModel() {
    val myProfile: MutableLiveData<User> = MutableLiveData()
    val myHostRoomsID: MutableLiveData<List<String>> = MutableLiveData()
    val myMemberRoomsID: MutableLiveData<List<String>> = MutableLiveData()

    fun showUser(user_id: String){
        viewModelScope.launch{
            myProfile.value = ApiClients.userAPIClient.showProfile(user_id)
        }
    }

    fun getNumHostedRoom(user_id: String){
        viewModelScope.launch {
            val user = ApiClients.userAPIClient.showProfile(user_id)
            val hostsID: ArrayList<String> = ArrayList<String>()
            val membersID: ArrayList<String> = ArrayList<String>()
            for(room_id in user.rooms) {
                val room = ApiClients.roomAPIClient.show(user_id, room_id)
                if (user_id == room.host_id) {
                    hostsID.add(room_id)
                } else {
                    membersID.add(room_id)
                }
            }
            myHostRoomsID.value = hostsID
            myMemberRoomsID.value = membersID
        }
    }


}