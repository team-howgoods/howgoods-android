package com.cyanlch.data.datasource.survey

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.character.AnimeCharacters

interface SurveyDataSource {
    suspend fun fetchAnimeCatalog(): List<Anime>
    suspend fun fetchCharacters(animationIds: List<Int>): List<AnimeCharacters>
}
