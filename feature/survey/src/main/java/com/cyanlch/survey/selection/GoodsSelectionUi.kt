package com.cyanlch.survey.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.WidthSpacer
import com.cyanlch.designsystem.button.HgButtonDefaults
import com.cyanlch.designsystem.button.HgSolidButton
import com.cyanlch.designsystem.search.HgSearchField
import com.cyanlch.designsystem.select.HgImageSelector
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.designsystem.ui.LocalHGColors
import com.cyanlch.survey.component.SurveyBottomBar
import com.cyanlch.survey.model.SelectedGoods
import com.cyanlch.survey.selection.component.SelectedGoodsRow
import com.cyanlch.ui.topbar.HgBasicTopBar
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent

@CircuitInject(GoodsSelectionScreen::class, ActivityRetainedComponent::class)
class GoodsSelectionUi : Ui<GoodsSelectionScreen.State> {
    @Composable
    override fun Content(
        state: GoodsSelectionScreen.State,
        modifier: Modifier,
    ) {
        HGTheme {
            Scaffold(
                topBar = { HgBasicTopBar(onBackClick = state.onBack) },
                bottomBar = {
                    SurveyBottomBar {
                        HgSolidButton(
                            colors = HgButtonDefaults.SolidButtonAlternativeColors,
                            onClick = state.onSkip,
                        ) {
                            HgText(
                                text = "다음에 할게요",
                                style = HGTypography.label1SemiBold,
                                tone = HgTextTone.Unspecified,
                            )
                        }
                        WidthSpacer(4)
                        HgSolidButton(
                            onClick = state.onNext,
                            enabled = state.selectedGoods.isNotEmpty(),
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            HgText(
                                text = "완료",
                                style = HGTypography.body2SemiBold,
                                tone = HgTextTone.Unspecified,
                            )
                        }
                    }
                },
            ) { inner ->
                GoodsSelectionContent(
                    state = state,
                    modifier = Modifier.padding(inner),
                )
            }
        }
    }
}

@Composable
private fun GoodsGroup(
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

@Composable
private fun GoodsSelectionContent(
    state: GoodsSelectionScreen.State,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LocalHGColors.current.bgDefault)
            .padding(horizontal = 16.dp),
    ) {
        val goodsGroups = state.items.groupBy { it.animationId }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                HgText(
                    text = "최근 관심 있는 굿즈가 있다면 골라주세요!\n" +
                        "최저가일 때 알려 드릴게요",
                    style = HGTypography.headlineSemiBold,
                )
                HeightSpacer(8)
                HgText(
                    text = "최대 3개 까지만 선택 가능해요",
                    style = HGTypography.label1Medium,
                    tone = HgTextTone.Assistive,
                )
                HeightSpacer(16)
                HgSearchField(
                    value = "",
                    onValueChange = {},
                    placeholder = "굿즈 이름을 입력해 주세요",
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { state.onSearchClick() },
                )
                SelectedGoodsRow(state)
                HeightSpacer(24)
            }

            items(goodsGroups.entries.toList()) { entry ->
                val animationName = entry.value.first().animationName
                GoodsGroup(
                    animationName = animationName,
                    goods = entry.value,
                    selectedGoods = state.selectedGoods,
                    onToggleGoods = state.onToggleGoods,
                )
            }
        }
    }
}
