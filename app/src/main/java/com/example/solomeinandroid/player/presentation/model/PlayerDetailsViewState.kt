package com.example.solomeinandroid.player.presentation.model

data class PlayerDetailsViewState(
    val player: PlayerModel,
    val isFavorite: Boolean = false
)
