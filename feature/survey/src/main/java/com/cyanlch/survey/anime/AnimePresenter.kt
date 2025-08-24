package com.cyanlch.survey.anime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.domain.policy.SurveySelectionPolicy
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
            if (store.validate(SurveyStep.Anime).isValid) {
                // navigator.goTo(CharacterScreen)
                TODO()
            }
        }

        return AnimeScreen.State(
            animeCatalog = storeState.form.animeCatalog,
            selectedAnimeIds = storeState.form.selectedAnimeIds,
            canSelectMore = canSelectMore,
            isLoading = storeState.isLoading,
            onToggleAnime = store::selectOrDeselectAnime,
            onNext = ::handleNext,
        )
    }

    @CircuitInject(AnimeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): AnimePresenter
    }
}
