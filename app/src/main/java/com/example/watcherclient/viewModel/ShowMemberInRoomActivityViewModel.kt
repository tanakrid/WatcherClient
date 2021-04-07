package com.example.watcherclient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.Person
import com.example.watcherclient.model.Room
import kotlinx.coroutines.launch

class ShowMemberInRoomActivityViewModel:  ViewModel(){

    val myResponseList: MutableLiveData<List<Person>> = MutableLiveData()

    fun showMember(user_id : String, room_id: String) {
        viewModelScope.launch {
            val room: Room = ApiClients.roomAPIClient.show(user_id, room_id)
            val members: ArrayList<Person> = ArrayList()
            for (member_id in room.members_id){
                val member: Person = ApiClients.userAPIClient.show(member_id)
                members.add(member)
            }
            myResponseList.value = members
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
}