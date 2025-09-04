package com.cyanlch.survey.search

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

internal class GoodsSearchPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<GoodsSearchScreen.State> {
    @Composable
    override fun present(): GoodsSearchScreen.State {
        // TODO: state 구성
        return GoodsSearchScreen.State
    }

    @CircuitInject(GoodsSearchScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): GoodsSearchPresenter
    }
}