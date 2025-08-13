package com.cyanlch.login

import com.cyanlch.domain.model.auth.SocialPlatform
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

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
        // data object NavigateToMain : Event
    }
}
