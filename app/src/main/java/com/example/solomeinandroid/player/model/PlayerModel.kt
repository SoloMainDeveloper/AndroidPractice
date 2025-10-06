package com.example.solomeinandroid.player.model

import kotlinx.serialization.Serializable

data class Team (
    val name: String,
    val imageUrl: String
)

@Serializable
data class PlayerModel(
    val name: String,
    val nick: String,
    val nationality: String,
    val imageUrl : String,
    val age: Int,
    val team: Team,
    val totalWinningsDollars: Int,
    val info: String
)