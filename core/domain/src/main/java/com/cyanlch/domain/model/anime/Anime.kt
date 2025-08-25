package com.cyanlch.domain.model.anime

import kotlinx.serialization.Serializable

typealias AnimeId = Int
typealias CharacterId = Int

@Serializable
data class Anime(val id: AnimeId, val name: String)

@Serializable
data class AnimeCharacter(
    val id: CharacterId,
    val animeId: AnimeId,
    val name: String,
    val imageUrl: String,
)

@Serializable
data class AnimeCharacterList(val anime: Anime, val characters: List<AnimeCharacter>)
