package com.xheghun.voyatrip

import android.app.Application
import com.xheghun.voyatrip.di.appModule
import com.xheghun.voyatrip.di.networkingModule
import com.xheghun.voyatrip.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VoyaTripApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VoyaTripApp)
            modules(listOf(appModule(), networkingModule(), viewModelModule()))
        }
    }
}