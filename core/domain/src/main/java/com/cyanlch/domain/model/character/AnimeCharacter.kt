package com.cyanlch.domain.model.character

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId
import kotlinx.serialization.Serializable

@Serializable
data class AnimeCharacter(
    val id: CharacterId,
    val animeId: AnimeId,
    val name: String,
    val imageUrl: String,
)

@Serializable
data class AnimeCharacters(
    val anime: Anime,
    val characters: List<AnimeCharacter>,
)
