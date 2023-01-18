package com.sector.contacts.model.database.repository

import com.sector.contacts.entity.User

interface UserRepository {

    suspend fun getUsers(): MutableList<User>

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun deleteAllUsers()
}