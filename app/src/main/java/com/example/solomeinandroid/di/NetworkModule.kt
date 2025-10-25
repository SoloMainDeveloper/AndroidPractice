package com.example.solomeinandroid.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.solomeinandroid.player.data.PlayersApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }

    single {
        val json = Json {
            explicitNulls = false
            ignoreUnknownKeys = true
        }
        Retrofit.Builder()
            .baseUrl("https://firestore.googleapis.com/v1/projects/valorantplayers-f89e1/databases/(default)/")
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
            .client(get())
            .build()
    }

    single<PlayersApi> { get<Retrofit>().create(PlayersApi::class.java) }
}