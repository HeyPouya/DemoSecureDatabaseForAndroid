package com.pouyaheydari.demo.securedatabase.android.presentation.di

import com.pouyaheydari.demo.securedatabase.android.presentation.users.UserViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::UserViewModel)
}
