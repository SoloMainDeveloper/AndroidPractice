package com.example.solomeinandroid.player.domain.interactor

import com.example.solomeinandroid.player.data.repository.PlayersRepository

class PlayerInteractor(
    private val repository: PlayersRepository
) {
    suspend fun getPlayerList() = repository.getPlayerList()
}