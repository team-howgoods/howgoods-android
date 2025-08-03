package com.cyanlch.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.cyanlch.login.social.SocialLoginDispatcher
import com.cyanlch.login.social.SocialPlatform
import com.cyanlch.ui.R
import com.cyanlch.ui.SocialButton
import com.kakao.sdk.common.util.Utility
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class LoginScreen(val nextScreen: Screen? = null) : Screen {
    data class State(
        val effect: Event? = null,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data class LaunchSocial(val platform: SocialPlatform) : Event
    }
}

class LoginPresenter @AssistedInject constructor() : Presenter<LoginScreen.State> {
    @Composable
    override fun present(): LoginScreen.State {
        val context = LocalContext.current
        var effect by remember { mutableStateOf<LoginScreen.Event?>(null) }
        val currentEffect = effect
        LaunchedEffect(currentEffect) {
            effect = null
        }

        LaunchedEffect(Unit) {
            Log.e("Cyan", "${Utility.getKeyHash(context)}")
        }

        fun handleEvent(event: LoginScreen.Event) {
            when (event) {
                is LoginScreen.Event.LaunchSocial -> {
                    effect = LoginScreen.Event.LaunchSocial(event.platform)
                }
            }
        }

        return LoginScreen.State(effect, ::handleEvent)
    }

    @CircuitInject(LoginScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(): LoginPresenter
    }
}

@CircuitInject(LoginScreen::class, ActivityRetainedComponent::class)
class LoginUi @Inject constructor(
    private val dispatcher: SocialLoginDispatcher,
) : Ui<LoginScreen.State> {
    @Composable
    override fun Content(
        state: LoginScreen.State,
        modifier: Modifier,
    ) {
        val context = LocalContext.current
        state.effect?.let {
            when (it) {
                is LoginScreen.Event.LaunchSocial -> {
                    dispatcher.login(platform = it.platform, context = context)
                }
            }
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                SocialButton(
                    text = "Login with Kakao",
                    isEnabled = true,
                    imagePainter = painterResource(id = R.drawable.ic_apple),
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    onClick = { state.eventSink(LoginScreen.Event.LaunchSocial(SocialPlatform.KAKAO)) },
                )

                SocialButton(
                    text = "Login with Naver",
                    isEnabled = true,
                    imagePainter = painterResource(id = R.drawable.ic_apple),
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    onClick = { state.eventSink(LoginScreen.Event.LaunchSocial(SocialPlatform.NAVER)) },
                )
            }
        }
    }
}

