package com.cyanlch.survey.goodstype

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.button.HgSolidButton
import com.cyanlch.designsystem.decoration.WhiteFadeBar
import com.cyanlch.designsystem.select.HgImageSelector
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.designsystem.ui.LocalHGColors
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
                    Box(
                        modifier = Modifier
                            .background(LocalHGColors.current.bgDefault)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp),
                    ) {
                        Row {
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
                        WhiteFadeBar(
                            horizontal = false,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .height(24.dp)
                                .offset(y = (-24).dp)
                                .zIndex(1f),
                        )
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
                .fillMaxWidth()
                .background(LocalHGColors.current.bgDefault),
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
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(28.dp),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                PrimaryColorButton(
                    text = "전체 선택",
                    onClick = { state.onToggleAllGoodsType() },
                )

                for (goodsType in state.goodsTypes) {
                    HgImageSelector(
                        imageUrl = goodsType.imageUrl,
                        selected = goodsType.isSelected,
                        onClick = { state.onToggleGoodsType(goodsType.goodsTypeId) },
                        caption = goodsType.name,
                        modifier = Modifier.size(108.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun PrimaryColorButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(108.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(HGTheme.colors.primary)
            .clickable(onClick = onClick),
    ) {
        HgText(
            text = text,
            style = HGTypography.body1SemiBold,
            tone = HgTextTone.White,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
