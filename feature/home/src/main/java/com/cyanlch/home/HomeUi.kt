package com.cyanlch.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
class MainUi @Inject constructor() : Ui<HomeScreen.State> {
    @Composable
    override fun Content(
        state: HomeScreen.State,
        modifier: Modifier,
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Text("Home")
        }
    }
}

