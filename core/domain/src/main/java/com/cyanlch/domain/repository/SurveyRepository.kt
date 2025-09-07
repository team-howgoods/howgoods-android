package com.cyanlch.domain.repository

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.character.AnimeCharacters

interface SurveyRepository {
    suspend fun fetchAnimeCatalog(): List<Anime>
    suspend fun fetchCharactersByAnime(animationIds: List<Int>): List<AnimeCharacters>
    // suspend fun submit(payload: SurveySubmitDto)
}
