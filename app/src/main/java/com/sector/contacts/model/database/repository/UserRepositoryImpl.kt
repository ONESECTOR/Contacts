package com.sector.contacts.model.database.repository

import com.sector.contacts.di.provider.DatabaseProvider
import com.sector.contacts.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var databaseProvider: DatabaseProvider
): UserRepository {

    override fun getUsers(): Flow<List<User>> =
        databaseProvider.userDao().getUsers()

    override suspend fun addUser(user: User) {
        databaseProvider.userDao().addUser(user)
    }

    override suspend fun updateUser(user: User) {
        databaseProvider.userDao().updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        databaseProvider.userDao().deleteUser(user)
    }

    override suspend fun deleteAllUsers() {
        databaseProvider.userDao().deleteAllUsers()
    }
}