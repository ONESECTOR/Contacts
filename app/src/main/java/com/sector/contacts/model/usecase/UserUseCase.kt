package com.sector.contacts.model.usecase

import com.sector.contacts.entity.User
import com.sector.contacts.model.database.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private var userRepository: UserRepository
) {
    fun getUsers() = userRepository.getUsers()

    suspend fun addUser(user: User) {
        userRepository.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userRepository.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userRepository.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userRepository.deleteAllUsers()
    }
}