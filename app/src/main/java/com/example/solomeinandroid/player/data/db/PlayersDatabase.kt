package com.example.solomeinandroid.player.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.solomeinandroid.player.data.dao.PlayerDao
import com.example.solomeinandroid.player.data.entity.PlayerDbEntity

@Database(entities = [PlayerDbEntity::class], version = 1)
abstract class PlayersDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}