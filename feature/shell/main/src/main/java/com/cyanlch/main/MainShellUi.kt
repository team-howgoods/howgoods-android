package com.cyanlch.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@CircuitInject(MainShellScreen::class, ActivityRetainedComponent::class)
class MainShellUi @Inject constructor() : Ui<MainShellScreen.State> {
    @Composable
    override fun Content(
        state: MainShellScreen.State,
        modifier: Modifier,
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    state.tabs.forEachIndexed { i, tab ->
                        NavigationBarItem(
                            selected = tab.selected,
                            onClick = { state.onTabSelected(i) },
                            icon = { Icon(
                                painter = painterResource(tab.iconResId),
                                contentDescription = null
                            ) },
                            label = { Text(stringResource(tab.labelResId)) },
                        )
                    }
                }
            },
        ) { inner ->
            state.content(
                Modifier
                    .padding(inner)
            )
        }
    }
}