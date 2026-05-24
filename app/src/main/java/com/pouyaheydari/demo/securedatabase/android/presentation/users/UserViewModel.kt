package com.pouyaheydari.demo.securedatabase.android.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.demo.securedatabase.android.domain.model.User
import com.pouyaheydari.demo.securedatabase.android.domain.usecase.AddUserUseCase
import com.pouyaheydari.demo.securedatabase.android.domain.usecase.GetAllUsersFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(
    getAllUsersFlowUseCase: GetAllUsersFlowUseCase,
    private val addUserUseCase: AddUserUseCase,
) : ViewModel() {

    val allUsers: Flow<List<User>> = getAllUsersFlowUseCase()

    fun addUser(name: String, email: String) {
        viewModelScope.launch {
            addUserUseCase(name, email)
        }
    }
}
