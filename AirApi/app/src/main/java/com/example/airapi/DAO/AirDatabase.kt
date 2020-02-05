package com.example.airapi.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.airapi.models.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AirDatabase : RoomDatabase() {
    abstract fun dao(): ModelDao

    companion object {
        @Volatile
        private var INSTANCE: AirDatabase?= null

        fun getInstance(context: Context): AirDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AirDatabase::class.java,
                        "air_database"
                    )

                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}



//class Database {
//
//    var dao = Dao()
//        private set
//
//    companion object {
//        private var instance: Database? = null
//        fun getInstance(): Database {
//            if (instance == null)
//                instance = Database()
//            return instance as Database
//        }
//    }
//}