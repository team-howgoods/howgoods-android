package com.cyanlch.domain.policy

import com.cyanlch.domain.model.anime.AnimeCharacterList
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId

object SurveySelectionPolicy {
    const val MIN_ANIME = 1
    const val MAX_ANIME = 3
    const val MIN_CHARACTER = 1
    const val MAX_CHARACTER = 3

    fun isCharacterAllowed(
        selectedAnimeIds: Set<AnimeId>,
        characterListsByAnime: Map<AnimeId, AnimeCharacterList>,
        candidateId: CharacterId
    ): Boolean = selectedAnimeIds.any { aid ->
        characterListsByAnime[aid]?.characters.orEmpty().any { it.id == candidateId }
    }

    fun pruneCharactersNotIn(
        selectedAnimeIds: Set<AnimeId>,
        characterListsByAnime: Map<AnimeId, AnimeCharacterList>,
        chosenCharacterIds: Set<CharacterId>
    ): Set<CharacterId> {
        val allowed: Set<CharacterId> = selectedAnimeIds
            .flatMap { aid -> characterListsByAnime[aid]?.characters.orEmpty() }
            .map { it.id }
            .toSet()
        return chosenCharacterIds.filterTo(mutableSetOf()) { it in allowed }
    }
}
