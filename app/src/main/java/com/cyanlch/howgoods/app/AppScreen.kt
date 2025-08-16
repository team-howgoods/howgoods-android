package com.cyanlch.howgoods.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cyanlch.domain.usecase.auth.GetUserLoginStateUseCase
import com.cyanlch.domain.usecase.auth.LoginState
import com.cyanlch.login.LoginScreen
import com.cyanlch.main.MainShellScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize

@Parcelize
object AppScreen : Screen {
    data object State : CircuitUiState
}

class AppPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val getUserLoginStateUseCase: GetUserLoginStateUseCase
) : Presenter<AppScreen.State> {
    @Composable
    override fun present(): AppScreen.State {
        LaunchedEffect(Unit) {
            val loginState = getUserLoginStateUseCase()
            if (loginState is LoginState.Login) {
                navigator.goTo(MainShellScreen)
            } else {
                navigator.goTo(LoginScreen())
            }
        }

        return AppScreen.State
    }

    @CircuitInject(AppScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): AppPresenter
    }
}

@CircuitInject(AppScreen::class, ActivityRetainedComponent::class)
@Composable
fun AppUi(state: AppScreen.State, modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
