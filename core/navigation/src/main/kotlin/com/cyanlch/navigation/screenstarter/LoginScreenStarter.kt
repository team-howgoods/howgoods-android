package com.cyanlch.navigation.screenstarter

import com.slack.circuit.runtime.screen.Screen

fun interface LoginScreenStarter {
    operator fun invoke(nextScreen: Screen?): Screen
}
