package com.example.watcherclient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcherclient.api.ApiClients
import com.example.watcherclient.model.Room
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    val myResponseList: MutableLiveData<Map<String, Room>> = MutableLiveData()


    fun signup(username: String, email: String, password: String): String {
        var response: String = ""
        viewModelScope.launch {
            response = ApiClients.userAPIClient.createUser(username, email, password)
        }
        return response
    }

    fun login(username: String, password: String){
        viewModelScope.launch{
            ApiClients.userAPIClient.login(username, password)
        }
    }
}