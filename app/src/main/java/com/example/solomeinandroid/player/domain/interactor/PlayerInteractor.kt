package com.example.solomeinandroid.player.domain.interactor

import com.example.solomeinandroid.player.data.repository.PlayersRepository
import com.example.solomeinandroid.player.domain.model.PlayerEntity

class PlayerInteractor(
    private val repository: PlayersRepository
) {
    suspend fun getPlayerList(richPlayersFirst : Boolean) = repository.getPlayerList(richPlayersFirst)

    fun observePlayerFirstSettings() = repository.observePlayerFirstSettings()

    suspend fun setRichPlayersFirstSetting(richPlayersFirst: Boolean) =
        repository.setRichPlayersFirstSettings(richPlayersFirst)

    suspend fun addFavorite(player: PlayerEntity) = repository.addFavorite(player)

    suspend fun removeFromFavorites(nick: String) {
        repository.removeFromFavorites(nick)
    }

    suspend fun isFavorite(nick: String): Boolean {
        return repository.isFavorite(nick)
    }

    suspend fun getFavorites() = repository.getFavorites()
}