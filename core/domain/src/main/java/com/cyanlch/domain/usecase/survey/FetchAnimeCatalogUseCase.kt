package com.cyanlch.domain.usecase.survey

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchAnimeCatalogUseCase @Inject constructor(
    private val repository: SurveyRepository,
) {
    suspend operator fun invoke(): Result<List<Anime>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                repository.fetchAnimeCatalog()
            }
        }
    }
}
