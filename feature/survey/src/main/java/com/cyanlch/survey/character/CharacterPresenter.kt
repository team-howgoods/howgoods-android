package com.cyanlch.survey.character

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.domain.policy.SurveySelectionPolicy
import com.cyanlch.survey.model.SurveyStep
import com.cyanlch.survey.model.SurveyStore
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class CharacterPresenter @AssistedInject constructor(
    private val store: SurveyStore,
    @Assisted private val navigator: Navigator,
) : Presenter<CharacterScreen.State> {

    @Composable
    override fun present(): CharacterScreen.State {
        val storeState by store.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            store.loadCharactersForSelectedAnime()
        }

        val canSelectMore by remember(storeState.form.selectedCharacterIds) {
            derivedStateOf {
                storeState.form.selectedCharacterIds.size < SurveySelectionPolicy.MAX_CHARACTER
            }
        }
        val groups: List<AnimeCharactersGroup> = remember(
            storeState.form.selectedAnimeIds,
            storeState.form.characterListsByAnime,
            storeState.form.selectedCharacterIds,
        ) {
            storeState.form.selectedAnimeIds
                .mapNotNull { aid -> storeState.form.characterListsByAnime[aid] }
                .map { list ->
                    AnimeCharactersGroup(
                        anime = list.anime,
                        items = list.characters.map { ch ->
                            CharacterRowItem(
                                id = ch.id,
                                name = ch.name,
                                imageUrl = ch.imageUrl,
                                isSelected = ch.id in storeState.form.selectedCharacterIds,
                                isEnabled = (ch.id in storeState.form.selectedCharacterIds) ||
                                    canSelectMore,
                            )
                        },
                    )
                }
        }

        fun handleNext() {
            if (store.validate(SurveyStep.Character).isValid) {
                // navigator.goTo(CharacterScreen)
                TODO()
            }
        }

        fun onBack() {
            navigator.pop()
        }

        return CharacterScreen.State(
            groups = groups,
            canSelectMore = canSelectMore,
            isLoading = storeState.isLoading,
            onToggleAnimeCharacter = store::selectOrDeselectCharacter,
            onNext = ::handleNext,
            onBack = ::onBack,
        )
    }

    @CircuitInject(CharacterScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): CharacterPresenter
    }
}
