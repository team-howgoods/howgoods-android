package com.cyanlch.network.model.survey

import com.cyanlch.domain.model.anime.Anime
import kotlinx.serialization.Serializable


@Serializable
data class AnimeCatalogResponse(
    val items: List<AnimeDto>,
)

@Serializable
data class AnimeDto(
    val animationId: Int,
    val name: String,
    val imageUrl: String,
)

fun AnimeCatalogResponse.toDomain(): List<Anime> = items.map { it.toDomain() }
private fun AnimeDto.toDomain(): Anime = Anime(
    id = animationId,
    name = name,
)
