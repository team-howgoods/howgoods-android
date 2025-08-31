package com.cyanlch.survey.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.cyanlch.domain.model.anime.Anime
import com.cyanlch.domain.policy.SurveySelectionPolicy
import com.cyanlch.survey.character.component.AnimeCharacterSlot
import com.cyanlch.survey.component.SurveyBottomBar
import com.cyanlch.ui.topbar.HgBasicTopBar
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@CircuitInject(CharacterScreen::class, ActivityRetainedComponent::class)
class CharacterUi @Inject constructor() : Ui<CharacterScreen.State> {
    @Composable
    override fun Content(
        state: CharacterScreen.State,
        modifier: Modifier,
    ) {
        HGTheme {
            Scaffold(
                topBar = { HgBasicTopBar(onBackClick = state.onBack) },
                bottomBar = {
                    SurveyBottomBar {
                        HgSolidButton(
                            onClick = state.onNext,
                            enabled = state.selectedCharacterCount
                                >= SurveySelectionPolicy.MIN_CHARACTER,
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            HgText(
                                text = "다음 (${state.selectedCharacterCount} " +
                                    "/ ${SurveySelectionPolicy.MAX_CHARACTER})",
                                style = HGTypography.body2SemiBold,
                                tone = HgTextTone.Unspecified,
                            )
                        }
                    }
                },
            ) { inner ->
                AnimeCharacterContent(
                    state = state,
                    modifier = modifier
                        .padding(inner),
                )
            }
        }
    }
}

@Composable
fun AnimeCharacterContent(
    state: CharacterScreen.State,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LocalHGColors.current.bgDefault),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            stickyHeader {
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
                        text = "가장 좋아하는 캐릭터는 누구인가요?",
                        style = HGTypography.headlineSemiBold,
                    )
                    HeightSpacer(28)
                }
            }

            items(state.groups) { group ->
                AnimeCharacterSlot(
                    characterGroup = group,
                    onToggleCharacter = { state.onToggleAnimeCharacter(it) },
                )
            }
        }
    }
}

@Preview
@Composable
fun AnimeCharacterScreenContentPreview() {
    HGTheme {
        AnimeCharacterContent(
            state = CharacterScreen.State(
                groups = listOf(
                    AnimeCharactersGroup(
                        Anime(id = 1, name = "원피스"),
                        listOf(
                            CharacterRowItem(id = 1, name = "루피"),
                            CharacterRowItem(id = 2, name = "조로"),
                            CharacterRowItem(id = 3, name = "상디"),
                        ),
                    ),
                    AnimeCharactersGroup(
                        Anime(id = 2, name = "나루토"),
                        listOf(
                            CharacterRowItem(id = 4, name = "나루토"),
                            CharacterRowItem(id = 5, name = "사스케"),
                            CharacterRowItem(id = 6, name = "사쿠라"),
                        ),
                    ),
                    AnimeCharactersGroup(
                        Anime(id = 3, name = "블리치"),
                        listOf(
                            CharacterRowItem(id = 7, name = "이치고"),
                            CharacterRowItem(id = 8, name = "루키아"),
                            CharacterRowItem(id = 9, name = "렌지"),
                        ),
                    ),
                    AnimeCharactersGroup(
                        Anime(id = 3, name = "블리치"),
                        listOf(
                            CharacterRowItem(id = 7, name = "이치고"),
                            CharacterRowItem(id = 8, name = "루키아"),
                            CharacterRowItem(id = 9, name = "렌지"),
                        ),
                    ),
                    AnimeCharactersGroup(
                        Anime(id = 3, name = "블리치"),
                        listOf(
                            CharacterRowItem(id = 7, name = "이치고"),
                            CharacterRowItem(id = 8, name = "루키아"),
                            CharacterRowItem(id = 9, name = "렌지"),
                        ),
                    ),
                ),
                canSelectMore = false,
                isLoading = false,
                onNext = {},
                onBack = {},
                onToggleAnimeCharacter = {},
                selectedCharacterCount = 1,
            ),
            modifier = Modifier,
        )
    }
}
