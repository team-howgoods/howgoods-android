package com.cyanlch.main

import com.cyanlch.navigation.TabSpec
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object MainShellScreen : Screen {
    data class State(
        val tabs: List<TabSpec>,
        val currentIndex: Int,
        val onTabSelected: (Int) -> Unit,
    ) : CircuitUiState
}
