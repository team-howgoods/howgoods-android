package com.cyanlch.survey.goodstype

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.button.HgSolidButton
import com.cyanlch.designsystem.select.HgImageSelector
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.designsystem.ui.LocalHGColors
import com.cyanlch.survey.component.SurveyBottomBar
import com.cyanlch.ui.topbar.HgBasicTopBar
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@CircuitInject(GoodsTypeScreen::class, ActivityRetainedComponent::class)
class GoodsTypeUi @Inject constructor() : Ui<GoodsTypeScreen.State> {
    @Composable
    override fun Content(
        state: GoodsTypeScreen.State,
        modifier: Modifier,
    ) {
        HGTheme {
            Scaffold(
                topBar = { HgBasicTopBar(onBackClick = state.onBack) },
                bottomBar = {
                    SurveyBottomBar {
                        HgSolidButton(
                            onClick = state.onNext,
                            enabled = state.selectedGoodsTypes.isNotEmpty(),
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            HgText(
                                text = "다음",
                                style = HGTypography.body2SemiBold,
                                tone = HgTextTone.Unspecified,
                            )
                        }
                    }
                },
            ) { inner ->
                GoodsTypeContent(
                    state = state,
                    modifier = modifier
                        .padding(inner),
                )
            }
        }
    }
}

@Composable
private fun GoodsTypeContent(
    modifier: Modifier = Modifier,
    state: GoodsTypeScreen.State,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LocalHGColors.current.bgDefault)
            .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            HgText(
                text = "필수 선택",
                style = HGTypography.caption12SemiBold,
                tone = HgTextTone.BrandPrimary,
            )
            HeightSpacer(8)
            HgText(
                text = "거의 다 왔어요!\n" +
                    "소장하고 싶은 굿즈 형태를 골라주세요",
                style = HGTypography.headlineSemiBold,
            )
            HeightSpacer(28)

            AllToggleButton(
                isAllChecked = state.selectedGoodsTypes.size == state.goodsTypes.size,
                onClick = { state.onToggleAllGoodsType() },
                modifier = Modifier.padding(vertical = 6.dp),
            )

            HeightSpacer(8)

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(
                    items = state.goodsTypes,
                    key = { it.goodsTypeId },
                ) { goodsType ->
                    HgImageSelector(
                        imageUrl = goodsType.imageUrl,
                        selected = goodsType.isSelected,
                        onClick = { state.onToggleGoodsType(goodsType.goodsTypeId) },
                        caption = goodsType.name,
                        modifier = Modifier.aspectRatio(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun AllToggleButton(
    modifier: Modifier = Modifier,
    isAllChecked: Boolean,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(4.dp),
    ) {
        Icon(
            painter = if (isAllChecked) {
                painterResource(R.drawable.ic_check_primary)
            } else {
                painterResource(R.drawable.ic_check_gray)
            },
            contentDescription = "all checked $isAllChecked",
            tint = Color.Unspecified,
        )
        HgText(
            text = "전체 선택",
            style = HGTypography.label1SemiBold,
        )
    }
}
