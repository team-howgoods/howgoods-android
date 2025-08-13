package com.cyanlch.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.usecase.auth.SocialLoginUseCase
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.launch

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
                            // TODO: remove
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
