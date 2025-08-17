package com.cyanlch.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.cyanlch.navigation.MainTabs
import com.cyanlch.navigation.TabSpec
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class MainShellPresenter @AssistedInject constructor(
    @param:MainTabs private val tabs: Set<@JvmSuppressWildcards TabSpec>
) : Presenter<MainShellScreen.State> {
    @Composable
    override fun present(): MainShellScreen.State {
        val ordered = remember(tabs) { tabs.sortedBy { it.order } }
        if (ordered.isEmpty()) {
            return MainShellScreen.State(
                tabs = emptyList(),
                onTabSelected = {},
                currentIndex = 0
            )
        }

        var currentIndex by rememberSaveable { mutableIntStateOf(0) }
        if (currentIndex !in ordered.indices) currentIndex = 0

        return MainShellScreen.State(
            tabs = ordered,
            onTabSelected = { currentIndex = it },
            currentIndex = currentIndex
        )
    }

    @CircuitInject(MainShellScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(): MainShellPresenter
    }
}