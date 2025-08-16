package com.cyanlch.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.cyanlch.main.model.TabState
import com.cyanlch.navigation.TabSpec
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.presenter.Presenter
import javax.inject.Inject

class MainShellPresenter @Inject constructor(
    private val tabs: Set<@JvmSuppressWildcards TabSpec>
) : Presenter<MainShellScreen.State> {
    @Composable
    override fun present(): MainShellScreen.State {
        val ordered = remember(tabs) { tabs.sortedBy { it.order } }
        var currentIndex by rememberSaveable { mutableIntStateOf(0) }
        val backStacks =
            ordered.map { rememberSaveableBackStack(root = it.root) }
        val currentBackStack = backStacks[currentIndex]
        val navigator = rememberCircuitNavigator(currentBackStack)
        val content: @Composable (Modifier) -> Unit = { modifier ->
            NavigableCircuitContent(navigator, currentBackStack, modifier)
        }

        return MainShellScreen.State(
            tabs = ordered.mapIndexed { index, tabSpec -> TabState(
                tabSpec.labelResId,
                tabSpec.iconResId,
                index == currentIndex) },
            onTabSelected = { currentIndex = it },
            content = content
        )
    }
}