package com.cyanlch.survey.selection.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.select.HgImageSelector
import com.cyanlch.designsystem.select.ImageSelectorSize
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGColors
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.domain.policy.SurveySelectionPolicy
import com.cyanlch.survey.model.SelectedGoods
import com.cyanlch.survey.selection.GoodsSelectionScreen

private val GoodsImageSize = 167.dp

@Composable
fun GoodsGroup(
    animeName: String,
    goods: List<GoodsSelectionScreen.GoodsItem>,
    selectedGoods: List<SelectedGoods>,
    onToggleGoods: (SelectedGoods) -> Unit,
) {
    var visibleCount by remember(animeName) {
        mutableIntStateOf(minOf(4, goods.size))
    }
    val orderById = remember(selectedGoods) {
        selectedGoods.withIndex().associate { (idx, sg) -> sg.id to (idx + 1) }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        HgText(
            text = animeName,
            style = HGTypography.headlineMedium,
        )

        HeightSpacer(8)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            goods.take(visibleCount).forEach { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(GoodsImageSize),
                ) {
                    val order = orderById[item.id]

                    HgImageSelector(
                        imageUrl = item.imageUrl,
                        contentDescription = item.name,
                        imageSize = ImageSelectorSize.Large,
                        selected = order != null,
                        onClick = {
                            onToggleGoods(
                                SelectedGoods(
                                    id = item.id,
                                    name = item.name,
                                    imageUrl = item.imageUrl,
                                ),
                            )
                        },
                        modifier = Modifier.size(GoodsImageSize),
                        cnt = order,
                    )
                    HeightSpacer(4)
                    HgText(
                        text = item.name,
                        style = HGTypography.label1Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        if (visibleCount < goods.size) {
            HeightSpacer(8)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = HGColors.lineAlternative,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(vertical = 12.dp)
                    .clickable {
                        visibleCount = minOf(
                            visibleCount + SurveySelectionPolicy.ITEMS_TO_SHOW_ON_MORE,
                            goods.size,
                        )
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement
                    .spacedBy(4.dp, Alignment.CenterHorizontally),
            ) {
                HgText(
                    text = "더보기",
                    style = HGTypography.body2SemiBold,
                    tone = HgTextTone.Alternative,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_move_20),
                    contentDescription = "더보기",
                    tint = HGColors.lineDefault,
                    modifier = Modifier
                        .rotate(90f),
                )
            }
        }
        HeightSpacer(28)
    }
}
