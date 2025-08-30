package com.cyanlch.survey.noselection

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent

@CircuitInject(NoSelectionScreen::class, ActivityRetainedComponent::class)
class NoSelectionUi : Ui<NoSelectionScreen.State> {
    @Composable
    override fun Content(
        state: NoSelectionScreen.State,
        modifier: Modifier,
    ) {
        TODO("Not yet implemented")
    }
}
