package com.cyanlch.domain.model.goods

import kotlinx.serialization.Serializable

@Serializable
data class GoodsType(val goodsTypeId: Int, val name: String, val imageUrl: String)
