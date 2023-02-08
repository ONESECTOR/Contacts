package com.sector.contacts.ui.fragments.edit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sector.contacts.entity.User
import com.sector.contacts.model.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditUserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    fun saveUser(id: Int, name: String, surname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(id = id, name = name, surname = surname)
            userUseCase.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userUseCase.deleteUser(user)
        }
    }
}