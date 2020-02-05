package com.example.airapi.DAO

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.airapi.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class Repository private constructor(private val dao: ModelDao){

    companion object {
        private var instance: Repository?= null

        fun getInstance(dao: ModelDao): Repository {
            if (instance == null)
                instance = Repository(dao)
            return instance as Repository
        }
    }

//    fun addUser(user: User) {
//        dao.addUser(user)
//    }


    fun addUser(user: User) {
        runBlocking { this.launch(Dispatchers.IO) {
            dao.addUser(user)
        } }
    }

}