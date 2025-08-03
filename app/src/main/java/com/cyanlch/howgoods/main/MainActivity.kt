package com.cyanlch.howgoods.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.overlay.ContentWithOverlays
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var circuit: Circuit

    private var _stackedScreens: List<Screen> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            CircuitCompositionLocals(circuit) {
                /*val stackedScreens: List<Screen> by remember {
                    mutableStateOf(_stackedScreens)
                }*/
                ContentWithOverlays {
                    val backStack = rememberSaveableBackStack(root = MainScreen)
                    val navigator = rememberCircuitNavigator(backStack)
                    NavigableCircuitContent(
                        navigator = navigator,
                        backStack = backStack,
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }
}
