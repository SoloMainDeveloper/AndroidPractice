package com.example.solomeinandroid.player.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
class PlayerListResponse(
    val documents: List<PlayerListDocument>?,
)

@Keep
@Serializable
class PlayerListDocument(
    val name: String? = null,
    val fields: PlayerFirestoreModel?,
)

@Keep
@Serializable
class PlayerFirestoreModel(
    val id: StringFirestoreModel?,
    val name: StringFirestoreModel?,
    val info: StringFirestoreModel?,
    val nickname: StringFirestoreModel?,
    val nationality: StringFirestoreModel?,
    val team: StringFirestoreModel?,
    val imageUrl: StringFirestoreModel?,
    val teamImageUrl: StringFirestoreModel?,
    val age: NumberFirestoreModel?,
    val totalWinnings: NumberFirestoreModel?
)
