package com.example.solomeinandroid

import android.app.Application
import com.example.solomeinandroid.di.dbModule
import com.example.solomeinandroid.di.mainModule
import com.example.solomeinandroid.di.networkModule
import com.example.solomeinandroid.player.di.playerFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mainModule, playerFeatureModule, networkModule, dbModule)
        }
    }
}