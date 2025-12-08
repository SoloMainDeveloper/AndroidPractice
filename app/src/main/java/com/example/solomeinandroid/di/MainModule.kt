package com.example.solomeinandroid.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.solomeinandroid.Players
import com.example.core.navigation.Route
import com.example.core.navigation.TopLevelBackStack
import com.example.solomeinandroid.player.data.cache.BadgeCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainModule = module {
    single { TopLevelBackStack<Route>(Players) }
    single { getDataStore(androidContext())}
    single { BadgeCache() }
}

fun getDataStore(androidContext: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }
}
