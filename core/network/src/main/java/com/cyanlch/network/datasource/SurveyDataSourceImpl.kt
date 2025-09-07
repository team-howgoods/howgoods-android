package com.cyanlch.network.datasource

import com.cyanlch.data.datasource.survey.SurveyDataSource
import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.character.AnimeCharacters
import com.cyanlch.network.Endpoints
import com.cyanlch.network.Endpoints.SURVEY_CHARACTERS_PARAMETER
import com.cyanlch.network.model.survey.AnimeCatalogResponse
import com.cyanlch.network.model.survey.AnimeCharactersResponse
import com.cyanlch.network.model.survey.toDomain
import com.cyanlch.network.model.unwrap
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import javax.inject.Inject

class SurveyDataSourceImpl @Inject constructor(
    private val ktorClient: HttpClient,
) : SurveyDataSource {
    override suspend fun fetchAnimeCatalog(): List<Anime> {
        return ktorClient.get {
            url {
                path(Endpoints.SURVEY_ANIMATIONS)
            }
        }.unwrap<AnimeCatalogResponse>().toDomain()
    }

    override suspend fun fetchCharacters(animationIds: List<Int>): List<AnimeCharacters> {
        return ktorClient.get {
            url {
                path(Endpoints.SURVEY_CHARACTERS)
            }
            animationIds.forEach { id ->
                parameter(SURVEY_CHARACTERS_PARAMETER, id)
            }
        }.unwrap<AnimeCharactersResponse>().toDomain()
    }
}
