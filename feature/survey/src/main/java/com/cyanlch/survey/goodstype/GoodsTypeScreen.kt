package com.cyanlch.survey.goodstype

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object GoodsTypeScreen : Screen {
    data class State(
        val goodsTypes: List<GoodsTypeGridItem>,
        val selectedGoodsTypes: Set<Int>,
        val selectedGoodsTypeCount: Int,
        val onToggleGoodsType: (Int) -> Unit,
        val onToggleAllGoodsType: () -> Unit,
        val onNext: () -> Unit,
        val onBack: () -> Unit,
    ) : CircuitUiState
}

data class GoodsTypeGridItem(
    val goodsTypeId: Int,
    val name: String,
    val imageUrl: String,
    val isSelected: Boolean,
    val isEnabled: Boolean = true,
)
