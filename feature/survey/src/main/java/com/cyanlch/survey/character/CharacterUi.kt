package com.cyanlch.survey.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.WidthSpacer
import com.cyanlch.designsystem.button.HgSolidButton
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.designsystem.ui.LocalHGColors
import com.cyanlch.survey.character.component.AnimeCharacterSlot
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
    modifier: Modifier,
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

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(LocalHGColors.current.bgDefault)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp, top = 24.dp),
        ) {
            Row {
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
        }
    }
}
/*

@Preview
@Composable
fun AnimeCharacterScreenContentPreview() {
    HGTheme {
        AnimeCharacterContent(
            state = CharacterScreen.State(
                ,
                selectedAnimeIds = setOf("1"),
                canSelectMore = true,
                isLoading = false,
                onToggleAnimeCharacter = {},
                onNext = {},
                onBack = {},
            ),
            modifier = Modifier,
        )
    }
}
*/
