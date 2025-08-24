package com.cyanlch.survey.character

import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object CharacterScreen : Screen {
    data class State(
        val selectedAnimeIds: Set<AnimeId>,
        val canSelectMore: Boolean,
        val isLoading: Boolean,
        val onToggleAnimeCharacter: (CharacterId) -> Unit,
        val onNext: () -> Unit,
        val onBack: () -> Unit,
    ) : CircuitUiState
}
