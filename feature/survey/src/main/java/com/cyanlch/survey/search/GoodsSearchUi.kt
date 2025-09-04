package com.cyanlch.survey.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent

@CircuitInject(GoodsSearchScreen::class, ActivityRetainedComponent::class)
internal class GoodsSearchUi : Ui<GoodsSearchScreen.State> {
    @Composable
    override fun Content(state: GoodsSearchScreen.State, modifier: Modifier) {
        TODO("Not yet implemented")
    }
}
