package com.pouyaheydari.demo.securedatabase.android.domain.di

import com.pouyaheydari.demo.securedatabase.android.domain.usecase.AddUserUseCase
import com.pouyaheydari.demo.securedatabase.android.domain.usecase.GetAllUsersFlowUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetAllUsersFlowUseCase)
    factoryOf(::AddUserUseCase)
}
