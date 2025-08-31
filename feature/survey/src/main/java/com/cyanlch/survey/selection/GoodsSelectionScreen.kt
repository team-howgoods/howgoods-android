package com.cyanlch.survey.selection

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object GoodsSelectionScreen : Screen {
    data class State(
        val searchText: String,
        val onSearchTextChange: (String) -> Unit,
        val selectedGoodsIds: Set<Int>,
        val onToggleGoods: (Int) -> Unit,
        val onNext: () -> Unit,
        val onSkip: () -> Unit,
        val onBack: () -> Unit,
    ) : CircuitUiState
}
