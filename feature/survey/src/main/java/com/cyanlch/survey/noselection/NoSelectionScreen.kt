package com.cyanlch.survey.noselection

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object NoSelectionScreen : Screen {
    data class State(
        val onBack: () -> Unit,
        val onHomeClick: () -> Unit,
    ) : CircuitUiState
}
