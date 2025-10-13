package com.example.solomeinandroid.player.domain.model

import com.example.solomeinandroid.player.presentation.model.Team

class PlayerEntity (
    val name: String,
    val nick: String,
    val nationality: String,
    val imageUrl : String,
    val age: Int,
    val team: Team,
    val totalWinningsDollars: Int,
    val info: String
)