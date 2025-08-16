package com.cyanlch.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cyanlch.main.model.TabState
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object MainShellScreen : Screen {
    data class State(
        val tabs: List<TabState>,
        val onTabSelected: (Int) -> Unit,
        val content: @Composable (Modifier) -> Unit
    ): CircuitUiState
}