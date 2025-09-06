package com.cyanlch.survey.selection.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.select.HgImageSelector
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.survey.model.SelectedGoods
import com.cyanlch.survey.selection.GoodsSelectionScreen


@Composable
fun GoodsGroup(
    animationName: String,
    goods: List<GoodsSelectionScreen.GoodsItem>,
    selectedGoods: List<SelectedGoods>,
    onToggleGoods: (SelectedGoods) -> Unit,
) {
    var visibleCount by remember(animationName) {
        mutableIntStateOf(minOf(4, goods.size))
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        HgText(
            text = animationName,
            style = HGTypography.body1SemiBold,
        )
        HeightSpacer(8)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            goods.take(visibleCount).forEach { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(108.dp),
                ) {
                    val order = selectedGoods.indexOfFirst { it.id == item.id }
                        .takeIf { it >= 0 }?.plus(1)
                    HgImageSelector(
                        imageUrl = item.imageUrl,
                        contentDescription = item.name,
                        selected = order != null,
                        onClick = {
                            onToggleGoods(SelectedGoods(item.id, item.name, item.imageUrl))
                        },
                        modifier = Modifier.size(108.dp),
                        cnt = order,
                    )
                    HeightSpacer(4)
                    HgText(
                        text = item.name,
                        style = HGTypography.label1Medium,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        if (visibleCount < goods.size) {
            HeightSpacer(8)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        visibleCount = minOf(visibleCount + 6, goods.size)
                    },
                contentAlignment = Alignment.Center,
            ) {
                HgText(
                    text = "더보기",
                    style = HGTypography.body2SemiBold,
                    tone = HgTextTone.BrandPrimary,
                )
            }
        }
        HeightSpacer(24)
    }
}
