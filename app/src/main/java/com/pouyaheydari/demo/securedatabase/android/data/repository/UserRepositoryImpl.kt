package com.pouyaheydari.demo.securedatabase.android.data.repository

import com.pouyaheydari.demo.securedatabase.android.data.local.UserDao
import com.pouyaheydari.demo.securedatabase.android.data.mapper.toDomain
import com.pouyaheydari.demo.securedatabase.android.data.mapper.toEntity
import com.pouyaheydari.demo.securedatabase.android.domain.model.User
import com.pouyaheydari.demo.securedatabase.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun getAllUsers(): Flow<List<User>> =
        userDao.getAllUsers().map { entities -> entities.map { it.toDomain() } }

    override suspend fun addUser(user: User) {
        userDao.insertUser(user.toEntity())
    }
}
