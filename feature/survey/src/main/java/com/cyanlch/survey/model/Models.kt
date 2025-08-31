package com.cyanlch.survey.model

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.anime.AnimeCharacterList
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId
import com.cyanlch.domain.model.goods.GoodsType

data class SurveyForm(
    val animeCatalog: List<Anime> = emptyList(),
    val selectedAnimeIds: Set<AnimeId> = emptySet(),
    val selectedCharacterIds: Set<CharacterId> = emptySet(),
    val characterListsByAnime: Map<AnimeId, AnimeCharacterList> = emptyMap(),
    val goodsTypes: List<GoodsType> = emptyList(),
    val selectedGoodsTypes: Set<Int> = emptySet(),
    val selectedGoodsIds: Set<Int> = emptySet(),
)

enum class SurveyStep { Anime, Character, GoodsType }

enum class FormFieldKey { AnimeSelection, CharacterSelection, GoodsTypeSelection, GoodsSelection }

data class FieldError(val field: FormFieldKey, val message: String)

data class ValidationResult(
    val isValid: Boolean,
    val errors: List<FieldError> = emptyList(),
)

data class SurveyUiState(
    val form: SurveyForm = SurveyForm(),
    val isLoading: Boolean = false,
    val lastErrorMessage: String? = null,
)
