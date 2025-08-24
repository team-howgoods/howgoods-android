package com.cyanlch.survey.anime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.WidthSpacer
import com.cyanlch.designsystem.button.HgButtonDefaults
import com.cyanlch.designsystem.button.HgSolidButton
import com.cyanlch.designsystem.select.HgTextSelector
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.designsystem.ui.LocalHGColors
import com.cyanlch.domain.model.anime.Anime
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@CircuitInject(AnimeScreen::class, ActivityRetainedComponent::class)
class AnimeUi @Inject constructor() : Ui<AnimeScreen.State> {
    @Composable
    override fun Content(
        state: AnimeScreen.State,
        modifier: Modifier,
    ) {
        HGTheme {
            Scaffold { inner ->
                AnimeScreenContent(
                    state = state,
                    modifier = modifier
                        .padding(inner)
                )
            }
        }
    }
}

@Composable
fun AnimeScreenContent(
    state: AnimeScreen.State,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LocalHGColors.current.bgDefault)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            HeightSpacer(48)
            HgText(
                text = "좋아하는 굿즈, 더 똑똑하게 살 수 있어요!",
                style = HGTypography.body1Medium,
            )
            HgText(
                text = "원하는 상품의 최저가 정보를 알려드릴게요",
                style = HGTypography.label1Medium,
                tone = HgTextTone.Assistive,
            )

            HeightSpacer(44)

            HgText(
                text = "먼저 가장 좋아하는 애니메이션을 골라주세요!",
                style = HGTypography.headlineSemiBold,
            )

            HeightSpacer(16)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        state.animeCatalog.forEach { anime ->
                            HgTextSelector(
                                text = anime.name,
                                selected = state.selectedAnimeIds.contains(anime.id),
                                onClick = { state.onToggleAnime(anime.id) },
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(LocalHGColors.current.bgDefault)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp, top = 24.dp),
        ) {
            Row {
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
                    modifier = Modifier
                        .weight(1f)
                ) {
                    HgText(
                        text = "완료",
                        style = HGTypography.body2SemiBold,
                        tone = HgTextTone.Unspecified,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AnimeScreenContentPreview() {
    HGTheme {
        AnimeScreenContent(
            state = AnimeScreen.State(
                animeCatalog = listOf(
                    Anime(id = "1", name = "Ane"),
                    Anime(id = "2", name = "Anime 2"),
                    Anime(id = "3", name = "Anime 3"),
                    Anime(id = "4", name = "Anime 4"),
                    Anime(id = "5", name = "Anime 5"),
                    Anime(id = "6", name = "Anime 6"),
                    Anime(id = "7", name = "Anime 7"),
                    Anime(id = "8", name = "Anime 8"),
                    Anime(id = "9", name = "Anime 9"),
                    Anime(id = "10", name = "Anime 10"),
                ),
                selectedAnimeIds = setOf("1"),
                canSelectMore = true,
                isLoading = false,
                onToggleAnime = {},
                onNext = {},
                onSkip = {},
            ),
            modifier = Modifier,
        )
    }
}
