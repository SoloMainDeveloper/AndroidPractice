package com.example.solomeinandroid.player.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.solomeinandroid.player.data.entity.PlayerDbEntity

@Dao
interface PlayerDao {
    @Query("SELECT * FROM PlayerDbEntity")
    suspend fun getAll(): List<PlayerDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driverDbEntity: PlayerDbEntity)

    @Query("DELETE FROM PlayerDbEntity WHERE nick = :nick")
    suspend fun deleteByNick(nick: String)

    @Query("SELECT * FROM PlayerDbEntity WHERE nick = :nick")
    suspend fun getPlayer(nick: String): PlayerDbEntity?
}