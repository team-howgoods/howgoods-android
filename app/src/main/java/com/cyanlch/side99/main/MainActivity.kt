package com.cyanlch.side99.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.overlay.ContentWithOverlays
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    @Inject
    lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            CircuitCompositionLocals(circuit) {
                ContentWithOverlays {
                    val backStack = rememberSaveableBackStack(root = MainScreen)
                    val navigator = rememberCircuitNavigator(backStack)
                    NavigableCircuitContent(
                        navigator = navigator,
                        backStack = backStack
                    )
                }
            }
        }
    }
}