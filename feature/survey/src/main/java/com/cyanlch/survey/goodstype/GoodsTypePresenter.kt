package com.cyanlch.survey.goodstype

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.survey.model.SurveyStore
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class GoodsTypePresenter @AssistedInject constructor(
    private val store: SurveyStore,
    @Assisted private val navigator: Navigator,
) : Presenter<GoodsTypeScreen.State> {
    @Composable
    override fun present(): GoodsTypeScreen.State {
        val storeState by store.uiState.collectAsStateWithLifecycle()

        fun onNext() {
            // TODO
        }

        fun onBack() {
            navigator.pop()
        }

        return GoodsTypeScreen.State(
            goodsTypes = storeState.form.goodsTypes,
            onToggleGoodsType = store::selectOrDeselectGoodsType,
            onNext = ::onNext,
            onBack = ::onBack,
        )
    }

    @CircuitInject(GoodsTypeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): GoodsTypePresenter
    }
}
