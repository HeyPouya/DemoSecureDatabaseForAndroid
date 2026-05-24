package com.pouyaheydari.demo.securedatabase.android.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.demo.securedatabase.android.domain.model.User
import com.pouyaheydari.demo.securedatabase.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val allUsers: Flow<List<User>> = userRepository.getAllUsers()

    fun addUser(name: String, email: String) {
        if (name.isBlank() || email.isBlank()) {
            // Optionally, handle empty input (e.g., show a message to the user)
            return
        }
        viewModelScope.launch {
            userRepository.addUser(User(name = name, email = email))
        }
    }
}

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
