package com.cyanlch.survey.model

import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId
import com.cyanlch.domain.model.goods.GoodsType
import com.cyanlch.domain.policy.SurveySelectionPolicy
import com.cyanlch.domain.usecase.survey.FetchAnimeCatalogUseCase
import com.cyanlch.domain.usecase.survey.FetchCharactersByAnimeUseCase
import com.cyanlch.domain.usecase.survey.FetchGoodsTypeUseCase
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.collections.emptyList

@ActivityRetainedScoped
class SurveyStore @Inject constructor(
    private val fetchAnimeCatalog: FetchAnimeCatalogUseCase,
    private val fetchCharactersByAnime: FetchCharactersByAnimeUseCase,
    private val fetchGoodsType: FetchGoodsTypeUseCase,
    // private val submitSurvey: SubmitSurveyUseCase
) {
    private val _uiState = MutableStateFlow(SurveyUiState())
    val uiState: StateFlow<SurveyUiState> = _uiState

    private inline val form get() = _uiState.value.form

    suspend fun loadAnimeCatalogIfEmpty() {
        if (form.animeCatalog.isNotEmpty()) return
        setLoading(true)
        val list = fetchAnimeCatalog().fold(
            onSuccess = { it },
            onFailure = {
                setErrorMessage(it.message ?: "Error")
                emptyList()
            },
        )
        updateForm { it.copy(animeCatalog = list) }
        setLoading(false)
    }

    suspend fun loadCharactersForSelectedAnime() {
        val need = form.selectedAnimeIds.filter { it !in form.characterListsByAnime }
        if (need.isEmpty()) {
            pruneCharacters()
            return
        }
        setLoading(true)
        fetchCharactersByAnime(need).fold(
            onSuccess = { lists ->
                val incoming = lists.associateBy { it.anime.id }
                val merged = form.characterListsByAnime
                    .toMutableMap()
                    .apply { putAll(incoming) }
                updateForm { it.copy(characterListsByAnime = merged) }
                pruneCharacters()
            },
            onFailure = {
                setErrorMessage(it.message ?: "Error")
            },
        )
        setLoading(false)
    }

    suspend fun loadGoodsTypeIfEmpty() {
        if (form.goodsTypes.isNotEmpty()) return
        setLoading(true)
        val list = fetchGoodsType().fold(
            onSuccess = { it },
            onFailure = {
                setErrorMessage(it.message ?: "Error")
                emptyList()
            },
        )
        updateForm { it.copy(goodsTypes = list) }
        setLoading(false)
    }

    private fun pruneCharacters() {
        val pruned = SurveySelectionPolicy.pruneCharactersNotIn(
            form.selectedAnimeIds,
            form.characterListsByAnime,
            form.selectedCharacterIds,
        )
        if (pruned != form.selectedCharacterIds) {
            updateForm { it.copy(selectedCharacterIds = pruned) }
        }
    }

    // ---------- Selections ----------
    fun selectOrDeselectAnime(animeId: AnimeId) = updateForm { f ->
        val next = f.selectedAnimeIds.toMutableSet()
        if (!next.add(animeId)) next.remove(animeId)
        if (next.size > SurveySelectionPolicy.MAX_ANIME) return@updateForm f
        f.copy(selectedAnimeIds = next)
    }

    fun selectOrDeselectCharacter(characterId: CharacterId) {
        if (!SurveySelectionPolicy.isCharacterAllowed(
                selectedAnimeIds = form.selectedAnimeIds,
                characterListsByAnime = form.characterListsByAnime,
                candidateId = characterId,
            )
        ) {
            return
        }

        updateForm { f ->
            val next = f.selectedCharacterIds.toMutableSet()
            if (!next.add(characterId)) next.remove(characterId)
            if (next.size > SurveySelectionPolicy.MAX_CHARACTER) return@updateForm f
            f.copy(selectedCharacterIds = next)
        }
    }

    fun selectOrDeselectGoodsType(goodsTypeId: Int) = updateForm { f ->
        val next = f.selectedGoodsTypes.toMutableSet()
        if (!next.add(goodsTypeId)) next.remove(goodsTypeId)
        f.copy(selectedGoodsTypes = next)
    }

    fun selectOrDeselectAllGoodsType() = updateForm { f ->
        val shouldSelectAll = f.selectedGoodsTypes.isEmpty()
        val next = if (shouldSelectAll) {
            f.goodsTypes.map { it.goodsTypeId }.toSet()
        } else {
            emptySet()
        }

        f.copy(selectedGoodsTypes = next)
    }

    // ---------- Validation & Submit ----------
    fun validate(step: SurveyStep): ValidationResult = when (step) {
        SurveyStep.Anime -> SurveyValidator.validateAnime(form)
        SurveyStep.Character -> SurveyValidator.validateCharacter(form)
        SurveyStep.Goods -> SurveyValidator.validateGoods(form)
    }

   /* suspend fun submit(): Result<Unit> {
        val all = listOf(validate(SurveyStep.Anime), validate(SurveyStep.Character), validate(SurveyStep.Goods))
        if (all.any { !it.isValid }) return Result.failure(IllegalStateException("validation failed"))
        setLoading(true)
        val r = runCatching {
            submitSurvey(
                animeIds = form.selectedAnimeIds.toList(),
                characterIds = form.selectedCharacterIds.toList(),
                goods = form.selectedGoods.toList()
            )
        }
        setLoading(false)
        return r
    }*/

    private inline fun updateForm(transform: (SurveyForm) -> SurveyForm) {
        _uiState.update { it.copy(form = transform(it.form), lastErrorMessage = null) }
    }
    private fun setLoading(b: Boolean) {
        _uiState.update { it.copy(isLoading = b, lastErrorMessage = null) }
    }

    private fun setErrorMessage(message: String) {
        _uiState.update { it.copy(lastErrorMessage = message) }
    }
}
