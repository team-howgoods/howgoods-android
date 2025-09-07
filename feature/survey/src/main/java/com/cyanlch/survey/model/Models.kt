package com.cyanlch.survey.model

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId
import com.cyanlch.domain.model.character.AnimeCharacters
import com.cyanlch.domain.model.goods.GoodsType

data class SelectedGoods(
    val id: Int,
    val name: String,
    val imageUrl: String,
)

data class SurveyForm(
    val animeCatalog: List<Anime> = emptyList(),
    val selectedAnimeIds: Set<AnimeId> = emptySet(),
    val selectedCharacterIds: Set<CharacterId> = emptySet(),
    val characterListsByAnime: Map<AnimeId, AnimeCharacters> = emptyMap(),
    val goodsTypes: List<GoodsType> = emptyList(),
    val selectedGoodsTypeIds: Set<Int> = emptySet(),
    val selectedGoods: List<SelectedGoods> = emptyList(),
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
