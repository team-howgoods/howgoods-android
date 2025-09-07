package com.cyanlch.domain.policy

import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId
import com.cyanlch.domain.model.character.AnimeCharacters

object SurveySelectionPolicy {
    const val MIN_ANIME = 1
    const val MAX_ANIME = 5
    const val MIN_CHARACTER = 1
    const val MAX_CHARACTER = 3
    const val MAX_GOODS = 20
    const val ITEMS_TO_SHOW_ON_MORE = 6

    fun isCharacterAllowed(
        selectedAnimeIds: Set<AnimeId>,
        characterListsByAnime: Map<AnimeId, AnimeCharacters>,
        candidateId: CharacterId,
    ): Boolean = selectedAnimeIds.any { aid ->
        characterListsByAnime[aid]?.characters.orEmpty().any { it.id == candidateId }
    }

    fun pruneCharactersNotIn(
        selectedAnimeIds: Set<AnimeId>,
        characterListsByAnime: Map<AnimeId, AnimeCharacters>,
        chosenCharacterIds: Set<CharacterId>,
    ): Set<CharacterId> {
        val allowed: Set<CharacterId> = selectedAnimeIds
            .flatMap { aid -> characterListsByAnime[aid]?.characters.orEmpty() }
            .map { it.id }
            .toSet()
        return chosenCharacterIds.intersect(allowed)
    }
}
