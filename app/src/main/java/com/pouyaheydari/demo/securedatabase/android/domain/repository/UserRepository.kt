package com.pouyaheydari.demo.securedatabase.android.domain.repository

import com.pouyaheydari.demo.securedatabase.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    suspend fun addUser(user: User)
}
