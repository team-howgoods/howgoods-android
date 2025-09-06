package com.cyanlch.domain.model.goods

import com.cyanlch.domain.model.anime.AnimeId

/**
 * Represents a goods item along with its parent animation information.
 */
data class GoodsSelectionItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val animationId: AnimeId,
    val animationName: String,
)

