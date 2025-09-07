package com.cyanlch.domain.model.anime

import kotlinx.serialization.Serializable

typealias AnimeId = Int
typealias CharacterId = Int

@Serializable
data class Anime(val id: AnimeId, val name: String)
