package com.cyanlch.survey.search

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object GoodsSearchScreen : Screen {
    data object State : CircuitUiState
}
