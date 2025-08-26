package com.cyanlch.survey.goodstype

import com.cyanlch.domain.model.goods.GoodsType
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object GoodsTypeScreen : Screen {
    data class State(
        val goodsTypes: List<GoodsType>,
        val onToggleGoodsType: (GoodsType) -> Unit,
        val onNext: () -> Unit,
        val onBack: () -> Unit,
    ) : CircuitUiState
}
