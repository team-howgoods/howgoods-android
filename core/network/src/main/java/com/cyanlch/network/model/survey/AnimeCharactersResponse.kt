package com.cyanlch.network.model.survey

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.character.AnimeCharacter
import com.cyanlch.domain.model.character.AnimeCharacters
import kotlinx.serialization.Serializable

@Serializable
data class AnimeCharactersResponse(
    val items: List<AnimeCharactersDto>,
)

@Serializable
data class AnimeCharactersDto(
    val animationId: Int,
    val animationName: String,
    val characters: List<AnimeCharacterDto>,
)

@Serializable
data class AnimeCharacterDto(
    val characterId: Int,
    val name: String,
    val imageUrl: String,
)

fun AnimeCharactersResponse.toDomain() =
    items.map { animeCharacters ->
        AnimeCharacters(
            anime = Anime(
                id = animeCharacters.animationId,
                name = animeCharacters.animationName,
            ),
            characters = animeCharacters.characters.map { it.toDomain(animeCharacters.animationId) },
        )
    }

private fun AnimeCharacterDto.toDomain(animationId: Int) = AnimeCharacter(
    id = characterId,
    name = name,
    imageUrl = imageUrl,
    animeId = animationId,
)
