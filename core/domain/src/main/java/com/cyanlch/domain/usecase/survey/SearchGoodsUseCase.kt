package com.cyanlch.domain.usecase.survey

import com.cyanlch.domain.model.goods.Goods
import javax.inject.Inject

private val DUMMY_GOODS = listOf(
    Goods(
        id = 1,
        name = "주술회전 극장판0 스티커/고죠 사토루 유카타",
        imageUrl = "https://animate.godohosting.com/Goods/4550621155161.jpg",
    ),
    Goods(
        id = 2,
        name = "주술회전 자개풍 시리즈 아크릴키홀더 Vol.2 이타도리 유지",
        imageUrl = "https://animate.godohosting.com/Goods/4580722007922.jpg",
    ),
    Goods(
        id = 3,
        name = "귀멸의 칼날 렌고쿠 쿄쥬로 아크릴 스탠드",
        imageUrl = "https://example.com/goods3.jpg",
    ),
    Goods(
        id = 4,
        name = "귀멸의 칼날 탄지로 피규어",
        imageUrl = "https://example.com/goods4.jpg",
    ),
    Goods(
        id = 5,
        name = "원피스 루피 해적왕 모자",
        imageUrl = "https://example.com/goods5.jpg",
    ),
    Goods(
        id = 6,
        name = "원피스 조로 검 세트",
        imageUrl = "https://example.com/goods6.jpg",
    ),
)

data class GoodsSearchResult(
    val items: List<Goods>,
    val nextCursor: Int?,
)

class SearchGoodsUseCase @Inject constructor() {
    suspend operator fun invoke(query: String, cursor: Int?): Result<GoodsSearchResult> =
        runCatching {
            // simulate network error
            if (query.contains("error", ignoreCase = true)) {
                throw IllegalStateException("API error")
            }
            val filtered = DUMMY_GOODS.filter { it.name.contains(query, ignoreCase = true) }
            val start = cursor ?: 0
            val pageSize = 2
            val slice = filtered.drop(start).take(pageSize)
            val next = if (start + pageSize < filtered.size) start + pageSize else null
            GoodsSearchResult(items = slice, nextCursor = next)
        }
}
