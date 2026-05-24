package com.pouyaheydari.demo.securedatabase.android.domain.usecase

import com.pouyaheydari.demo.securedatabase.android.domain.model.User
import com.pouyaheydari.demo.securedatabase.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetAllUsersFlowUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<List<User>> = userRepository.getAllUsers()
}
