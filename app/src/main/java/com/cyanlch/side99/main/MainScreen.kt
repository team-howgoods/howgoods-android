package com.cyanlch.side99.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cyanlch.login.LoginScreen
import com.cyanlch.ui.circuit.NoState
import com.slack.circuit.codegen.annotations.CircuitInject
import androidx.compose.material3.CircularProgressIndicator
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize

@Parcelize
object MainScreen : Screen

class MainPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator
) : Presenter<NoState> {
    @Composable
    override fun present(): NoState {
        LaunchedEffect(Unit) {
            navigator.goTo(LoginScreen())
        }
        return NoState
    }

    @CircuitInject(MainScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): MainPresenter
    }
}

@CircuitInject(MainScreen::class, ActivityRetainedComponent::class)
@Composable
fun MainUi(state: NoState, modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
