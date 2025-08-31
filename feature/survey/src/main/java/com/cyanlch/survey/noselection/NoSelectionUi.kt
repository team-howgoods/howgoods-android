package com.cyanlch.survey.noselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.button.HgSolidButton
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.designsystem.ui.LocalHGColors
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent

@CircuitInject(NoSelectionScreen::class, ActivityRetainedComponent::class)
class NoSelectionUi : Ui<NoSelectionScreen.State> {
    @Composable
    override fun Content(
        state: NoSelectionScreen.State,
        modifier: Modifier,
    ) {
        HGTheme {
            Scaffold { inner ->
                NoSelectionContent(
                    onHomeClick = state.onHomeClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LocalHGColors.current.bgDefault)
                        .padding(inner),
                )
            }
        }
    }
}

@Composable
private fun NoSelectionContent(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        ) {
            HgText(
                text = "좋아하는 애니메이션을 선택하지 않으셨어요!",
                style = HGTypography.headlineSemiBold,
            )
            HeightSpacer(8)
            HgText(
                text = "대신 인기 많은 굿즈를 보여드릴게요",
                style = HGTypography.body1Medium,
            )
            HeightSpacer(24)
            HgSolidButton(
                contentPadding = PaddingValues(horizontal = 28.dp, vertical = 14.dp),
                onClick = onHomeClick,
            ) {
                HgText(
                    text = "홈으로 이동할게요",
                    style = HGTypography.body1Medium,
                    tone = HgTextTone.Unspecified,
                )
            }
        }
    }
}

@Preview
@Composable
private fun NoSelectionContentPreview() {
    HGTheme {
        NoSelectionContent(
            onHomeClick = {},
        )
    }
}
