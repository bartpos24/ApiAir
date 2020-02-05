package com.example.airapi.ui.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airapi.DAO.Repository

import com.example.airapi.models.User

class AddUserViewModel(val database: Repository, application: Application) : AndroidViewModel(application){

    private val _text = MutableLiveData<String>().apply {
        value = "This is add user Fragment"
    }
    val text: LiveData<String> = _text


    fun insertUser(user: User) {
        database.addUser(user)
    }
}