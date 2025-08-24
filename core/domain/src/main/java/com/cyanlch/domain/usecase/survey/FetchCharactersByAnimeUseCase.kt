package com.cyanlch.domain.usecase.survey

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.anime.AnimeCharacter
import com.cyanlch.domain.model.anime.AnimeCharacterList
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchCharactersByAnimeUseCase @Inject constructor(
    private val repository: SurveyRepository,
) {
    suspend operator fun invoke(animeIds: List<AnimeId>): Result<List<AnimeCharacterList>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val resultList = mutableListOf<AnimeCharacterList>()
                for (animeId in animeIds) {
                    val currentAnime: Anime
                    val currentCharacters: List<AnimeCharacter>
                    when (animeId) {
                        1 -> {
                            currentAnime = Anime(id = 1, name = "원피스") // 예시 애니메이션 정보
                            currentCharacters = listOf(
                                AnimeCharacter(id = 101, animeId = 1, name = "몽키 D. 루피", imageUrl = ""),
                                AnimeCharacter(id = 102, animeId = 1, name = "로로노아 조로", imageUrl = ""),
                                AnimeCharacter(id = 103, animeId = 1, name = "나미", imageUrl = ""),
                            )
                            resultList.add(AnimeCharacterList(anime = currentAnime, characters = currentCharacters))
                        }
                        2 -> {
                            currentAnime = Anime(id = 2, name = "나루토") // 예시 애니메이션 정보
                            currentCharacters = listOf(
                                AnimeCharacter(id = 201, animeId = 2, name = "우즈마키 나루토", imageUrl = ""),
                                AnimeCharacter(id = 202, animeId = 2, name = "우치하 사스케", imageUrl = ""),
                                AnimeCharacter(id = 203, animeId = 2, name = "하루노 사쿠라", imageUrl = ""),
                            )
                            resultList.add(AnimeCharacterList(anime = currentAnime, characters = currentCharacters))
                        }
                        3 -> {
                            currentAnime = Anime(id = 3, name = "드래곤볼") // 예시 애니메이션 정보
                            currentCharacters = listOf(
                                AnimeCharacter(id = 301, animeId = 3, name = "손오공", imageUrl = ""),
                                AnimeCharacter(id = 302, animeId = 3, name = "베지터", imageUrl = ""),
                                AnimeCharacter(id = 303, animeId = 3, name = "피콜로", imageUrl = ""),
                            )
                            resultList.add(AnimeCharacterList(anime = currentAnime, characters = currentCharacters))
                        }
                        // 다른 animeId에 대한 케이스 추가 가능
                    }
                }
                resultList
                // repository.fetchCharactersByAnime(animeId)
            }
        }
    }
}
