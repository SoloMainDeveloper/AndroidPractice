package com.example.profile.data.provider

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.profile.data.serializer.ProfileSerializer
import com.example.solomeinandroid.profile.data.entity.ProfileEntity

class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}