package com.example.airapi.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.airapi.models.User

@Dao
interface ModelDao {
    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT SUM(id) FROM user_table")
    fun getUsersCount(): LiveData<Int>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: Int): User
}