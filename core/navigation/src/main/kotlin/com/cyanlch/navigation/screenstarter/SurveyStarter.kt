package com.cyanlch.navigation.screenstarter

import com.slack.circuit.runtime.screen.Screen

fun interface SurveyStarter {
    operator fun invoke(): Screen
}
