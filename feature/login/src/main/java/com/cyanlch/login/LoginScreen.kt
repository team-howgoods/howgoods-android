package com.cyanlch.login

import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginScreen(val nextScreen: Screen? = null) : Screen
