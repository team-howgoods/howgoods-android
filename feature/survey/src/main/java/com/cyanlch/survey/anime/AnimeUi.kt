package com.cyanlch.survey.anime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
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
import com.cyanlch.survey.component.SurveyBottomBar
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
            Scaffold(
                topBar = {
                    Spacer(modifier = Modifier.height(44.dp))
                },
                bottomBar = {
                    SurveyBottomBar {
                        HgSolidButton(
                            colors = HgButtonDefaults.SolidButtonUnspecifiedColors,
                            onClick = state.onSkip,
                        ) {
                            HgText(
                                text = "다음에 할게요",
                                style = HGTypography.label1SemiBold.copy(
                                    textDecoration = TextDecoration.Underline,
                                ),
                                tone = HgTextTone.Unspecified,
                            )
                        }
                        WidthSpacer(4)
                        HgSolidButton(
                            onClick = state.onNext,
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
                AnimeScreenContent(
                    state = state,
                    modifier = modifier
                        .padding(inner),
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
            .background(LocalHGColors.current.bgDefault),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 48.dp, bottom = 120.dp),
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(LocalHGColors.current.bgDefault),
                ) {
                    HgText(
                        text = "최저가 굿즈 찾기, 시작해볼까요?",
                        style = HGTypography.body1Medium,
                        tone = HgTextTone.Assistive,
                    )
                    HeightSpacer(16)
                    HgText(
                        text = "먼저 가장 좋아하는 애니메이션을 골라주세요!",
                        style = HGTypography.headlineSemiBold,
                    )
                    HeightSpacer(4)
                    HgText(
                        text = "최대 5개 선택 가능",
                        style = HGTypography.label1Medium,
                        tone = HgTextTone.Assistive,
                    )

                    if (state.lastErrorMessage.isNotEmpty()) {
                        HeightSpacer(16)
                        HgText(
                            text = state.lastErrorMessage,
                            style = HGTypography.body2Medium,
                            tone = HgTextTone.Warning,
                        )
                    }
                }
            }
            item { HeightSpacer(16) }
            item {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    HgTextSelector(
                        text = "좋아하는 애니메이션이 없어요",
                        iconVisible = false,
                        onClick = { state.onSkip() },
                        selected = false,
                    )

                    state.animeCatalog.forEach { anime ->
                        HgTextSelector(
                            text = anime.name,
                            selected = state.selectedAnimeIds.contains(anime.id),
                            onClick = { state.onToggleAnime(anime.id) },
                            enabled = state.canSelectMore ||
                                state.selectedAnimeIds.contains(anime.id),
                        )
                    }
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
                    Anime(id = 1, name = "원피스"),
                    Anime(id = 2, name = "나루토"),
                    Anime(id = 3, name = "드래곤볼"),
                    Anime(id = 4, name = "데몬슬레이어"),
                    Anime(id = 5, name = "공격의 거인"),
                    Anime(id = 6, name = "주술회전"),
                    Anime(id = 7, name = "헌터x헌터"),
                    Anime(id = 8, name = "스파이 패밀리"),
                    Anime(id = 9, name = "체인소우맨"),
                    Anime(id = 10, name = "블리치"),
                ),
                selectedAnimeIds = setOf(1),
                canSelectMore = true,
                isLoading = false,
                onToggleAnime = {},
                onNext = {},
                onSkip = {},
                lastErrorMessage = "Test",
            ),
            modifier = Modifier,
        )
    }
}
