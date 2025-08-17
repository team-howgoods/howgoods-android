package com.cyanlch.home

import androidx.compose.runtime.Composable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class HomePresenter @AssistedInject constructor(
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        return HomeScreen.State
    }

    @CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(): HomePresenter
    }
}
