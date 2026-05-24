package com.pouyaheydari.demo.securedatabase.android

import android.app.Application
import com.pouyaheydari.demo.securedatabase.android.data.di.dataModule
import com.pouyaheydari.demo.securedatabase.android.domain.di.domainModule
import com.pouyaheydari.demo.securedatabase.android.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SecureDatabaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@SecureDatabaseApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}
