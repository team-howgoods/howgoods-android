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
    private val fixtures: Map<AnimeId, AnimeCharacterList> by lazy {
        val onePiece = Anime(id = 1, name = "원피스")
        val naruto = Anime(id = 2, name = "나루토")
        val dragon = Anime(id = 3, name = "드래곤볼")
        val d4 = Anime(id = 4, name = "데몬슬레이어")
        val d5 = Anime(id = 5, name = "공격의 거인")
        fun ph(id: Int) = "https://picsum.photos/seed/$id/256/256"

        mapOf(
            1 to AnimeCharacterList(
                anime = onePiece,
                characters = listOf(
                    AnimeCharacter(id = 101, animeId = 1, name = "몽키 D. 루피", imageUrl = ph(101)),
                    AnimeCharacter(id = 102, animeId = 1, name = "로로노아 조로", imageUrl = ph(102)),
                    AnimeCharacter(id = 103, animeId = 1, name = "나미", imageUrl = ph(103)),
                ),
            ),
            2 to AnimeCharacterList(
                anime = naruto,
                characters = listOf(
                    AnimeCharacter(id = 201, animeId = 2, name = "우즈마키 나루토", imageUrl = ph(201)),
                    AnimeCharacter(id = 202, animeId = 2, name = "우치하 사스케", imageUrl = ph(202)),
                    AnimeCharacter(id = 203, animeId = 2, name = "하루노 사쿠라", imageUrl = ph(203)),
                ),
            ),
            3 to AnimeCharacterList(
                anime = dragon,
                characters = listOf(
                    AnimeCharacter(id = 301, animeId = 3, name = "손오공", imageUrl = ph(301)),
                    AnimeCharacter(id = 302, animeId = 3, name = "베지터", imageUrl = ph(302)),
                    AnimeCharacter(id = 303, animeId = 3, name = "피콜로", imageUrl = ph(303)),
                ),
            ),
            4 to AnimeCharacterList(
                anime = d4,
                characters = listOf(
                    AnimeCharacter(id = 301, animeId = 3, name = "손오공", imageUrl = ph(301)),
                    AnimeCharacter(id = 302, animeId = 3, name = "베지터", imageUrl = ph(302)),
                    AnimeCharacter(id = 303, animeId = 3, name = "피콜로", imageUrl = ph(303)),
                ),
            ),
            5 to AnimeCharacterList(
                anime = d5,
                characters = listOf(
                    AnimeCharacter(id = 301, animeId = 3, name = "손오공", imageUrl = ph(301)),
                    AnimeCharacter(id = 302, animeId = 3, name = "베지터", imageUrl = ph(302)),
                    AnimeCharacter(id = 303, animeId = 3, name = "피콜로", imageUrl = ph(303)),
                ),
            ),
        )
    }

    suspend operator fun invoke(animeIds: List<AnimeId>): Result<List<AnimeCharacterList>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                animeIds
                    .distinct()
                    .mapNotNull { fixtures[it] }
                // repository.fetchCharactersByAnime(animeId)
            }
        }
    }
}
