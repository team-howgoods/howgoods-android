package com.cyanlch.survey.character

import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.model.anime.AnimeCharacter
import com.cyanlch.domain.model.anime.AnimeCharacterList
import com.cyanlch.domain.model.anime.AnimeId
import com.cyanlch.domain.model.anime.CharacterId
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object CharacterScreen : Screen {
    data class State(
        val groups: List<AnimeCharactersGroup>,
        val canSelectMore: Boolean,
        val isLoading: Boolean,
        val onToggleAnimeCharacter: (CharacterId) -> Unit,
        val onNext: () -> Unit,
        val onBack: () -> Unit,
    ) : CircuitUiState
}

data class CharacterRowItem(
    val id: CharacterId,
    val name: String,
    val imageUrl: String,
    val isSelected: Boolean,
    val isEnabled: Boolean
)

data class AnimeCharactersGroup(
    val anime: Anime,
    val items: List<CharacterRowItem>
)
