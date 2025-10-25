package com.example.solomeinandroid.player.data

import com.example.solomeinandroid.player.data.model.PlayerListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayersApi {
    @GET("documents/players")
    suspend fun getPlayerList(
        @Query("pageSize") pageSize: Int = 10
    ): PlayerListResponse
}