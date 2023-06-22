package com.aashushaikh.contactmanagerapp.room

import androidx.lifecycle.LiveData

class UserRepository(private val dao: UserDAO) {
    private val users = dao.getAllUsersInDB()
    suspend fun insert(user: User): Long{
        return dao.insertUser(user)
    }
    suspend fun delete(user: User){
        dao.deleteUser(user)
    }
    suspend fun update(user: User){
        dao.updateUser(user)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
    fun getAllUsers(): LiveData<List<User>> {
        return users
    }
}