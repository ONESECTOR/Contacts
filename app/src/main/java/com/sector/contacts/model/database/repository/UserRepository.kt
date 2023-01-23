package com.sector.contacts.model.database.repository

import com.sector.contacts.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun deleteAllUsers()
}