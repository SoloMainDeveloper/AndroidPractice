package com.example.solomeinandroid.player.data.mapper

import com.example.solomeinandroid.player.data.model.PlayerListResponse
import com.example.solomeinandroid.player.domain.model.PlayerEntity
import com.example.solomeinandroid.player.presentation.model.Team

class PlayersResponseToEntityMapper {
    fun mapResponse(response: PlayerListResponse): List<PlayerEntity> {
        return response.documents?.map { doc ->
            PlayerEntity(
                name = doc.fields?.name?.stringValue.orEmpty(),
                nick = doc.fields?.nickname?.stringValue.orEmpty(),
                nationality = doc.fields?.nationality?.stringValue.orEmpty(),
                imageUrl  = doc.fields?.imageUrl?.stringValue.orEmpty(),
                age = doc.fields?.age?.integerValue.orZero(),
                team = Team(doc.fields?.team?.stringValue.orEmpty(),
                    doc.fields?.teamImageUrl?.stringValue.orEmpty()),
                totalWinningsDollars = doc.fields?.totalWinnings?.integerValue.orZero(),
                info = doc.fields?.info?.stringValue.orEmpty()
            )
        }.orEmpty()
    }
}

private fun Int?.orZero() = this ?: 0
