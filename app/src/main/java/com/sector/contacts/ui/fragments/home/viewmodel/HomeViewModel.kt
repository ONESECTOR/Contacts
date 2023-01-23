package com.sector.contacts.ui.fragments.home.viewmodel

import androidx.lifecycle.ViewModel
import com.sector.contacts.entity.User
import com.sector.contacts.model.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    fun getAllUsers(): Flow<List<User>> = userUseCase.getUsers()
}