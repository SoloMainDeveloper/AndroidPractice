package com.example.solomeinandroid.di

import android.content.Context
import androidx.room.Room
import com.example.solomeinandroid.player.data.db.PlayersDatabase
import org.koin.dsl.module

val dbModule = module {
    single { DatabaseBuilder.getInstance(get()) }
}

object DatabaseBuilder {
    fun getInstance(context: Context) = buildRoomDB(context)

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            PlayersDatabase::class.java,
            "players"
        ).build()
}