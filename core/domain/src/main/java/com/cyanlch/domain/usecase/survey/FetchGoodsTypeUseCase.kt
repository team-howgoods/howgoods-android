package com.cyanlch.domain.usecase.survey

import com.cyanlch.domain.model.goods.GoodsType
import com.cyanlch.domain.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchGoodsTypeUseCase @Inject constructor(
    private val repository: SurveyRepository,
) {
    suspend operator fun invoke(): Result<List<GoodsType>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                GoodsTypeFixtures.all
            }
        }
    }
}

object GoodsTypeFixtures {
    private const val SAMPLE_IMG =
        "https://cdn.mariooutlet.com/Product/A0462/B6W/P000733796_d1.jpg"

    val AcrylicStand = GoodsType(1, "아크릴 스탠드", SAMPLE_IMG)
    val TShirt = GoodsType(2, "티셔츠", SAMPLE_IMG)
    val Keyring = GoodsType(3, "키링", SAMPLE_IMG)
    val Mascot = GoodsType(4, "마스코트", SAMPLE_IMG)
    val Stationery = GoodsType(5, "문구류", SAMPLE_IMG)
    val PhotoCard = GoodsType(6, "포토카드", SAMPLE_IMG)
    val Doll = GoodsType(7, "인형", SAMPLE_IMG)
    val Etc = GoodsType(8, "기타", SAMPLE_IMG)

    val all: List<GoodsType> = listOf(
        AcrylicStand,
        TShirt,
        Keyring,
        Mascot,
        Stationery,
        PhotoCard,
        Doll,
        Etc,
    )

    fun byId(id: Int): GoodsType? = all.firstOrNull { it.goodsTypeId == id }
}
