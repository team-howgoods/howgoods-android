package com.cyanlch.survey.selection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.domain.usecase.survey.FetchGoodsSelectionUseCase
import com.cyanlch.survey.model.SurveyStore
import com.cyanlch.survey.search.GoodsSearchScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class GoodsSelectionPresenter @AssistedInject constructor(
    private val fetchGoods: FetchGoodsSelectionUseCase,
    private val store: SurveyStore,
    @Assisted private val navigator: Navigator,
) : Presenter<GoodsSelectionScreen.State> {
    @Composable
    override fun present(): GoodsSelectionScreen.State {
        val storeState by store.uiState.collectAsStateWithLifecycle()
        var items by remember { mutableStateOf(emptyList<GoodsSelectionScreen.GoodsItem>()) }

        LaunchedEffect(Unit) {
            fetchGoods().onSuccess { list ->
                items = list.map {
                    GoodsSelectionScreen.GoodsItem(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.imageUrl,
                        animeId = it.animeId,
                        animeName = it.animeName,
                    )
                }
            }
        }

        fun onBack() {
            navigator.pop()
        }

        fun onSearchClick() {
            navigator.goTo(GoodsSearchScreen)
        }

        return GoodsSelectionScreen.State(
            items = items,
            selectedGoods = storeState.form.selectedGoods,
            onToggleGoods = store::selectOrDeselectGoods,
            onSearchClick = ::onSearchClick,
            onNext = {},
            onSkip = {},
            onBack = ::onBack,
        )
    }

    @CircuitInject(GoodsSelectionScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): GoodsSelectionPresenter
    }
}
