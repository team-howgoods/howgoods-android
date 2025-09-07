package com.cyanlch.data.repository.impl

import com.cyanlch.data.datasource.survey.SurveyDataSource
import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.character.AnimeCharacters
import com.cyanlch.domain.repository.SurveyRepository
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(
    private val dataSource: SurveyDataSource,
) : SurveyRepository {
    override suspend fun fetchAnimeCatalog(): List<Anime> {
        return dataSource.fetchAnimeCatalog()
    }

    override suspend fun fetchCharactersByAnime(animationIds: List<Int>): List<AnimeCharacters> {
        return dataSource.fetchCharacters(animationIds)
    }
}
