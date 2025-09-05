package com.pouyaheydari.demo.securedatabase.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    fun addUser(name: String, email: String) {
        if (name.isBlank() || email.isBlank()) {
            // Optionally, handle empty input (e.g., show a message to the user)
            return
        }
        viewModelScope.launch {
            val user = User(name = name, email = email)
            userDao.insertUser(user)
        }
    }
}

class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
