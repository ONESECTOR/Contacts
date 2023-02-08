package com.sector.contacts.ui.fragments.add.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sector.contacts.entity.User
import com.sector.contacts.model.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    fun addUser(name: String, surname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(0, name, surname)
            userUseCase.addUser(user)
        }
    }
}