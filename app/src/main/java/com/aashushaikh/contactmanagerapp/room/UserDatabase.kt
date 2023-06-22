package com.aashushaikh.contactmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDAO: UserDAO

    //Singleton design pattern --> A pattern which is used mainly used in databases and connectivity which is used to create only one object at
    // runtime. this is used to reduce the memory leaks and save time
    //We create singleton as companion object
    companion object{
        @Volatile // makes this visible to all the threads
        private var INSTANCE: UserDatabase?= null
        fun getInstance(context: Context): UserDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "users_db")
                        .build()
                }
                return instance
            }
        }
    }
}