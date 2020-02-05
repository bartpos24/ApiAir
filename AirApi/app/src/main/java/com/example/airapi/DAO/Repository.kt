package com.example.airapi.DAO

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.airapi.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class Repository(application: Application){

//    companion object {
//        private var instance: Repository?= null
//
//        fun getInstance(dao: ModelDao): Repository {
//            if (instance == null)
//                instance = Repository(dao)
//            return instance as Repository
//        }
//    }

    private val dao: ModelDao
    private val allUsers: LiveData<List<User>>


    init {
        val database = AirDatabase.getInstance(application.applicationContext)
        dao = database.dao()
        allUsers = dao.getUsers()
    }


    fun addUser(user: User) {
        runBlocking { this.launch(Dispatchers.IO) {
            dao.addUser(user)
        } }
    }

}