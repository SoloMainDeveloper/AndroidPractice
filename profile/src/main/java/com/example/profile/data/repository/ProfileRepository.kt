package com.example.profile.data.repository

import androidx.datastore.core.DataStore
import com.example.solomeinandroid.profile.data.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class ProfileRepository {
    private val dataStore : DataStore<ProfileEntity> by inject(DataStore::class.java, named("profile"))

    suspend fun observeProfile(): Flow<ProfileEntity> = dataStore.data

    suspend fun getProfile(): ProfileEntity? = dataStore.data.firstOrNull()

    suspend fun setProfile(photoUri: String, name: String, nick: String, url: String, lessonTime : String) =
        dataStore.updateData {
            it.toBuilder().apply {
                this.photoUri = photoUri
                this.name = name
                this.nick = nick
                this.url = url
                this.lessonTime = lessonTime
            }.build()
        }
}
