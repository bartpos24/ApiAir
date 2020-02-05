package com.example.airapi.ui.station

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airapi.DAO.Repository
import com.example.airapi.models.User

class StationViewModel(private val repository: Repository) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is station fragment"
    }
    val text: LiveData<String> = _text
    val users = MutableLiveData<List<User>>()

//    fun getUsers(): LiveData<List<User>> {
//        users.value = repository.getUsers()
//        return users
//    }
}