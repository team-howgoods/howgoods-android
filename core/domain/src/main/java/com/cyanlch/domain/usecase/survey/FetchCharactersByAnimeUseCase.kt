package com.cyanlch.domain.usecase.survey

import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.character.AnimeCharacters
import com.cyanlch.domain.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchCharactersByAnimeUseCase @Inject constructor(
    private val repository: SurveyRepository,
) {
    suspend operator fun invoke(animeIds: List<AnimeId>): Result<List<AnimeCharacters>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                repository.fetchCharactersByAnime(animeIds)
            }
        }
    }
}
