package com.cyanlch.domain.policy

import com.cyanlch.domain.model.anime.AnimeCharacter
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId

object SurveySelectionPolicy {
    const val MAX_ANIME = 3
    const val MAX_CHARACTER = 3

    fun isCharacterAllowed(
        selectedAnimeIds: Set<AnimeId>,
        charactersByAnime: Map<AnimeId, List<AnimeCharacter>>,
        candidateId: String,
    ): Boolean = selectedAnimeIds.any { id ->
        charactersByAnime[id].orEmpty().any { it.id == candidateId }
    }

    fun pruneCharactersNotIn(
        selectedAnimeIds: Set<AnimeId>,
        charactersByAnime: Map<AnimeId, List<AnimeCharacter>>,
        chosenCharacterIds: Set<CharacterId>,
    ): Set<CharacterId> {
        val allowed = selectedAnimeIds
            .flatMap { charactersByAnime[it].orEmpty() }
            .map { it.id }
            .toSet()
        return chosenCharacterIds.filterTo(mutableSetOf()) { it in allowed }
    }
}
