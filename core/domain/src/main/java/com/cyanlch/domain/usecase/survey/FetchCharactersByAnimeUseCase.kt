package com.cyanlch.domain.usecase.survey

import com.cyanlch.domain.model.anime.AnimeCharacter
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchCharactersByAnimeUseCase @Inject constructor(
    private val repository: SurveyRepository,
) {
    suspend operator fun invoke(animeId: AnimeId): Result<List<AnimeCharacter>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                // 테스트용 임시 데이터
                when (animeId) {
                    "1" -> listOf(
                        AnimeCharacter(id = "1-1", animeId = "1", name = "몽키 D. 루피", image = ""),
                        AnimeCharacter(id = "1-2", animeId = "1", name = "로로노아 조로", image = ""),
                        AnimeCharacter(id = "1-3", animeId = "1", name = "나미", image = "")
                    )
                    "2" -> listOf(
                        AnimeCharacter(id = "2-1", animeId = "2", name = "우즈마키 나루토", image = ""),
                        AnimeCharacter(id = "2-2", animeId = "2", name = "우치하 사스케", image = ""),
                        AnimeCharacter(id = "2-3", animeId = "2", name = "하루노 사쿠라", image = "")
                    )
                    "3" -> listOf(
                        AnimeCharacter(id = "3-1", animeId = "3", name = "손오공", image = ""),
                        AnimeCharacter(id = "3-2", animeId = "3", name = "베지터", image = ""),
                        AnimeCharacter(id = "3-3", animeId = "3", name = "피콜로", image = "")
                    )
                    else -> emptyList()
                }
                // repository.fetchCharactersByAnime(animeId)
            }
        }
    }
}
