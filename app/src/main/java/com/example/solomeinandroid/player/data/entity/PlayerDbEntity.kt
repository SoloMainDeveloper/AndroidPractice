package com.example.solomeinandroid.player.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.solomeinandroid.player.presentation.model.Team

@Entity
class PlayerDbEntity (
    @PrimaryKey val nick: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nationality") val nationality: String,
    @ColumnInfo(name = "imageUrl") val imageUrl : String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "teamName") val teamName: String,
    @ColumnInfo(name = "teamImageUrl") val teamImageUrl: String,
    @ColumnInfo(name = "totalWinningsDollars") val totalWinningsDollars: Int,
    @ColumnInfo(name = "info") val info: String
)