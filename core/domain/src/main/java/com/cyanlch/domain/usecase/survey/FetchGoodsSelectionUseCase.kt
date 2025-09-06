package com.cyanlch.domain.usecase.survey

import com.cyanlch.domain.model.goods.GoodsSelectionItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchGoodsSelectionUseCase @Inject constructor() {
    suspend operator fun invoke(): Result<List<GoodsSelectionItem>> {
        return withContext(Dispatchers.IO) {
            runCatching { GoodsSelectionFixtures.all }
        }
    }
}

object GoodsSelectionFixtures {
    private const val SAMPLE_JJK_IMG1 =
        "https://animate.godohosting.com/Goods/4550621155161.jpg"
    private const val SAMPLE_JJK_IMG2 =
        "https://animate.godohosting.com/Goods/4580722007922.jpg"
    private const val SAMPLE_JJK_IMG3 = "https://example.com/goods7.jpg"
    private const val SAMPLE_JJK_IMG4 = "https://example.com/goods8.jpg"
    private const val SAMPLE_JJK_IMG5 = "https://example.com/goods9.jpg"
    private const val SAMPLE_DS_IMG1 = "https://example.com/goods3.jpg"
    private const val SAMPLE_DS_IMG2 = "https://example.com/goods4.jpg"
    private const val SAMPLE_OP_IMG1 = "https://example.com/goods5.jpg"
    private const val SAMPLE_OP_IMG2 = "https://example.com/goods6.jpg"

    val all: List<GoodsSelectionItem> = listOf(
        GoodsSelectionItem(
            id = 1,
            name = "주술회전 극장판0 스티커/고죠 사토루 유카타",
            imageUrl = SAMPLE_JJK_IMG1,
            animationId = 6,
            animationName = "주술회전",
        ),
        GoodsSelectionItem(
            id = 2,
            name = "주술회전 자개풍 시리즈 아크릴키홀더 Vol.2 이타도리 유지",
            imageUrl = SAMPLE_JJK_IMG2,
            animationId = 6,
            animationName = "주술회전",
        ),
        GoodsSelectionItem(
            id = 3,
            name = "귀멸의 칼날 렌고쿠 쿄쥬로 아크릴 스탠드",
            imageUrl = SAMPLE_DS_IMG1,
            animationId = 4,
            animationName = "데몬슬레이어",
        ),
        GoodsSelectionItem(
            id = 4,
            name = "귀멸의 칼날 탄지로 피규어",
            imageUrl = SAMPLE_DS_IMG2,
            animationId = 4,
            animationName = "데몬슬레이어",
        ),
        GoodsSelectionItem(
            id = 5,
            name = "원피스 루피 해적왕 모자",
            imageUrl = SAMPLE_OP_IMG1,
            animationId = 1,
            animationName = "원피스",
        ),
        GoodsSelectionItem(
            id = 6,
            name = "원피스 조로 검 세트",
            imageUrl = SAMPLE_OP_IMG2,
            animationId = 1,
            animationName = "원피스",
        ),
        GoodsSelectionItem(
            id = 7,
            name = "주술회전 손거울 고죠 사토루",
            imageUrl = SAMPLE_JJK_IMG3,
            animationId = 6,
            animationName = "주술회전",
        ),
        GoodsSelectionItem(
            id = 8,
            name = "주술회전 쿠키 세트 이타도리·후시구로·쿠기사키",
            imageUrl = SAMPLE_JJK_IMG4,
            animationId = 6,
            animationName = "주술회전",
        ),
        GoodsSelectionItem(
            id = 9,
            name = "주술회전 포스트카드 컬렉션 모든 등장인물 한정판 세트",
            imageUrl = SAMPLE_JJK_IMG5,
            animationId = 6,
            animationName = "주술회전",
        ),
    )
}

