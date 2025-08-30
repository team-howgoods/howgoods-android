package com.cyanlch.survey.selection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.survey.model.SurveyStore
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class GoodsSelectionPresenter @AssistedInject constructor(
    private val store: SurveyStore,
    @Assisted private val navigator: Navigator,
) : Presenter<GoodsSelectionScreen.State> {
    @Composable
    override fun present(): GoodsSelectionScreen.State {
        val storeState by store.uiState.collectAsStateWithLifecycle()
        var searchText by remember { mutableStateOf("") }

        fun onBack() {
            navigator.pop()
        }

        return GoodsSelectionScreen.State(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onToggleGoods = { TODO() },
            selectedGoodsIds = storeState.form.selectedGoodsIds,
            selectedGoodsCount = storeState.form.selectedGoodsIds.size,
            onNext = { TODO() },
            onBack = ::onBack,
            onSkip = { TODO() }
        )
    }

    @CircuitInject(GoodsSelectionScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): GoodsSelectionPresenter
    }
}