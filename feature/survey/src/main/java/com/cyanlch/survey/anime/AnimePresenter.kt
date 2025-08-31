package com.cyanlch.survey.anime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.domain.policy.SurveySelectionPolicy
import com.cyanlch.survey.character.CharacterScreen
import com.cyanlch.survey.model.SurveyStep
import com.cyanlch.survey.model.SurveyStore
import com.cyanlch.survey.noselection.NoSelectionScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class AnimePresenter @AssistedInject constructor(
    private val store: SurveyStore,
    @Assisted private val navigator: Navigator,
) : Presenter<AnimeScreen.State> {

    @Composable
    override fun present(): AnimeScreen.State {
        val storeState by store.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            store.loadAnimeCatalogIfEmpty()
        }
        val canSelectMore by remember(storeState.form.selectedAnimeIds) {
            derivedStateOf {
                storeState.form.selectedAnimeIds.size < SurveySelectionPolicy.MAX_ANIME
            }
        }

        fun handleNext() {
            val validator = store.validate(SurveyStep.Anime)
            if (validator.isValid) {
                navigator.goTo(CharacterScreen)
            }
        }

        fun onSkip() {
            navigator.goTo(NoSelectionScreen)
        }

        return AnimeScreen.State(
            errorMessage = storeState.lastErrorMessage,
            maxSelectCount = SurveySelectionPolicy.MAX_ANIME,
            animeCatalog = storeState.form.animeCatalog,
            selectedAnimeIds = storeState.form.selectedAnimeIds,
            canSelectMore = canSelectMore,
            isLoading = storeState.isLoading,
            onToggleAnime = store::selectOrDeselectAnime,
            onNext = ::handleNext,
            onSkip = ::onSkip,
        )
    }

    @CircuitInject(AnimeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): AnimePresenter
    }
}
