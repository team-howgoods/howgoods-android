package com.cyanlch.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.cyanlch.domain.model.auth.SocialPlatform
import com.cyanlch.login.social.SocialLoginDispatcher
import com.cyanlch.ui.R
import com.cyanlch.ui.SocialButton
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

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
                    imagePainter = painterResource(id = com.cyanlch.ui.R.drawable.ic_apple),
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

