package com.pouyaheydari.demo.securedatabase.android.data.di

import com.pouyaheydari.demo.securedatabase.android.data.local.AppDatabase
import com.pouyaheydari.demo.securedatabase.android.data.local.UserDao
import com.pouyaheydari.demo.securedatabase.android.data.repository.UserRepositoryImpl
import com.pouyaheydari.demo.securedatabase.android.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<android.content.SharedPreferences> {
        androidContext().getSharedPreferences("prefs", android.content.Context.MODE_PRIVATE)
    }

    single<AppDatabase> {
        AppDatabase.getDatabase(androidContext(), get())
    }

    single<UserDao> { get<AppDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl(get()) }
}
