package com.cyanlch.survey.selection

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object GoodsSelectionScreen : Screen {
    data class State(
        val selectedGoodsIds: Set<Int>,
        val onToggleGoods: (Int) -> Unit,
        val onSearchClick: () -> Unit,
        val onNext: () -> Unit,
        val onSkip: () -> Unit,
        val onBack: () -> Unit,
    ) : CircuitUiState
}
