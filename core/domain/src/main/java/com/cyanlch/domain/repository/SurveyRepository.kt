package com.cyanlch.domain.repository

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.anime.AnimeCharacter
import com.cyanlch.domain.model.anime.AnimeId

interface SurveyRepository {
    suspend fun fetchAnimeCatalog(): List<Anime>
    suspend fun fetchCharactersByAnime(animeId: AnimeId): List<AnimeCharacter>
    // suspend fun submit(payload: SurveySubmitDto)
}
