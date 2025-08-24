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
                // 테스트용 임시 데이터
                listOf(
                    Anime(id = "1", name = "원피스"),
                    Anime(id = "2", name = "나루토"),
                    Anime(id = "3", name = "드래곤볼"),
                    Anime(id = "4", name = "데몬슬레이어"),
                    Anime(id = "5", name = "공격의 거인"),
                    Anime(id = "6", name = "주술회전"),
                    Anime(id = "7", name = "헌터x헌터"),
                    Anime(id = "8", name = "스파이 패밀리"),
                    Anime(id = "9", name = "체인소우맨"),
                    Anime(id = "10", name = "블리치"),
                )
                // repository.fetchAnimeCatalog()
            }
        }
    }
}
