package com.cyanlch.survey.search.ext

import com.cyanlch.domain.usecase.survey.GoodsSearchResult
import com.cyanlch.survey.model.SelectedGoods
import com.cyanlch.survey.search.GoodsSearchItem

internal fun mapToSearchItems(
    goodsSearchResult: GoodsSearchResult,
    selectedGoods: List<SelectedGoods>,
): List<GoodsSearchItem> {
    val orderMap = selectedGoods.mapIndexed { index, g -> g.id to index + 1 }.toMap()
    val selectedIds = selectedGoods.map { it.id }
    return goodsSearchResult.items.map {
        GoodsSearchItem(
            id = it.id,
            name = it.name,
            imageUrl = it.imageUrl,
            isSelected = it.id in selectedIds,
            order = orderMap[it.id],
        )
    }
}
