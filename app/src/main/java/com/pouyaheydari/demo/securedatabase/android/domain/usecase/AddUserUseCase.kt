package com.pouyaheydari.demo.securedatabase.android.domain.usecase

import com.pouyaheydari.demo.securedatabase.android.domain.model.User
import com.pouyaheydari.demo.securedatabase.android.domain.repository.UserRepository

class AddUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(name: String, email: String) {
        if (name.isBlank() || email.isBlank()) return
        userRepository.addUser(User(name = name, email = email))
    }
}
