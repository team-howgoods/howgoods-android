package com.cyanlch.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.SocialPlatform
import com.cyanlch.domain.usecase.auth.SocialLoginUseCase
import com.cyanlch.login.social.SocialLoginDispatcher
import com.cyanlch.ui.R
import com.cyanlch.ui.SocialButton
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.launch
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
        data class RequestLogin(val platform: SocialPlatform, val code: String) : Event
        data class Toast(val message: String) : Event
    }
}

class LoginPresenter @AssistedInject constructor(
    private val socialLoginUseCase: SocialLoginUseCase
) : Presenter<LoginScreen.State> {
    @Composable
    override fun present(): LoginScreen.State {
        var effect by remember { mutableStateOf<LoginScreen.Event?>(null) }
        val scope = rememberCoroutineScope()
        val currentEffect = effect
        LaunchedEffect(currentEffect) {
            effect = null
        }

        fun handleEvent(event: LoginScreen.Event) {
            when (event) {
                is LoginScreen.Event.LaunchSocial -> {
                    effect = LoginScreen.Event.LaunchSocial(event.platform)
                }
                is LoginScreen.Event.RequestLogin -> {
                    scope.launch {
                        socialLoginUseCase(SocialLoginRequest(
                            platform = event.platform,
                            code = event.code
                        )).onSuccess {
                            Log.e("LoginPresenter", it.email)
                            effect = LoginScreen.Event.Toast(it.email)
                        }.onFailure {
                            Log.e("LoginPresenter", it.message.toString())
                            effect = LoginScreen.Event.Toast(it.message.toString())
                        }
                    }
                }
                is LoginScreen.Event.Toast -> {}
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
        val scope = rememberCoroutineScope()

        state.effect?.let {
            when (it) {
                is LoginScreen.Event.LaunchSocial -> {
                    scope.launch {
                        val providerToken = dispatcher.login(platform = it.platform, context = context).getOrNull()
                        providerToken ?: return@launch
                        state.eventSink(LoginScreen.Event.RequestLogin(it.platform, providerToken))
                    }
                }
                is LoginScreen.Event.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
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

