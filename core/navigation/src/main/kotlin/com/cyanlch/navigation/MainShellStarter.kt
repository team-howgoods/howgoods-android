package com.cyanlch.navigation

import com.slack.circuit.runtime.screen.Screen

fun interface MainShellStarter {
    operator fun invoke(): Screen
}
