package com.cyanlch.survey.selection

import com.cyanlch.survey.model.SelectedGoods
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object GoodsSelectionScreen : Screen {
    data class GoodsItem(
        val id: Int,
        val name: String,
        val imageUrl: String,
        val animeId: Int,
        val animeName: String,
    )

    data class State(
        val items: List<GoodsItem>,
        val selectedGoods: List<SelectedGoods>,
        val onToggleGoods: (SelectedGoods) -> Unit,
        val onSearchClick: () -> Unit,
        val onNext: () -> Unit,
        val onSkip: () -> Unit,
        val onBack: () -> Unit,
    ) : CircuitUiState
}
