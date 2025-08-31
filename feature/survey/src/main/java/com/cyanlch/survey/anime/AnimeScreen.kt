package com.cyanlch.survey.anime

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.anime.AnimeId
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object AnimeScreen : Screen {
    data class State(
        val lastErrorMessage: String,
        val animeCatalog: List<Anime>,
        val selectedAnimeIds: Set<AnimeId>,
        val canSelectMore: Boolean,
        val isLoading: Boolean,
        val onToggleAnime: (AnimeId) -> Unit,
        val onNext: () -> Unit,
        val onSkip: () -> Unit,
    ) : CircuitUiState
}
