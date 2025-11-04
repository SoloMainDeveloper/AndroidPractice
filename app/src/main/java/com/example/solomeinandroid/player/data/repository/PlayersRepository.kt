package com.example.solomeinandroid.player.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.solomeinandroid.player.data.PlayersApi
import com.example.solomeinandroid.player.data.db.PlayersDatabase
import com.example.solomeinandroid.player.data.entity.PlayerDbEntity
import com.example.solomeinandroid.player.data.mapper.PlayersResponseToEntityMapper
import com.example.solomeinandroid.player.domain.model.PlayerEntity
import com.example.solomeinandroid.player.presentation.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.map

class PlayersRepository (private val api: PlayersApi,
                         private val mapper: PlayersResponseToEntityMapper,
                         private val dataStore: DataStore<Preferences>,
                         private val database: PlayersDatabase
) {
    private val areRichFirst = booleanPreferencesKey(ARE_RICH_FIRST)
    suspend fun getPlayerList(richPlayersFirst : Boolean) : List<PlayerEntity> = withContext(Dispatchers.IO) {
        val response = api.getPlayerList()
        val players = mapper.mapResponse(response)

        if (richPlayersFirst) {
            players.sortedByDescending { it.totalWinningsDollars }
        } else {
            players
        }
    }

    suspend fun setRichPlayersFirstSettings(richPlayersFirst: Boolean) = withContext(Dispatchers.IO) {
        dataStore.edit {
            it[areRichFirst] = richPlayersFirst
        }
    }

    fun observePlayerFirstSettings(): Flow<Boolean> =
        dataStore.data.map { it[areRichFirst] ?: false }

    suspend fun getFavorites() =
        withContext(Dispatchers.IO) {
            database.playerDao().getAll().map {
                PlayerEntity(
                    name = it.name,
                    nick = it.nick,
                    nationality = it.nationality,
                    imageUrl = it.imageUrl,
                    age = it.age,
                    team = Team(it.teamName, it.teamImageUrl),
                    totalWinningsDollars = it.totalWinningsDollars,
                    info = it.info
                    )
            }
        }

    suspend fun addFavorite(player: PlayerEntity) =
        withContext(Dispatchers.IO) {
            database.playerDao().insert(
                PlayerDbEntity(
                    name = player.name,
                    nick = player.nick,
                    nationality = player.nationality,
                    imageUrl = player.imageUrl,
                    age = player.age,
                    teamName = player.team.name,
                    teamImageUrl = player.team.imageUrl,
                    totalWinningsDollars = player.totalWinningsDollars,
                    info = player.info
                )
            )
        }

    suspend fun removeFromFavorites(nick: String) =
        withContext(Dispatchers.IO) {
            database.playerDao().deleteByNick(nick)
        }

    suspend fun isFavorite(nick: String): Boolean {
        return database.playerDao().getPlayer(nick) != null
    }


    companion object {
        private const val ARE_RICH_FIRST = "ARE_RICH_FIRST"
    }
}


