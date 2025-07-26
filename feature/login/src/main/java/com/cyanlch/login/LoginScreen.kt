package com.cyanlch.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cyanlch.ui.SocialButton
import com.cyanlch.ui.circuit.NoState
import com.cyanlch.ui.R
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
data class LoginScreen(val nextScreen: Screen? = null) : Screen {
    data object State : CircuitUiState
}

class LoginPresenter @AssistedInject constructor() : Presenter<LoginScreen.State> {
    @Composable
    override fun present(): LoginScreen.State {
        return LoginScreen.State
    }

    @CircuitInject(LoginScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(): LoginPresenter
    }
}


@CircuitInject(LoginScreen::class, ActivityRetainedComponent::class)
@Composable
fun LoginUi(state: LoginScreen.State, modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center)
    {
        SocialButton(
            text = "Login with Apple",
            isEnabled = true,
            imagePainter = painterResource(id = R.drawable.ic_apple),
            textColor = Color.Black,
            backgroundColor = Color.White,
            onClick = {}
        )
    }
}
