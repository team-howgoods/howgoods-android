package com.cyanlch.network.model.survey

import com.cyanlch.domain.model.character.Character
import com.cyanlch.domain.model.character.Characters
import kotlinx.serialization.Serializable

@Serializable
data class CharacterCatalogResponse(
    val animationId: Int,
    val animationName: String,
    val characters: List<CharacterDto>,
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
)

fun CharacterCatalogResponse.toDomain() = Characters(
    animationId = animationId,
    animationName = animationName,
    characters = characters.map { it.toDomain() },
)

private fun CharacterDto.toDomain(): Character = Character(
    id = id,
    name = name,
    imageUrl = imageUrl,
)
