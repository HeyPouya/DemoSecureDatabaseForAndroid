package com.pouyaheydari.demo.securedatabase.android.data.mapper

import com.pouyaheydari.demo.securedatabase.android.data.local.UserEntity
import com.pouyaheydari.demo.securedatabase.android.domain.model.User

fun UserEntity.toDomain(): User = User(id = id, name = name, email = email)

fun User.toEntity(): UserEntity = UserEntity(id = id, name = name, email = email)
