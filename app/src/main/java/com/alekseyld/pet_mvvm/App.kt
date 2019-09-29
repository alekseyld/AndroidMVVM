package com.alekseyld.pet_mvvm

import android.app.Application
import com.alekseyld.pet_mvvm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules(appModule)
        }
    }
}