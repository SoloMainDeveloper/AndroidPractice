package com.example.solomeinandroid.profile.data.provider

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.solomeinandroid.profile.data.entity.ProfileEntity
import com.example.solomeinandroid.profile.data.serializer.ProfileSerializer

class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}