package com.cyanlch.domain.model.anime

import kotlinx.serialization.Serializable

typealias AnimeId = String
typealias CharacterId = String

@Serializable
data class Anime(val id: AnimeId, val name: String)

@Serializable
data class AnimeCharacter(val id: CharacterId, val animeId: AnimeId, val name: String, val image: String)
