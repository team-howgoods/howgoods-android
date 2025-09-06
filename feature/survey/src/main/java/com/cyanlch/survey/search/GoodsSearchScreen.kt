package com.cyanlch.survey.search

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object GoodsSearchScreen : Screen {
    data class State(
        val query: String,
        val items: List<GoodsSearchItem>,
        val isLoading: Boolean,
        val canLoadMore: Boolean,
        val errorMessage: String?,
        val onQueryChange: (String) -> Unit,
        val onToggleGoods: (GoodsSearchItem) -> Unit,
        val onLoadMore: () -> Unit,
        val onBack: () -> Unit,
        val onErrorShown: () -> Unit,
    ) : CircuitUiState
}

data class GoodsSearchItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isSelected: Boolean,
    val order: Int? = null,
)
