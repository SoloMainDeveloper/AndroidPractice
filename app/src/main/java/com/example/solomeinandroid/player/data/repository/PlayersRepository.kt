package com.example.solomeinandroid.player.data.repository

import com.example.solomeinandroid.player.data.PlayersApi
import com.example.solomeinandroid.player.data.mapper.PlayersResponseToEntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayersRepository (private val api: PlayersApi,
                         private val mapper: PlayersResponseToEntityMapper,
) {
    suspend fun getPlayerList() = withContext(Dispatchers.IO) {
        val response = api.getPlayerList()
        mapper.mapResponse(response)
    }}
