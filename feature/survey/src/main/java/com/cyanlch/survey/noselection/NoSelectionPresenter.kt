package com.cyanlch.survey.noselection

import androidx.compose.runtime.Composable
import com.cyanlch.navigation.MainShellStarter
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class NoSelectionPresenter @AssistedInject constructor(
    private val mainShellStarter: MainShellStarter,
    @Assisted private val navigator: Navigator,
) : Presenter<NoSelectionScreen.State> {
    @Composable
    override fun present(): NoSelectionScreen.State {
        fun onHomeClick() {
            navigator.resetRoot(mainShellStarter())
        }

        fun onBack() {
            navigator.pop()
        }

        return NoSelectionScreen.State(
            onBack = ::onBack,
            onHomeClick = ::onHomeClick,
        )
    }

    @CircuitInject(NoSelectionScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): NoSelectionPresenter
    }
}
